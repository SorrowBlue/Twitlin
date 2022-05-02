package com.sorrowblue.twitlin.api.client

import com.sorrowblue.twitlin.api.oauth2.BearerToken
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
import io.ktor.http.Parameters
import io.ktor.http.content.PartData
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.util.InternalAPI
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

/**
 * Oauth2client
 *
 * @property consumerKeys
 * @property bearerToken
 */
public class Oauth2Client(internal val consumerKeys: ConsumerKeys, internal val bearerToken: BearerToken? = null) :
    TwitterClient {

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val httpClient: HttpClient
        get() = HttpClient(clientEngineFactory) {
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
        return decodeFromResponse(serializer, httpClient.get(url) { block.invoke(this) })
    }

    override suspend fun <T : TwitterResponse> post(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return decodeFromResponse(serializer, httpClient.post(url) { block.invoke(this) })
    }

    override suspend fun <T : TwitterResponse> submitForm(
        url: String,
        serializer: KSerializer<T>,
        parameters: Parameters,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return decodeFromResponse(serializer, httpClient.submitForm(url, parameters) { block.invoke(this) })
    }

    override suspend fun <T : TwitterResponse> submitFormWithBinaryData(
        url: String,
        serializer: KSerializer<T>,
        formBuilder: List<PartData>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return decodeFromResponse(
            serializer,
            httpClient.submitFormWithBinaryData(url, formBuilder) { block.invoke(this) }
        )
    }

    override suspend fun <T : TwitterResponse> delete(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return decodeFromResponse(serializer, httpClient.delete(url) { block.invoke(this) })
    }

    override suspend fun <T : TwitterResponse> put(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return decodeFromResponse(serializer, httpClient.put(url) { block.invoke(this) })
    }

    @OptIn(ExperimentalCoroutinesApi::class, InternalAPI::class)
    override fun <T : TwitterResponse> streaming(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): Flow<T> {
        return channelFlow {
            httpClient.prepareGet(url) { block.invoke(this) }.execute { response ->
                do {
                    val body = response.content.readUTF8Line()!!
                    json.decodeFromString(serializer, body).let {
                        it.rateLimitInfo = RateLimitInfo.fromHeaders(response.headers)
                        trySend(it).isSuccess
                    }
                } while (isClosedForSend.not())
            }
        }
    }

    private suspend fun <T : TwitterResponse> decodeFromResponse(serializer: KSerializer<T>, response: HttpResponse) =
        json.decodeFromString(serializer, response.body()).also {
            it.rateLimitInfo = RateLimitInfo.fromHeaders(response.headers)
        }
}
