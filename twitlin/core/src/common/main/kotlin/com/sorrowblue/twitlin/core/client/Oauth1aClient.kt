package com.sorrowblue.twitlin.core.client

import com.sorrowblue.twitlin.core.Twitlin
import com.sorrowblue.twitlin.core.authentication.AccessToken
import com.sorrowblue.twitlin.core.clientEngineFactory
import com.sorrowblue.twitlin.core.ktx.headerAuthorization
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpStatement
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.content.PartData
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
@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
public open class Oauth1aClient(private val consumerKeys: ConsumerKeys, private val accessToken: AccessToken?) :
    TwitterClient {

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val httpClient: HttpClient
        get() = HttpClient(clientEngineFactory) {
            expectSuccess = false
            defaultRequest {
                if (headers[HttpHeaders.Authorization].isNullOrEmpty()) {
                    headerAuthorization(consumerKeys, accessToken)
                }
            }
            install(Logging) {
                logger = Twitlin.clientLogger
                level = Twitlin.clientLogLevel
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
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
        val param = parameters.toMap().map { it.key to it.value.first() }
        return decodeFromResponse(
            serializer,
            httpClient.submitForm(url, parameters) {
                block.invoke(this)
                headerAuthorization(consumerKeys, accessToken, param)
            }
        )
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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : TwitterResponse> streaming(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit
    ): Flow<T> {
        return channelFlow {
            httpClient.get<HttpStatement>(url) { block.invoke(this) }.execute { response ->
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
        json.decodeFromString(serializer, response.receive()).also {
            it.rateLimitInfo = RateLimitInfo.fromHeaders(response.headers)
        }
}
