/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.sorrowblue.twitlin.core.IResponse
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer

internal typealias UrlParams = Pair<String, Any?>

internal expect val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>

public abstract class AbstractClient(apiKey: String, secretKey: String) :
    TwitterClient(apiKey, secretKey) {
    protected suspend inline fun <T : Any, R : IResponse<T>> request(
        method: HttpMethod,
        url: String,
        serializer: KSerializer<R>,
        noinline headerBlock: HttpRequestBuilder.() -> Unit,
        noinline bodyBlock: HttpRequestBuilder.() -> Unit
    ): R = baseRequest(method, url, serializer, headerBlock, bodyBlock)

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any, R : IResponse<T>> onError(throwable: Throwable): R =
        Response.error<T>(Error("${throwable.message}", ErrorCodes.CLIENT_ERROR)) as R
}
