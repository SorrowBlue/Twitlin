/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.utils.urlEncode
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.content.TextContent
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64

internal class AppClient(
    apiKey: String,
    secretKey: String,
    var bearerToken: BearerToken? = null
) : AbstractClient(apiKey, secretKey) {

    suspend inline fun <reified T : Any> get(url: String, vararg params: UrlParams): Response<T> =
        request(HttpMethod.Get, url.combineParams(params))

    suspend inline fun <reified T : Any> post(url: String, vararg params: UrlParams): Response<T> =
        request(HttpMethod.Post, url) { bodyFormUrlEncoded(params.notNullParams) }

    suspend inline fun <reified T : Any> delete(
        url: String,
        vararg params: UrlParams
    ): Response<T> = request(HttpMethod.Delete, url.combineParams(params))

    @OptIn(InternalAPI::class)
    suspend inline fun <reified T : Any> postForClientCredentials(url: String): Response<T> =
        request(HttpMethod.Post, url, {
            val token = "${apiKey.urlEncode()}:${secretKey.urlEncode()}".encodeBase64()
            header(HttpHeaders.Authorization, "Basic $token")
            "${HttpHeaders.Authorization}: ${headers[HttpHeaders.Authorization]}"
        }, {
            TextContent(
                "grant_type=client_credentials",
                ContentType.Application.FormUrlEncoded
            ).also { body = it }.toString()
        })

    suspend inline fun <reified T : Any> request(
        method: HttpMethod,
        url: String,
        noinline block: (HttpRequestBuilder.() -> String)? = null
    ): Response<T> = request(method, url, { headerAuthorization(bearerToken) }, block)
}
