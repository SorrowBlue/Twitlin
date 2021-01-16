/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.post
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.decodeFromString

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
        request(
            HttpMethod.Post,
            url,
            params,
            accessToken = oauthToken?.let { AccessToken(it, "", "", "") }) {
            bodyFormUrlEncoded(params.notNullParams)
        }

    suspend inline fun <reified T : Any> delete(
        url: String,
        vararg params: UrlParams = emptyArray()
    ): Response<T> =
        request(HttpMethod.Delete, url.combineParams(params), params)

    suspend inline fun <reified T : Any, reified V : Any> postJson(
        url: String,
        clazz: V,
        vararg params: UrlParams
    ): Response<T> =
        request(HttpMethod.Post, url.combineParams(params), params) { bodyJson(clazz) }

    suspend inline fun <reified T : Any, reified V : Any> putJson(
        url: String,
        vararg params: UrlParams = emptyArray(),
        clazz: V
    ): Response<T> =
        request(HttpMethod.Put, url.combineParams(params), params) { bodyJson(clazz) }


    @OptIn(ExperimentalCoroutinesApi::class)
    inline fun <reified T : Any> streaming(
        url: String,
        vararg params: UrlParams
    ): Flow<Response<T>> = channelFlow {
        httpClient.post<HttpStatement>(url) {
            headerAuthorization(apiKey, secretKey, params.notNullParams, accessToken)
            bodyFormUrlEncoded(params.notNullParams)
        }.execute { response ->
            do {
                val body = response.readText()
                Napier.i(
                    "Request Twitter API-> GET:$url, body=${body}",
                    tag = "Twitlin"
                )
                json.decodeFromString<Response<T>>(body).let(channel::offer)
            } while (isClosedForSend.not())
        }
    }

    private suspend inline fun <reified T : Any> request(
        method: HttpMethod,
        url: String,
        params: Array<out Pair<String, Any?>>,
        accessToken: AccessToken? = null,
        noinline bodyBlock: (HttpRequestBuilder.() -> String)? = null
    ): Response<T> = request(method, url, {
        headerAuthorization(
            apiKey,
            secretKey,
            params.notNullParams,
            accessToken ?: this@UserClient.accessToken
        )
    }, bodyBlock)
}
