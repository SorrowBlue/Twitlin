/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.client.UrlParams
import com.sorrowblue.twitlin.client.bodyFormUrlEncoded
import com.sorrowblue.twitlin.client.bodyJson
import com.sorrowblue.twitlin.client.headerAuthorization
import com.sorrowblue.twitlin.client.notNullParams
import com.sorrowblue.twitlin.core.IResponse
import io.ktor.client.features.ClientRequestException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.KSerializer

internal open class UserClient(
    apiKey: String,
    secretKey: String,
    var accessToken: AccessToken? = null
) : AbstractClient(apiKey, secretKey) {

    override suspend fun <T : Any, R : IResponse<T>> get(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R = request(HttpMethod.Get, url.combineParams(params), params, serializer)

    override suspend fun <T : Any, R : IResponse<T>> post(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R = request(HttpMethod.Post, url, params, serializer) {
        bodyFormUrlEncoded(params.notNullParams)
    }

    override suspend fun <T : Any, R : IResponse<T>> delete(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R {
        TODO("Not yet implemented")
    }

    override suspend fun <T : Any, R : IResponse<T>> put(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R {
        TODO("Not yet implemented")
    }

    suspend inline fun <reified V : Any, reified T : Any> postJson(
        url: String,
        clazz: V,
        vararg params: UrlParams,
        serializer: KSerializer<Response<T>>
    ): Response<T> =
        request(HttpMethod.Post, url.combineParams(params), params, serializer) { bodyJson(clazz) }

    suspend inline fun <reified T : Any, reified V : Any> putJson(
        url: String,
        serializer: KSerializer<Response<T>>,
        clazz: V,
        vararg params: UrlParams
    ): Response<T> =
        request(HttpMethod.Put, url.combineParams(params), params, serializer) { bodyJson(clazz) }

    @Suppress("UNCHECKED_CAST")
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : Any, R : IResponse<T>> streaming(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): Flow<R> = channelFlow {
        httpClient.get<HttpStatement>(url.combineParams(params)) {
            headerAuthorization(apiKey, secretKey, params.notNullParams, accessToken)
            val header = headers.entries().joinToString(", ") { it.key + ": " + it.value }
            Napier.i("Request Twitter API-> GET:$url, header = $header, body =${this.body}")
        }.execute { response ->
            do {
                val body = response.readText()
                Napier.i("Response Twitter API-> GET:$url, body=$body")
                json.decodeFromString(serializer, body).let(channel::offer)
            } while (isClosedForSend.not())
        }
    }.catch {
        Napier.d("stackTraceToString: " + it.stackTraceToString(), it)
        val response: R = if (it is ClientRequestException) {
            kotlin.runCatching {
                json.decodeFromString(serializer, it.response.readText())
            }.getOrElse {
                Response.Error<T>(clientError(it.message)) as R
            }
        } else {
            Response.Error<T>(clientError(it.message)) as R
        }
        emit(response)
    }

    private suspend inline fun <T : Any, R : IResponse<T>> request(
        method: HttpMethod,
        url: String,
        params: Array<out Pair<String, Any?>>,
        serializer: KSerializer<R>,
        noinline bodyBlock: HttpRequestBuilder.() -> Unit = {}
    ): R {
        return request(
            method,
            url,
            serializer,
            { headerAuthorization(apiKey, secretKey, params.notNullParams, accessToken) },
            bodyBlock
        )
    }
}
