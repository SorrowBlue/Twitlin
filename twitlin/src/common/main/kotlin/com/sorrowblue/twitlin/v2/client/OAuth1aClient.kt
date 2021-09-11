package com.sorrowblue.twitlin.v2.client

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.core.clientEngineFactory
import com.sorrowblue.twitlin.core.headerAuthorization
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

public class OAuth1aClient(private val consumerKey: ConsumerKeys, private val accessToken: AccessToken? = null) :
    TwitterClient {

    private val json: Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val httpClient: HttpClient
        get () = HttpClient(clientEngineFactory) {
            expectSuccess = false
            defaultRequest {
                println("OAuth1aClient: defaultRequest")
                headerAuthorization(consumerKey, accessToken)
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
                accept(ContentType.Text.Html)
            }
        }
    override suspend fun <T> get(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit): T =
        httpClient.get<String>(url){
            block.invoke(this)
            println("OAuth1aClient: get")
        }.let { json.decodeFromString(serializer, it) }

    override suspend fun <T> post(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit): T {
        TODO("Not yet implemented")
    }

    override suspend fun <T> delete(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit): T {
        TODO("Not yet implemented")
    }

    override suspend fun <T> submitForm(url: String, serializer: KSerializer<T>, formParameters: Parameters): T {
        TODO("Not yet implemented")
    }

    override fun <T> streaming(url: String, serializer: KSerializer<T>, block: HttpRequestBuilder.() -> Unit): Flow<T> {
        TODO("Not yet implemented")
    }

}
