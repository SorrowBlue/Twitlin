/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.core.IResponse
import com.sorrowblue.twitlin.core.UrlParams
import com.sorrowblue.twitlin.core.bodyFormUrlEncoded
import com.sorrowblue.twitlin.core.headerAuthorization
import com.sorrowblue.twitlin.core.notNullParams
import com.sorrowblue.twitlin.utils.urlEncode
import io.ktor.client.features.ClientRequestException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.content.TextContent
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.KSerializer
import mu.KotlinLogging

internal class AppClient(
    apiKey: String,
    secretKey: String,
    var bearerToken: BearerToken? = null
) : AbstractClient(apiKey, secretKey) {

    override val logger = KotlinLogging.logger("com.sorrowblue.twitlin.client.AppClient")

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
    ): R = request(HttpMethod.Post, url, serializer) { bodyFormUrlEncoded(params.notNullParams) }

    override suspend fun <T : Any, R : IResponse<T>> put(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): R = request(HttpMethod.Post, url, serializer) { bodyFormUrlEncoded(params.notNullParams) }

    @Suppress("UNCHECKED_CAST")
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : Any, R : IResponse<T>> streaming(
        url: String,
        serializer: KSerializer<R>,
        vararg params: UrlParams
    ): Flow<R> = channelFlow<R> {
        httpClient.get<HttpStatement>(url.combineParams(params)) {
            headerAuthorization(bearerToken)
            val header = headers.entries().joinToString(", ") { it.key + ": " + it.value }
            logger.info { "Request Twitter API-> GET:$url, header = $header, body =${this.body}" }
        }.execute { response ->
            do {
                val body = response.readText()
                logger.info { "Response Twitter API-> GET:$url, body=$body" }
                json.decodeFromString(serializer, body).let { channel.offer(it) }
            } while (isClosedForSend.not())
        }
    }.catch {
        logger.error(it) { "streaming error" }
        val response: R = if (it is ClientRequestException) {
            kotlin.runCatching {
                json.decodeFromString(serializer, it.response.readText())
            }.getOrElse {
                Response.Error<T>(clientError) as R
            }
        } else {
            Response.Error<T>(clientError) as R
        }
        emit(response)
    }

    private val clientError =
        listOf(Error("Twitter Client was unable to process the response.", 400))

    @OptIn(InternalAPI::class)
    suspend inline fun <T : Any, R : IResponse<T>> postForClientCredentials(
        url: String,
        serializer: KSerializer<R>
    ): R = request(
        HttpMethod.Post,
        url,
        serializer,
        {
            val token = "${apiKey.urlEncode()}:${secretKey.urlEncode()}".encodeBase64()
            header(HttpHeaders.Authorization, "Basic $token")
        },
        {
            body =
                TextContent("grant_type=client_credentials", ContentType.Application.FormUrlEncoded)
        }
    )

    suspend inline fun <T : Any, R : IResponse<T>> request(
        method: HttpMethod,
        url: String,
        serializer: KSerializer<R>,
        noinline block: HttpRequestBuilder.() -> Unit = {}
    ): R = request(method, url, serializer, { headerAuthorization(bearerToken) }, block)
}
