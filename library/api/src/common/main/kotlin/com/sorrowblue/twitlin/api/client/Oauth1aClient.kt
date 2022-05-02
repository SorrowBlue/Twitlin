package com.sorrowblue.twitlin.api.client

import com.sorrowblue.twitlin.api.ktx.headerAuthorization
import com.sorrowblue.twitlin.api.oauth.AccessToken
import com.sorrowblue.twitlin.core.TwitlinConfig
import com.sorrowblue.twitlin.core.client.RateLimitInfo
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.client.TwitterResponse
import com.sorrowblue.twitlin.core.clientEngineFactory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.prepareGet
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.content.PartData
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.util.InternalAPI
import io.ktor.util.toMap
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Oauth1a client
 *
 * @property consumerKeys
 * @property accessToken
 */
public open class Oauth1aClient(private val consumerKeys: ConsumerKeys, private val accessToken: AccessToken?) :
    TwitterClient {

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val httpClient: HttpClient
        get() = HttpClient(clientEngineFactory) {
            expectSuccess = false
            install(Logging) {
                logger = TwitlinConfig.clientLogger
                level = TwitlinConfig.clientLogLevel
            }
            install(ContentNegotiation) {
                register(ContentType.Application.Json, KotlinxSerializationConverter(json))
            }
        }

    override suspend fun <T : TwitterResponse> get(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.get(url) {
            block.invoke(this)
            headerAuthorization(HttpMethod.Get, consumerKeys, accessToken)
        }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> post(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.post(url) {
            block.invoke(this)
            headerAuthorization(HttpMethod.Post, consumerKeys, accessToken)
        }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> submitForm(
        url: String,
        serializer: KSerializer<T>,
        parameters: Parameters,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        val param = parameters.toMap().map { it.key to it.value.first() }
        return httpClient.submitForm(url, parameters) {
            block.invoke(this)
            headerAuthorization(HttpMethod.Post, consumerKeys, accessToken, param)
        }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> submitFormWithBinaryData(
        url: String,
        serializer: KSerializer<T>,
        formBuilder: List<PartData>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.submitFormWithBinaryData(url, formBuilder) {
            block.invoke(this)
            headerAuthorization(HttpMethod.Post, consumerKeys, accessToken)
        }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> delete(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.delete(url) {
            block.invoke(this)
            headerAuthorization(HttpMethod.Delete, consumerKeys, accessToken)
        }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> put(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.put(url) {
            block.invoke(this)
            headerAuthorization(HttpMethod.Put, consumerKeys, accessToken)
        }.transform(serializer)
    }

    @OptIn(ExperimentalCoroutinesApi::class, InternalAPI::class)
    override fun <T : TwitterResponse> streaming(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): Flow<T> {
        return channelFlow {
            httpClient.prepareGet(url) {
                block.invoke(this)
                headerAuthorization(HttpMethod.Get, consumerKeys, accessToken)
            }.execute { response ->
                do {
                    response.content.readUTF8Line()?.let {
                        json.decodeFromString(serializer, it)
                    }?.also {
                        it.rateLimitInfo = RateLimitInfo.fromHeaders(response.headers)
                        trySend(it).isSuccess
                    } ?: close()
                } while (isClosedForSend.not())
            }
        }
    }

    private suspend fun <T : TwitterResponse> HttpResponse.transform(serializer: KSerializer<T>) =
        json.decodeFromString(serializer, body()).also {
            it.rateLimitInfo = RateLimitInfo.fromHeaders(headers)
        }
}
