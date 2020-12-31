/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.client.ErrorCodes
import com.sorrowblue.twitlin.client.bodyForTwitter
import com.sorrowblue.twitlin.client.clientEngineFactory
import com.sorrowblue.twitlin.client.combineParams
import com.sorrowblue.twitlin.client.headerForTwitter
import com.sorrowblue.twitlin.client.notNullParams
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.content.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.utils.io.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


private val json: Json = Json {
    isLenient = true
    ignoreUnknownKeys = true
    encodeDefaults = false
}

internal open class Client(
    private val apiKey: String,
    private val secretKey: String,
    var accessToken: AccessToken? = null,
    var bearerToken: BearerToken? = null
) {


    val httpClient
        get() = HttpClient(clientEngineFactory) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
            }
        }

    suspend inline fun <reified T : Any> get(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        useBearerToken: Boolean = false
    ): Response<T> =
        catchResponse {
            httpClient.get<HttpResponse>(url.combineParams(params.notNullParams)) {
                if (useBearerToken) {
                    headerForTwitter(this@Client.bearerToken)
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
                headerForTwitter(this@Client.bearerToken)
            } else {
                headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
            }
        }.toCustomResponse(converter)

    suspend inline fun <reified T : Any> post(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        oauthToken: String? = null,
        useBearerToken: Boolean = false
    ): Response<T> =
        httpClient.post<HttpResponse>(url) {
            if (useBearerToken) {
                headerForTwitter(this@Client.bearerToken)
            } else {
                val accessToken = oauthToken?.let { accessToken?.copy(oauthToken = it) }
                headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
            }
            bodyForTwitter(params.notNullParams)
        }.toResponse()

    suspend inline fun <reified V : Any, reified T : Any> postJson(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        body: V,
        oauthToken: String? = null,
        useBearerToken: Boolean = false
    ): Response<T> = httpClient.post<HttpResponse>(url) {
        if (useBearerToken) {
            headerForTwitter(this@Client.bearerToken)
        } else {
            val accessToken = oauthToken?.let { accessToken?.copy(oauthToken = it) }
            headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
        }
        this.body =
            TextContent(json.encodeToString(body), contentType = ContentType.Application.Json)
    }.toResponse()

    suspend inline fun <reified V : Any, reified T : Any, reified B : Any> postJsonCustom(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        body: B,
        oauthToken: String? = null,
        useBearerToken: Boolean = false,
        converter: (V, HttpResponse) -> Response<T>
    ): Response<T> = httpClient.post<HttpResponse>(url) {
        if (useBearerToken) {
            headerForTwitter(this@Client.bearerToken)
        } else {
            val accessToken = oauthToken?.let { accessToken?.copy(oauthToken = it) }
            headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
        }
        this.body =
            TextContent(json.encodeToString(body), contentType = ContentType.Application.Json)
    }.toCustomResponse(converter)

    suspend inline fun <reified T : Any, reified V : Any> put(url: String, body: V): Response<T> =
        httpClient.put<HttpResponse>(url) {
            headerForTwitter(apiKey, secretKey, emptyList(), accessToken)
            this.body =
                TextContent(json.encodeToString(body), contentType = ContentType.Application.Json)
        }.toResponse()

    @ExperimentalCoroutinesApi
    inline fun <reified T : Any> streaming(
        url: String,
        vararg params: Pair<String, Any?> = emptyArray(),
        useBearerToken: Boolean = false
    ): Flow<Response<T>> {
        return channelFlow {
            httpClient.get<HttpStatement>(url.combineParams(params.notNullParams)) {
                if (useBearerToken) {
                    headerForTwitter(this@Client.bearerToken)
                } else {
                    headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
                }
            }.execute { response ->
                do {
                    response.toResponse<T>().let(channel::offer)
                } while (isClosedForSend.not())
            }
        }
    }
}

internal fun HttpResponse.sendLog(content: String) {
    Napier.i(
        """
            status  = $status
		    method  = ${request.method}
		    url     = ${request.url}
		    headers = ${request.headers.toMap()}
		    content = $content
		""".trimIndent(), tag = TAG
    )
}

internal suspend inline fun <reified T : Any> catchResponse(function: () -> HttpResponse): Response<T> =
    runCatching {
        with(function()) {
            Napier.d("HttpResponse.toResponse isSuccess = ${status.isSuccess()}", tag = "APPAPP")
            if (status.isSuccess()) {
                readText().let {
                    sendLog(it)
                    json.decodeFromString<Response.Success<T>>(it)
                }
            } else {
                content.readUTF8Line()!!.let {
                    sendLog(it)
                    json.decodeFromString<Response.Failure<T>>(it)
                }
            }
        }
    }.getOrElse {
        it.toFailure()
    }

internal suspend inline fun <reified T : Any> HttpResponse.toResponse(): Response<T> =
    kotlin.runCatching {
        Napier.d("HttpResponse.toResponse isSuccess = ${status.isSuccess()}", tag = "APPAPP")
        if (status.isSuccess()) {
            readText().let {
                sendLog(it)
                json.decodeFromString<Response.Success<T>>(it)
            }
        } else {
            content.readUTF8Line()!!.let {
                sendLog(it)
                json.decodeFromString<Response.Failure<T>>(it)
            }
        }
    }.getOrElse { it.toFailure() }

internal suspend inline fun <reified T : Any, V : Any> HttpResponse.toCustomResponse(converter: (T, HttpResponse) -> Response<V>): Response<V> {
    return kotlin.runCatching {
        if (status.isSuccess()) {
            readText().let {
                sendLog(it)
                converter(json.decodeFromString(it), this)
            }
        } else {
            content.readUTF8Line()!!.let {
                sendLog(it)
                json.decodeFromString<Response.Failure<V>>(it)
            }
        }
    }.getOrElse { it.toFailure() }
}

internal fun <T : Any> Throwable.toFailure(): Response.Failure<T> {
    Napier.e(stackTraceToString(), this, TAG)
    return if (toString().contains("java.net.UnknownHostException")) {
        Response.Failure(
            statusCode = ErrorCodes.NO_NETWORK,
            Error(
                title = "UnknownHostException",
                detail = stackTraceToString(),
                type = Error.Type.UNKNOWN_HOST_EXCEPTION
            )
        )
    } else {
        Response.Failure(
            statusCode = ErrorCodes.CLIENT_ERROR,
            Error(
                title = "unknowns",
                detail = stackTraceToString(),
                type = Error.Type.UNKNOWNS
            )
        )
    }
}

internal const val TAG = "TwitlinClient"
