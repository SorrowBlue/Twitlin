/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.client.UrlParams
import com.sorrowblue.twitlin.client.bodyFormUrlEncoded
import com.sorrowblue.twitlin.client.bodyJson
import com.sorrowblue.twitlin.client.headerAuthorization
import com.sorrowblue.twitlin.client.notNullParams
import io.ktor.client.features.ClientRequestException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.HttpStatement
import io.ktor.client.statement.readText
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.decodeFromString

internal open class AppClient(
    apiKey: String,
    secretKey: String,
    var bearerToken: BearerToken? = null
) : AbstractClient(apiKey, secretKey) {

    suspend inline fun <reified T : Any> get(url: String, vararg params: UrlParams): Response<T> =
        request(HttpMethod.Get, url.combineParams(params))

    suspend inline fun <reified T : Any> post(url: String, vararg params: UrlParams): Response<T> =
        request(HttpMethod.Post, url) { bodyFormUrlEncoded(params.notNullParams) }

    suspend inline fun <reified T : Any, reified V : Any> postJson(
        url: String,
        clazz: V,
        vararg params: UrlParams
    ): Response<T> = request(HttpMethod.Post, url.combineParams(params)) { bodyJson(clazz) }

    suspend inline fun <reified T : Any, reified V : Any> putJson(
        url: String,
        clazz: V,
        vararg params: UrlParams
    ): Response<T> = request(HttpMethod.Put, url.combineParams(params)) { bodyJson(clazz) }

    @OptIn(ExperimentalCoroutinesApi::class)
    inline fun <reified T : Any, reified R : Response<T>> getStreaming(
        url: String,
        vararg params: UrlParams
    ): Flow<R> = channelFlow {
        lateinit var encodedPath: String
        httpClient.get<HttpStatement>(url.combineParams(params)) {
            encodedPath = this.url.buildString()
            val header = headerAuthorization(bearerToken)
            Napier.i("Request Twitter API-> GET:$encodedPath, header = $header, body=$body")
        }.execute { response ->
            Napier.i("status = ${response.status.isSuccess()}")
            do {
                Napier.i("status = ${response.status.isSuccess()}")
                if (response.status.isSuccess()) {
                    val body = response.content.readUTF8Line()
                    Napier.i("Response Twitter API-> GET:$encodedPath, body=$body")
                    json.decodeFromString<R>(body!!).let(channel::offer)
                } else {
                    Response.Error<T>(Error(response.status.description))
                }
            } while (isClosedForSend.not())
        }
    }.catch {
        Napier.d("stackTraceToString: " + it.stackTraceToString(), it)
        if (it is ClientRequestException) {
            kotlin.runCatching {
                this.emit(json.decodeFromString(it.response.readText()))
            }.getOrElse {
                emit(
                    Response.Error<T>(
                        Error(
                            "Twitter Client was unable to process the response.",
                            message = it.message
                        )
                    ) as R
                )
            }
        } else {
            emit(
                Response.Error<T>(
                    Error(
                        "Twitter Client was unable to process the response.",
                        message = it.message
                    )
                ) as R
            )
        }
    }

    private suspend inline fun <reified T : Any> request(
        method: HttpMethod,
        url: String,
        noinline bodyBlock: (HttpRequestBuilder.() -> String)? = null
    ): Response<T> = request(
        method,
        url,
        { headerAuthorization(bearerToken) },
        bodyBlock ?: { "" }
    )
}
