/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.formUrlEncode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * TODO
 *
 * @property apiKey
 * @property secretKey
 */
public abstract class TwitterClient(public val apiKey: String, public val secretKey: String) {

    protected val json: Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    protected val httpClient: HttpClient
        get() = HttpClient(clientEngineFactory) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
                accept(ContentType.Text.Html)
            }
        }

    protected fun String.combineParams(params: Array<out Pair<String, Any?>>): String =
        params.notNullParams.let { if (it.isEmpty()) this else this + "?" + it.formUrlEncode() }

    private val errorPair = listOf("java.net.UnknownHostException" to ErrorCodes.NO_NETWORK)

    protected suspend inline fun <reified R : Any> baseRequest(
        method: HttpMethod,
        url: String,
        noinline headerBlock: HttpRequestBuilder.() -> String,
        noinline bodyBlock: HttpRequestBuilder.() -> String,
        noinline onError: (Throwable) -> R
    ): R = runCatchingResponse({
        lateinit var encodedPath: String
        httpClient.request<R>(url) {
            this.method = method
            val header = headerBlock.invoke(this)
            val body = bodyBlock.invoke(this)
            encodedPath = this.url.encodedPath
            Napier.i("Request Twitter API-> ${method.value}:$encodedPath, header = $header, body=$body")
        }.also {
            Napier.i("Response Twitter API-> ${method.value}:$encodedPath, body=$it")
        }
    }, onError)

    protected suspend inline fun <reified R> runCatchingResponse(
        requestBlock: () -> R,
        noinline onError: (Throwable) -> R
    ): R = runCatching(requestBlock).getOrElse {
        Napier.d("stackTraceToString: " + it.stackTraceToString(), it)
        if (it is ClientRequestException) {
            json.decodeFromString(it.response.readText())
        } else {
            onError.invoke(it)
        }
    }
}
