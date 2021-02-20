/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.core

import com.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.formUrlEncode
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer
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

    public abstract suspend fun <T : Any, R : IResponse<T>> delete(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R

    public abstract suspend fun <T : Any, R : IResponse<T>> get(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R

    public abstract suspend fun <T : Any, R : IResponse<T>> post(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R

    public abstract suspend fun <T : Any, R : IResponse<T>> put(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R

    public abstract fun <T : Any, R : IResponse<T>> streaming(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): Flow<R>

    protected abstract fun <T : Any, R : IResponse<T>> onError(throwable: Throwable): R

    protected fun String.combineParams(params: Array<out Pair<String, Any?>>): String =
        params.notNullParams.let { if (it.isEmpty()) this else this + "?" + it.formUrlEncode() }

    protected suspend inline fun <T : Any, R : IResponse<T>> baseRequest(
        method: HttpMethod,
        url: String,
        serializer: KSerializer<R>,
        noinline headerBlock: HttpRequestBuilder.() -> Unit,
        noinline bodyBlock: HttpRequestBuilder.() -> Unit
    ): R = runCatchingResponse(serializer) {
        httpClient.get<String>(url) {
            this.method = method
            headerBlock.invoke(this)
            bodyBlock.invoke(this)
            val header = headers.entries().joinToString(", ") { it.key + ": " + it.value }
            Napier.i("Request Twitter API-> ${method.value}:$url, header = $header, body =${this.body}")
        }.let {
            Napier.i("Response Twitter API-> ${method.value}:$url, body=$it")
            json.decodeFromString(serializer, it)
        }
    }

    protected suspend inline fun <T : Any, R : IResponse<T>> runCatchingResponse(
        serializer: KSerializer<R>,
        requestBlock: () -> R
    ): R {
        return runCatching(requestBlock).getOrElse {
            Napier.d("stackTraceToString: " + it.stackTraceToString(), it)
            if (it is ClientRequestException) {
                json.decodeFromString(serializer, it.response.readText())
            } else {
                onError(it)
            }
        }
    }
}
