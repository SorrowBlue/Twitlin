/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.v2.TAG
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.client.statement.request
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.http.contentType
import io.ktor.http.formUrlEncode
import io.ktor.http.isSuccess
import io.ktor.util.toMap
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal expect val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>

internal class TwitlinClient(
    val apiKey: String,
    val secretKey: String,
    var accessToken: AccessToken? = null,
    var bearerToken: BearerToken? = null
) {

    val json: Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    val httpClient
        get() = HttpClient(clientEngineFactory) {
            install(JsonFeature) { serializer = KotlinxSerializer(json) }
        }

    suspend inline fun <reified T : Any> get(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        useBearerToken: Boolean = false,
    ): Response<T> = catchResponse {
        httpClient.get(url.combineParams(params.notNullParams)) {
            if (useBearerToken) {
                headerForTwitter(bearerToken)
            } else {
                headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
            }
        }
    }

    suspend inline fun <reified T : Any, reified V : Any> getCustom(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        useBearerToken: Boolean = false,
        converter: (V, HttpResponse) -> Response<T>
    ): Response<T> =
        httpClient.get<HttpResponse>(url.combineParams(params.notNullParams)) {
            if (useBearerToken) {
                headerForTwitter(bearerToken)
            } else {
                headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
            }
        }.toCustomResponse(converter)

    suspend inline fun <reified T : Any> post(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        useBearerToken: Boolean = false,
        oauthToken: String? = null
    ): Response<T> = catchResponse {
        httpClient.post(url) {
            if (useBearerToken) {
                headerForTwitter(bearerToken)
            } else {
                val accessToken = oauthToken?.let {
                    accessToken?.copy(oauthToken = oauthToken) ?: AccessToken(it, "", "", "")
                } ?: accessToken
                headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
            }
            bodyForTwitter(params.notNullParams)
        }
    }

    suspend inline fun <reified T : Any, reified V : Any> postCustom(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        useBearerToken: Boolean = false,
        converter: (V, HttpResponse) -> Response<T>
    ): Response<T> =
        httpClient.post<HttpResponse>(url.combineParams(params.notNullParams)) {
            if (useBearerToken) {
                headerForTwitter(bearerToken)
            } else {
                headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
            }
        }.toCustomResponse(converter)

    suspend inline fun <reified V : Any, reified T : Any> postJson(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        body: V,
        useBearerToken: Boolean = false
    ): Response<T> = catchResponse {
        httpClient.post(url) {
            if (useBearerToken) {
                headerForTwitter(bearerToken)
            } else {
                headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
            }
            contentType(ContentType.MultiPart.FormData)
            this.body =
                TextContent(json.encodeToString(body), contentType = ContentType.Application.Json)
        }
    }

    suspend inline fun <reified T : Any> postMultiPartFormData(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        useBearerToken: Boolean = false
    ): Response<T> = catchResponse {
        httpClient.post(url) {
            if (useBearerToken) {
                headerForTwitter(bearerToken)
            } else {
                headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
            }
            bodyForTwitter(params.notNullParams)
        }
    }

    private fun log(level: Napier.Level, response: HttpResponse, body: String) =
        Napier.log(
            priority = level,
            tag = "TwitlinClient",
            message = "method: ${response.request.method}, url: ${response.request.url}, statusCode: ${response.status}, headers: ${response.request.headers.toMap()}, body: $body",
        )

    private suspend inline fun <reified T> catchResponse(action: () -> HttpResponse): Response<T> =
        kotlin.runCatching { action() }
            .fold({
                if (it.status.isSuccess()) {
                    val body = it.readText()
                    log(Napier.Level.DEBUG, it, body)
                    Response.Success(json.decodeFromString<T>(body))
                } else {
                    val body = it.content.readUTF8Line()!!
                    log(Napier.Level.ERROR, it, body)
                    Response.Error(Json.decodeFromString(body))
                }
            }) {
                Napier.e(it.toString(), it, tag = "TwitlinClient")
                val message = it.message.orEmpty()
                val errorCode = errorPair.find { message.contains(it.first) }?.second
                    ?: ErrorCodes.CLIENT_ERROR
                Response.error(ErrorMessages.Error(it.message ?: "unknown error", errorCode))
            }

    private suspend inline fun <reified T : Any, V : Any> HttpResponse.toCustomResponse(converter: (T, HttpResponse) -> Response<V>): Response<V> {
        return kotlin.runCatching {
            if (status.isSuccess()) {
                converter(json.decodeFromString(readText()), this)
            } else {
                val body = content.readUTF8Line()!!
                json.decodeFromString<Response.Error<V>>(body)
            }
        }.getOrElse { it.toError() }
    }

    private fun <T : Any> Throwable.toError(): Response.Error<T> {
        Napier.e(stackTraceToString(), this, TAG)
        val errorCode = errorPair.find { message?.contains(it.first) == true }?.second
            ?: ErrorCodes.CLIENT_ERROR
        return if (toString().contains("java.net.UnknownHostException")) {
            Response.error(ErrorMessages.Error("UnknownHostException", errorCode))
        } else {
            Response.error(ErrorMessages.Error(stackTraceToString(), ErrorCodes.CLIENT_ERROR))
        }
    }


}

internal fun String.combineParams(params: List<Pair<String, String>>): String =
    if (params.isEmpty()) this else this + "?" + params.formUrlEncode()

private val errorPair = listOf("java.net.UnknownHostException" to ErrorCodes.NO_NETWORK)
