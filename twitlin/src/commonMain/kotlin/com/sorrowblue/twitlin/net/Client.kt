package com.sorrowblue.twitlin.net

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import com.sorrowblue.twitlin.basics.oauth2.BearerToken
import com.sorrowblue.twitlin.foundation.bodyForTwitter
import com.sorrowblue.twitlin.foundation.combineParams
import com.sorrowblue.twitlin.foundation.headerForTwitter
import com.sorrowblue.twitlin.utils.onSuccess
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal open class Client(
	internal val apiKey: String,
	internal val apiSecretKey: String,
	var accessToken: AccessToken? = null,
) {

	internal var bearerToken: BearerToken? = null

	val json: Json = Json {
		isLenient = true
		ignoreUnknownKeys = true
	}

	val httpClient
		get() = HttpClient(clientEngine) {
			install(JsonFeature) {
				serializer = KotlinxSerializer(json)
			}
		}

	suspend inline fun <reified T> catchResponse(body: () -> HttpResponse): Response<T> =
		kotlin.runCatching {
			body().onSuccess { json.decodeFromString<T>(it) }
		}.getOrElse {
			Napier.e(it.toString(), it, tag = "TwitlinClient")
			if (it.toString().contains("java.net.UnknownHostException"))
				Response.error(ErrorMessages.Error(it.message ?: "unknown error", -100))
			else Response.error(ErrorMessages.Error(it.message ?: "unknown error", -99))
		}

	suspend inline fun <reified T : Any> get(
		url: String,
		vararg params: Pair<String, Any?> = emptyArray(),
		bearerToken: Boolean = false,
	): Response<T> = catchResponse {
		httpClient.get(url.combineParams(params)) {
			headerForTwitter(
				apiKey, apiSecretKey, params,
				accessToken?.oauthToken, accessToken?.oauthTokenSecret,
				if (bearerToken) this@Client.bearerToken else null
			)
		}
	}

	suspend inline fun <reified T : Any> post(
		url: String,
		vararg params: Pair<String, String>,
		oauthToken: String? = null,
		bearerToken: Boolean = false
	): Response<T> = catchResponse {
		httpClient.post(url) {
			headerForTwitter(
				apiKey, apiSecretKey, params,
				oauthToken ?: accessToken?.oauthToken, accessToken?.oauthTokenSecret,
				if (bearerToken) this@Client.bearerToken else null
			)
			bodyForTwitter(params)
		}
	}
}


