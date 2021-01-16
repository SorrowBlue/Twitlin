/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.client.ErrorCodes
import com.sorrowblue.twitlin.client.TwitterClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpMethod

public abstract class AbstractClient(apiKey: String, secretKey: String) :
    TwitterClient(apiKey, secretKey) {


    private val errorPair = listOf("java.net.UnknownHostException" to ErrorCodes.NO_NETWORK)

    protected suspend inline fun <reified T : Any, reified R : Response<T>> request(
        method: HttpMethod,
        url: String,
        noinline headerBlock: HttpRequestBuilder.() -> String,
        noinline bodyBlock: HttpRequestBuilder.() -> String
    ): R = baseRequest(method, url, headerBlock, bodyBlock) {
        Napier.i("baseRequest error ${it.message}")
        Response.Error<T>(Error("${it.message}")) as R
    }
}
