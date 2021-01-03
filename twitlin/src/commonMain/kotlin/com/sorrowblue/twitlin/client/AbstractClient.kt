/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.formUrlEncode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal typealias UrlParams = Pair<String, Any?>

internal expect val clientEngineFactory: HttpClientEngineFactory<HttpClientEngineConfig>

public abstract class AbstractClient(
    public val apiKey: String,
    public val secretKey: String
) {

    protected val json: Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    public val httpClient: HttpClient
        get() = HttpClient(clientEngineFactory) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
                accept(ContentType.Text.Html)
            }
        }

    protected suspend inline fun <T, reified R : Response<T>> runCatchingResponse(block: () -> R): R {
        return runCatching(block).getOrElse {
            Napier.d("stackTraceToString: " + it.stackTraceToString(), it, "APPAPP")
            if (it is ClientRequestException) {
                Napier.d("body: " + it.response.readText(), it, "APPAPP")
                json.decodeFromString(it.response.readText())
            } else {
                Response.Error<T>(listOf(Error("${it.message}", ErrorCodes.CLIENT_ERROR))) as R
            }
        }
    }
}

public suspend inline fun <T, reified R : Response<T>> runCatchingResponse(block: () -> R): R {
    return runCatching(block).getOrElse {
        Napier.d("stackTraceToString: " + it.stackTraceToString(), it, "DEBUG")
        if (it is ClientRequestException) {
            Napier.d("body: " + it.response.readText(), it, "DEBUG")
            runCatching {
                Json.decodeFromString<R>(it.response.readText())
            }.getOrElse {
                Response.Error<T>(listOf(Error("${it.message}", ErrorCodes.CLIENT_ERROR))) as R
            }
        } else {
            Response.Error<T>(listOf(Error("${it.message}", ErrorCodes.CLIENT_ERROR))) as R
        }
    }
}
internal fun String.combineParams(params: Array<out Pair<String, Any?>>): String =
    if (params.isEmpty()) this else this + "?" + params.notNullParams.formUrlEncode()

private val errorPair = listOf("java.net.UnknownHostException" to ErrorCodes.NO_NETWORK)

