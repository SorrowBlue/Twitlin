package com.sorrowblue.twitlin.net

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import com.sorrowblue.twitlin.basics.oauth2.BearerToken
import com.sorrowblue.twitlin.foundation.authentication.bodyForTwitter
import com.sorrowblue.twitlin.foundation.authentication.headerForTwitter
import com.sorrowblue.twitlin.foundation.authentication.notNullParams
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.utils.io.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

internal open class Client(
	internal val apiKey: String,
	internal val secretKey: String,
	var accessToken: AccessToken? = null,
	var bearerToken: BearerToken? = null
) {

	val json: Json = Json {
		isLenient = true
		ignoreUnknownKeys = true
	}

	val httpClient
		get() = HttpClient(clientEngine) {
			install(JsonFeature) { serializer = KotlinxSerializer(json) }
		}

	suspend inline fun <reified T : Any> get(
		url: String,
		vararg params: Pair<String, Any?> = emptyArray(),
		useBearerToken: Boolean = false,
	): Response<T> = catchResponse {
		httpClient.get(url.combineParams(params.notNullParams)) {
			if (useBearerToken) {
				headerForTwitter(this@Client.bearerToken)
			} else {
				headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
			}
		}
	}

	suspend inline fun <reified T : Any> post(
		url: String,
		vararg params: Pair<String, String>,
		oauthToken: String? = null,
		useBearerToken: Boolean = false
	): Response<T> = catchResponse {
		httpClient.post(url) {
			if (useBearerToken) {
				headerForTwitter(this@Client.bearerToken)
			} else {
				val accessToken =
					oauthToken?.let { accessToken?.copy(oauthToken = it) ?: AccessToken(it, "") }
				headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
			}
			bodyForTwitter(params.notNullParams)
		}
	}

	suspend inline fun <reified T> catchResponse(function: () -> HttpResponse): Response<T> =
		kotlin.runCatching {
			with(function()) {
				val text: String
				if (status.value == 200) {
					text = readText()
					Response.SUCCESS(json.decodeFromString<T>(text))
				} else {
					text = content.readUTF8Line()!!
					Response.Error(Json.decodeFromString<ErrorMessages>(text).errors)
				}.also {
					Napier.d(
						"""
						method = ${request.method}
						url = ${request.url}
						headers = ${request.headers.toMap()}
						body = $text
					""".trimIndent(), tag = "HttpResponse.onSuccess"
					)
				}
			}
		}.getOrElse {
			Napier.e(it.toString(), it, tag = "TwitlinClient")
			val errorCode =
				if (it.toString().contains("java.net.UnknownHostException")) -100 else 0
			Response.error(ErrorMessages.Error(it.message ?: "unknown error", errorCode))
		}
}

fun String.combineParams(params: List<Pair<String, String>>): String =
	if (params.isEmpty()) this else this + "?" + params.formUrlEncode()


