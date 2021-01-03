/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.utils.urlEncode
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.request
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

    suspend fun <T : Any> get(url: String, vararg params: UrlParams = emptyArray()): Response<T> =
        request(HttpMethod.Get, url.combineParams(params), params)

    suspend fun <T : Any> post(
        url: String,
        vararg params: UrlParams = emptyArray()
    ): Response<T> = request(HttpMethod.Post, url, params)

    suspend fun <T : Any> delete(url: String, vararg params: UrlParams): Response<T> =
        request(HttpMethod.Delete, url.combineParams(params), params)

    @OptIn(InternalAPI::class)
    suspend inline fun <reified T : Any> postForClientCredentials(url: String): Response<T> {
        val token = "${apiKey.urlEncode()}:${secretKey.urlEncode()}".encodeBase64()
        return runCatchingResponse {
            httpClient.post<Response<T>>(url) {
                header(HttpHeaders.Authorization, "Basic $token")
                body = TextContent(
                    "grant_type=client_credentials",
                    ContentType.Application.FormUrlEncoded
                )
                Napier.i(
                    "Request Twitter API... ${method.value}:${this.url.encodedPath}, body=${body}",
                    tag = "Twitlin"
                )
            }.also {
                Napier.i(
                    "Response Twitter API... POST:$url, response=$it",
                    tag = "Twitlin"
                )
            }
        }
    }

    suspend fun <T : Any> request(
        method: HttpMethod,
        url: String,
        params: Array<out Pair<String, Any?>> = emptyArray(),
        bearerToken: BearerToken? = null,
        block: Any? = null
    ): Response<T> = runCatchingResponse {
        httpClient.request<Response<T>>(url) {
            headerAuthorization(bearerToken)
            block?.let { body = it }
            Napier.i(
                "Request Twitter API... $method:${this.url.encodedPath}, body=${body}",
                tag = "Twitlin"
            )
        }.also {
            Napier.i(
                "Response Twitter API... $method:${url.combineParams(params)}, response=$it",
                tag = "Twitlin"
            )
        }
    }
}
