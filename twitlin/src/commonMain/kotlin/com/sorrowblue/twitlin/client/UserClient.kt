/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.http.HttpMethod

internal class UserClient(
    apiKey: String,
    secretKey: String,
    var accessToken: AccessToken? = null
) : AbstractClient(apiKey, secretKey) {

    suspend inline fun <reified T : Any> get(url: String, vararg params: UrlParams): Response<T> =
        request(HttpMethod.Get, url.combineParams(params), params)

    suspend inline fun <reified T : Any> post(
        url: String,
        vararg params: UrlParams = emptyArray(),
        oauthToken: String? = null
    ): Response<T> =
        request(HttpMethod.Post, url, params, oauthToken?.let { AccessToken(it, "", "", "") }) {
            bodyFormUrlEncoded(params.notNullParams)
        }

    suspend inline fun <reified T : Any> delete(
        url: String,
        vararg params: UrlParams = emptyArray()
    ): Response<T> =
        request(HttpMethod.Delete, url.combineParams(params), params)

    suspend inline fun <reified T : Any, reified V : Any> postJson(
        url: String,
        vararg params: UrlParams = emptyArray(),
        clazz: V
    ): Response<T> = request(HttpMethod.Post, url.combineParams(params), params) { bodyJson(clazz) }

    suspend inline fun <reified T : Any, reified V : Any> putJson(
        url: String,
        vararg params: UrlParams = emptyArray(),
        clazz: V
    ): Response<T> = request(HttpMethod.Put, url.combineParams(params), params) { bodyJson(clazz) }

    private suspend inline fun <reified T : Any> request(
        method: HttpMethod,
        url: String,
        params: Array<out Pair<String, Any?>>,
        accessToken: AccessToken? = null,
        noinline block: (HttpRequestBuilder.() -> String)? = null
    ): Response<T> = runCatchingResponse {
        httpClient.request<Response<T>>(url) {
            this.method = method
            headerAuthorization(
                apiKey,
                secretKey,
                params.notNullParams,
                accessToken ?: this@UserClient.accessToken
            )
            val body = block?.invoke(this)
            Napier.i(
                "Request Twitter API-> ${method.value}:${this.url.encodedPath}, body=${body}",
                tag = "Twitlin"
            )
        }.also {
            Napier.i(
                "Response Twitter API-> ${method.value}:${url.combineParams(params)}, response=$it",
                tag = "Twitlin"
            )
        }
    }
}
