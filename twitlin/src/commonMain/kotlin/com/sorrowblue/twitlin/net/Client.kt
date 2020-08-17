package com.sorrowblue.twitlin.net

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import com.sorrowblue.twitlin.basics.oauth2.BearerToken
import com.sorrowblue.twitlin.utils.onSuccess
import com.sorrowblue.twitlin.utils.urlEncode
import com.sorrowblue.twitlin.foundation.buildHeaderString
import com.sorrowblue.twitlin.foundation.createSignature
import com.sorrowblue.twitlin.foundation.generateTimestamp
import com.sorrowblue.twitlin.foundation.notNullParams
import com.soywiz.klock.DateTime
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.random.Random

typealias Params = List<Pair<String, String>>

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

	suspend inline fun <reified T : Any> get(
		url: String,
		vararg params: Pair<String, Any?> = emptyArray(),
		useOAuth2: Boolean = false,
	): Response<T> {
		val params2 = params.mapNotNull {
			if (it.second != null) it.first to it.second.toString() else null
		}
		return httpClient.get<HttpResponse>(url.combineParams(params2)) {
			val value =
				if (useOAuth2) "Bearer ${bearerToken?.accessToken}"
				else "OAuth ${oAuthHeader("GET", url, params2)}"
			header(HttpHeaders.Authorization, value)
		}.onSuccess { json.decodeFromString(it) }
	}

	suspend inline fun <reified T : Any> getList(
		url: String, vararg params: Pair<String, Any?> = emptyArray(), useOAuth2: Boolean = false,
	): Response<List<T>> {
		val params2 = params.mapNotNull {
			if (it.second != null) it.first to it.second.toString() else null
		}
		return httpClient.get<HttpResponse>(url.combineParams(params2)) {
			val value =
				if (useOAuth2) "Bearer ${bearerToken?.accessToken}"
				else "OAuth ${oAuthHeader("GET", url, params2)}"
			header(HttpHeaders.Authorization, value)
		}.onSuccess {
			Napier.d(
				"The request for \"$url\" was successful. The response body is \"$it\".",
				tag = "Twitlin Client"
			)
			json.decodeFromString(it)
		}
	}

	suspend inline fun <reified T : Any> post(
		url: String,
		vararg headerParams: Pair<String, Any?> = emptyArray(),
		overrideOAuthToken: String? = null,
		oauthToken: Boolean = true,
		bearerToken: Boolean = false,
	): Response<T> {
		val authHeaderParams =
			headerParams.mapNotNull {
				if (it.second != null) it.first to it.second.toString() else null
			}.toMap().toMutableMap()
		if (overrideOAuthToken != null) authHeaderParams += "oauth_token" to overrideOAuthToken
		if (!oauthToken) authHeaderParams -= "oauth_token"
		val authorizationValue =
			if (bearerToken) "Bearer ${this@Client.bearerToken?.accessToken}"
			else "OAuth ${oAuthHeader("POST", url, authHeaderParams.toList())}"
		return kotlin.runCatching {
			httpClient.post<HttpResponse>(url) {
				header(HttpHeaders.Authorization, authorizationValue)
			}.onSuccess {
				if (T::class == String::class) it as T else json.decodeFromString(it)
			}
		}.getOrElse {
			Napier.e(it.toString(), it, "Client")
			if (it.toString().contains("java.net.UnknownHostException")) {
				Response.error(ErrorMessages.Error(it.message ?: "unknown error", -100))
			} else {
				Response.error(ErrorMessages.Error(it.message ?: "unknown error", -99))
			}
		}
	}

	suspend inline fun <reified T : Any> post2(
		url: String,
		vararg params: Pair<String, String>,
		overrideOAuthToken: String? = null
	): Response<T> {
		val nonce = generateNonce()
		val timestamp = generateTimestamp()
		val nonNullParams = params.notNullParams()
		return kotlin.runCatching {
			httpClient.post<HttpResponse>(url) {
				val signature = createSignature(
					apiKey, apiSecretKey, nonce, timestamp,
					overrideOAuthToken ?: accessToken?.oauthToken, accessToken?.oauthTokenSecret, nonNullParams
				)
				val headerString =
					buildHeaderString(apiKey, nonce, signature, timestamp, overrideOAuthToken ?: accessToken?.oauthToken, params.asList())
				header(HttpHeaders.Authorization, headerString)
				body = TextContent(nonNullParams.joinToString("&") { "${it.first.urlEncode()}=${it.second.urlEncode()}" }, contentType = ContentType.Application.FormUrlEncoded)
				Napier.d(
					"""
					$body
					${headers.build().toMap()}
					${this.url.build().fullPath}
				""".trimIndent()
				)
			}.onSuccess {
				json.decodeFromString<T>(it)
			}
		}.getOrElse {
			Response.error(ErrorMessages.Error(it.message ?: "error", -888))
		}
	}

	private val collectingParameters
		get() = mutableListOf(
			"oauth_consumer_key" to apiKey,
			"oauth_nonce" to generateNonce(),
			"oauth_signature_method" to "HMAC-SHA1",
			"oauth_timestamp" to (DateTime.nowUnixLong() / 1000).toString(),
			"oauth_version" to "1.0"
		).apply {
			accessToken?.let { add("oauth_token" to it.oauthToken) }
		}

	private fun String.combineParams(params: Params) =
		if (params.isEmpty()) this else "$this?${params.formUrlEncode()}"

	private fun genSignature(
		method: String,
		url: String,
		params: List<Pair<String, String>>,
	): String {
		val parameterStr = params.map { it.first.urlEncode() to it.second.urlEncode() }
			.sortedBy { it.first }.joinToString("&") { "${it.first}=${it.second}" }
		val baseString = "$method&${url.urlEncode()}&${parameterStr.urlEncode()}"
		val signingKey =
			"${apiSecretKey.urlEncode()}&${accessToken?.oauthTokenSecret.orEmpty()}"
		Napier.d("parameterStr = $parameterStr")
		Napier.d("baseString = $baseString")
		Napier.d("signingKey = $signingKey")
		return hmacSHA1(
			signingKey.encodeToByteArray(),
			baseString.encodeToByteArray()
		)
	}

	private fun generateNonce() =
		Random.nextBytes(32).decodeToString().encodeNoPaddingBase64()

	private fun oAuthHeader(
		method: String,
		urlString: String,
		params: List<Pair<String, String>>,
	): String {
		val headerParams = collectingParameters + params
		val signature = genSignature(method, urlString, headerParams)
		return headerParams.plus("oauth_signature" to signature).toList()
			.joinToString(", ") { "${it.first.urlEncode()}=\"${it.second.urlEncode()}\"" }
	}
}


