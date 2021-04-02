/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.core.IResponse
import com.sorrowblue.twitlin.core.UrlParams
import com.sorrowblue.twitlin.core.bodyFormUrlEncoded
import com.sorrowblue.twitlin.core.bodyJson
import com.sorrowblue.twitlin.core.headerAuthorization
import com.sorrowblue.twitlin.core.notNullParams
import io.ktor.client.features.ClientRequestException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.isActive
import kotlinx.serialization.KSerializer

internal open class AppClient(
    apiKey: String,
    secretKey: String,
    var bearerToken: BearerToken? = null
) : AbstractClient(apiKey, secretKey) {

    override suspend fun <T : Any, R : IResponse<T>> delete(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R = request(HttpMethod.Delete, url.combineParams(params), serializer)

    override suspend fun <T : Any, R : IResponse<T>> get(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R = request(HttpMethod.Get, url.combineParams(params), serializer)

    override suspend fun <T : Any, R : IResponse<T>> post(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R = request(HttpMethod.Post, url, serializer) {
        bodyFormUrlEncoded(params.notNullParams)
    }

    override suspend fun <T : Any, R : IResponse<T>> put(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R = request(HttpMethod.Post, url, serializer) {
        bodyFormUrlEncoded(params.notNullParams)
    }

    suspend inline fun <T : Any, R : Response<T>, reified V : Any> postJson(
        url: String,
        serializer: KSerializer<R>,
        clazz: V,
        vararg params: UrlParams
    ): R = request(HttpMethod.Post, url.combineParams(params), serializer) { bodyJson(clazz) }

    @Suppress("UNCHECKED_CAST")
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : Any, R : IResponse<T>> streaming(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): Flow<R> = callbackFlow {
        httpClient.get<HttpStatement>(url.combineParams(params)) {
            headerAuthorization(bearerToken)
            val header = headers.entries().joinToString(", ") { it.key + ": " + it.value }
            Napier.i("Request Twitter API-> GET:$url, header = $header, body =${this.body}")
        }.execute { response ->
            do {
                val body = response.content.readUTF8Line()!!
                Napier.i("Response Twitter API-> GET:$url, body=$body")
                json.decodeFromString(serializer, body).let(this::offer)
                Napier.i("isClosedForSend=$isClosedForSend, isActive=$isActive")
                if (isClosedForSend || isActive.not()) {
                    Napier.i("close streaming")
                }
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
        serializer: KSerializer<R>,
        noinline bodyBlock: HttpRequestBuilder.() -> Unit = {}
    ): R = request(
        method,
        url,
        serializer,
        { headerAuthorization(bearerToken) },
        bodyBlock
    )
}
