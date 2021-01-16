/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.client.UrlParams
import com.sorrowblue.twitlin.client.bodyFormUrlEncoded
import com.sorrowblue.twitlin.client.bodyJson
import com.sorrowblue.twitlin.client.headerAuthorization
import com.sorrowblue.twitlin.client.notNullParams
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.decodeFromString

internal open class UserClient(
    apiKey: String,
    secretKey: String,
    var accessToken: AccessToken? = null
) : AbstractClient(apiKey, secretKey) {

    suspend inline fun <reified T : Any> get(url: String, vararg params: UrlParams): Response<T> =
        request(HttpMethod.Get, url.combineParams(params), params)

    suspend inline fun <reified T : Response<R>, R : Any> testGet(
        url: String,
        vararg params: UrlParams,
        serializer: DeserializationStrategy<T>
    ): T {
        return runCatchingResponse({
            lateinit var encodedPath: String
            httpClient.get<String>(url.combineParams(params)) {
                headerAuthorization(apiKey, secretKey, params.notNullParams, accessToken)
                encodedPath = this.url.encodedPath
                Napier.i("Request Twitter API-> GET:$encodedPath, body=$body")
            }.also {
                Napier.i("Response Twitter API-> GET:$encodedPath, body=$it")
            }.let {
                json.decodeFromString(serializer, it)
            }
        }, {
            Response.Error<R>(Error("")) as T
        })
    }

    suspend inline fun <reified T : Any> post(url: String, vararg params: UrlParams): Response<T> =
        request(HttpMethod.Post, url, params) { bodyFormUrlEncoded(params.notNullParams) }

    suspend inline fun <reified V : Any, reified T : Any> postJson(
        url: String,
        clazz: V,
        vararg params: UrlParams
    ): Response<T> = request(HttpMethod.Post, url.combineParams(params), params) { bodyJson(clazz) }

    suspend inline fun <reified T : Any, reified V : Any> putJson(
        url: String,
        clazz: V,
        vararg params: UrlParams
    ): Response<T> = request(HttpMethod.Put, url.combineParams(params), params) { bodyJson(clazz) }

    @OptIn(ExperimentalCoroutinesApi::class)
    inline fun <reified T : Any> getStreaming(
        url: String,
        vararg params: UrlParams
    ): Flow<Response<T>> = channelFlow {
        lateinit var encodedPath: String
        httpClient.get<HttpStatement>(url.combineParams(params)) {
            encodedPath = this.url.encodedPath
            val header = headerAuthorization(apiKey, secretKey, params.notNullParams, accessToken)
            Napier.i("Request Twitter API-> GET:$encodedPath, header = $header, body=$body")
        }.execute { response ->
            do {
                val body = response.readText()
                Napier.i("Response Twitter API-> GET:$encodedPath, body=$body")
                json.decodeFromString<Response<T>>(body).let(channel::offer)
            } while (isClosedForSend.not())
        }
    }

    private suspend inline fun <reified T : Any> request(
        method: HttpMethod,
        url: String,
        params: Array<out Pair<String, Any?>>,
        noinline bodyBlock: (HttpRequestBuilder.() -> String)? = null
    ): Response<T> = request(
        method,
        url,
        { headerAuthorization(apiKey, secretKey, params.notNullParams, accessToken) },
        bodyBlock ?: { "" }
    )
}
