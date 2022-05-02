package com.sorrowblue.twitlin.api.v2.client

import com.sorrowblue.twitlin.api.v2.oauth2.OAuth2Token
import com.sorrowblue.twitlin.core.TwitlinConfig
import com.sorrowblue.twitlin.core.client.RateLimitInfo
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.client.TwitterResponse
import com.sorrowblue.twitlin.core.clientEngineFactory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.prepareGet
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsChannel
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.content.PartData
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

public class Oauth2Client(internal val clientId: String, private val oauth2Token: OAuth2Token? = null) : TwitterClient {

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val httpClient: HttpClient
        get() = HttpClient(clientEngineFactory) {
            expectSuccess = false
            defaultRequest {
                oauth2Token?.let {
                    header(HttpHeaders.Authorization, "Bearer ${oauth2Token.accessToken}")
                }
            }
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
        return httpClient.get(url) { block.invoke(this) }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> post(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.post(url) { block.invoke(this) }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> submitForm(
        url: String,
        serializer: KSerializer<T>,
        parameters: Parameters,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.submitForm(url, parameters) { block.invoke(this) }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> submitFormWithBinaryData(
        url: String,
        serializer: KSerializer<T>,
        formBuilder: List<PartData>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.submitFormWithBinaryData(url, formBuilder) { block.invoke(this) }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> delete(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.delete(url) { block.invoke(this) }.transform(serializer)
    }

    override suspend fun <T : TwitterResponse> put(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): T {
        return httpClient.put(url) { block.invoke(this) }.transform(serializer)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : TwitterResponse> streaming(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): Flow<T> {
        return channelFlow {
            httpClient.prepareGet(url) { block.invoke(this) }.execute { response ->
                val channel = response.bodyAsChannel()
                while (!isClosedForSend && !channel.isClosedForRead) {
                    channel.readUTF8Line()?.let {
                        json.decodeFromString(serializer, it)
                    }?.also {
                        it.rateLimitInfo = RateLimitInfo.fromHeaders(response.headers)
                        this.send(it)
                    }
                }
            }
        }
    }

    private suspend fun <T : TwitterResponse> HttpResponse.transform(serializer: KSerializer<T>) =
        json.decodeFromString(serializer, body()).also {
            it.rateLimitInfo = RateLimitInfo.fromHeaders(headers)
        }
}
