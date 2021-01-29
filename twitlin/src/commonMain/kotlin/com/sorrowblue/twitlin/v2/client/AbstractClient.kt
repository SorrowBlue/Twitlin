/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.core.IResponse
import com.sorrowblue.twitlin.core.TwitterClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer

public abstract class AbstractClient(apiKey: String, secretKey: String) :
    TwitterClient(apiKey, secretKey) {
    protected fun clientError(message: String?): List<Error> =
        listOf(Error("Twitter Client was unable to process the response.", message))

    protected suspend inline fun <T : Any, R : IResponse<T>> request(
        method: HttpMethod,
        url: String,
        serializer: KSerializer<R>,
        noinline headerBlock: HttpRequestBuilder.() -> Unit,
        noinline bodyBlock: HttpRequestBuilder.() -> Unit
    ): R = baseRequest(method, url, serializer, headerBlock, bodyBlock)

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any, R : IResponse<T>> onError(throwable: Throwable): R =
        Response.Error<T>(Error("${throwable.message}")) as R
}
