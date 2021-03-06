/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.core.IResponse
import com.sorrowblue.twitlin.core.UrlParams
import com.sorrowblue.twitlin.core.bodyFormUrlEncoded
import com.sorrowblue.twitlin.core.bodyJson
import com.sorrowblue.twitlin.core.headerAuthorization
import com.sorrowblue.twitlin.core.notNullParams
import io.ktor.client.features.ClientRequestException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.KSerializer
import mu.KotlinLogging

internal class UserClient(apiKey: String, secretKey: String, var accessToken: AccessToken? = null) :
    AbstractClient(apiKey, secretKey) {

    override val logger = KotlinLogging.logger("UserClient")

    override suspend fun <T : Any, R : IResponse<T>> put(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R = request(HttpMethod.Put, url.combineParams(params), params, serializer)

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
    ): R = request(HttpMethod.Delete, url.combineParams(params), params, serializer)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : Any, R : IResponse<T>> streaming(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): Flow<R> = channelFlow {
        httpClient.post<HttpStatement>(url) {
            headerAuthorization(apiKey, secretKey, params.notNullParams, accessToken)
            bodyFormUrlEncoded(params.notNullParams)
        }.execute { response ->
            do {
                val body = response.readText()
                logger.info { "Response Twitter API-> GET:$url, body=$body" }
                json.decodeFromString(serializer, body).let { channel.offer(it) }
            } while (isClosedForSend.not())
        }
    }

    suspend inline fun <T : Any, R : IResponse<T>, reified V : Any> postJson(
        url: String,
        serializer: KSerializer<R>,
        clazz: V,
        vararg params: UrlParams
    ): R =
        request(HttpMethod.Post, url.combineParams(params), params, serializer) { bodyJson(clazz) }

    suspend inline fun <T : Any, R : IResponse<T>, reified V : Any> putJson(
        url: String,
        serializer: KSerializer<R>,
        clazz: V,
        vararg params: UrlParams
    ): R =
        request(HttpMethod.Put, url.combineParams(params), params, serializer) { bodyJson(clazz) }

    private suspend inline fun <T : Any, R : IResponse<T>> request(
        method: HttpMethod,
        url: String,
        params: Array<out Pair<String, Any?>>,
        serializer: KSerializer<R>,
        accessToken: AccessToken? = null,
        noinline bodyBlock: HttpRequestBuilder.() -> Unit = {}
    ): R = request(
        method,
        url,
        serializer,
        {
            val token = accessToken ?: this@UserClient.accessToken
            headerAuthorization(apiKey, secretKey, params.notNullParams, token)
        },
        bodyBlock
    )

    suspend inline fun postForAuthentication(
        url: String,
        vararg params: UrlParams,
        oauthToken: String? = null
    ): Response<String> = runCatchingResponse2 {
        httpClient.post<String>(url) {
            val token = oauthToken?.let { AccessToken(it, "", "", "") }
            headerAuthorization(apiKey, secretKey, params.notNullParams, token)
            bodyFormUrlEncoded(params.notNullParams)
            val header = headers.entries().joinToString(", ") { it.key + ": " + it.value }
            logger.info { "Request Twitter API-> POST:$url, header = $header, body =${this.body}" }
        }.let {
            logger.info { "Response Twitter API-> POST:$url, body=$it" }
            Response.Success(it)
        }
    }

    private suspend inline fun runCatchingResponse2(requestBlock: () -> Response<String>): Response<String> {
        return runCatching(requestBlock).getOrElse {
            logger.error(it) { "response error" }
            if (it is ClientRequestException) {
                Response.error(Error(it.response.readText(), it.response.status.value))
            } else {
                onError(it)
            }
        }
    }
}
