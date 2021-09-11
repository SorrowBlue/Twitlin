package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.core.clientEngineFactory
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpStatement
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

public interface TwitterClient {

    public suspend fun <T> get(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit = {}): T

    public suspend fun <T> post(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit = {}): T

    public suspend fun <T> delete(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit = {}): T

    public suspend fun <T> submitForm(
        url: String,
        serializer: KSerializer<T>,
        formParameters: Parameters = Parameters.Empty
    ): T

    public fun <T> streaming(
        url: String,
        serializer: KSerializer<T>,
        block: HttpRequestBuilder.() -> Unit = {}
    ): Flow<T>
}

public data class ConsumerKeys(val key: String, val secret: String)

public class OAuth2Client(internal val clientId: String, private val accessToken: String? = null) : TwitterClient {

    private val json: Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val httpClient: HttpClient = HttpClient(clientEngineFactory) {
        accessToken?.let {
            defaultRequest {
                header(HttpHeaders.Authorization, "Bearer $it")
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }

    public override suspend fun <T> submitForm(url: String, serializer: KSerializer<T>, formParameters: Parameters): T =
        httpClient.submitForm<String>(url, formParameters = formParameters).let {
            json.decodeFromString(serializer, it)
        }

    override suspend fun <T> get(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit): T =
        httpClient.get<String>(url) { block.invoke(this) }.let {
            json.decodeFromString(serializer, it)
        }

    override suspend fun <T> post(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit): T =
        httpClient.post<String>(url) { block.invoke(this) }.let {
            json.decodeFromString(serializer, it)
        }

    override suspend fun <T> delete(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit): T =
        httpClient.delete<String>(url) { block.invoke(this) }.let {
            json.decodeFromString(serializer, it)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T> streaming(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit): Flow<T> =
        callbackFlow {
            httpClient.get<HttpStatement>(url) { block.invoke(this) }.execute { response ->
                do {
                    val body = response.content.readUTF8Line()!!
                    json.decodeFromString(serializer, body).let { trySend(it).isSuccess }
                } while (isClosedForSend.not())
            }
        }

}
