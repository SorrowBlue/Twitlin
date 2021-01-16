/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpMethod

internal typealias UrlParams = Pair<String, Any?>

internal expect val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>

public abstract class AbstractClient(apiKey: String, secretKey: String) :
    TwitterClient(apiKey, secretKey) {

    private val errorPair = listOf("java.net.UnknownHostException" to ErrorCodes.NO_NETWORK)

    protected suspend inline fun <T : Any, reified R : Response<T>> request(
        method: HttpMethod,
        url: String,
        noinline headerBlock: HttpRequestBuilder.() -> String,
        noinline bodyBlock: (HttpRequestBuilder.() -> String)? = null
    ): R = baseRequest(method, url, headerBlock, bodyBlock ?: { "" }) {
        Response.Error<T>(listOf(Error("${it.message}", ErrorCodes.CLIENT_ERROR))) as R
    }
}

