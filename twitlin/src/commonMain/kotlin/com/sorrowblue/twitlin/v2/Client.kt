package com.sorrowblue.twitlin.v2

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import com.sorrowblue.twitlin.basics.oauth2.BearerToken
import com.sorrowblue.twitlin.foundation.buildHeaderString
import com.sorrowblue.twitlin.foundation.createSignature
import com.sorrowblue.twitlin.foundation.generateTimestamp
import com.sorrowblue.twitlin.foundation.notNullParams
import com.sorrowblue.twitlin.net.clientEngine
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.utils.io.charsets.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

typealias Params = List<Pair<String, String>>

internal open class Client(
	private val apiKey: String,
	private val apiSecretKey: String,
	var accessToken: AccessToken? = null
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
		useOAuth2: Boolean = false
	): Response<T> {
		val nonce = com.sorrowblue.twitlin.foundation.generateNonce()
		val timestamp = generateTimestamp()
		val nonNullParams = params.notNullParams()
		return kotlin.runCatching {
			httpClient.get<HttpResponse>(url.combineParams(nonNullParams)) {
				val signature = createSignature(
					apiKey, apiSecretKey, nonce, timestamp,
					accessToken?.oauthToken, accessToken?.oauthTokenSecret, nonNullParams
				)
				val headerString =
					buildHeaderString(apiKey, nonce, signature, timestamp, accessToken?.oauthToken)
				val value = if (useOAuth2) "Bearer ${bearerToken?.accessToken}" else headerString
				header(HttpHeaders.Authorization, value)
			}.onSuccess<T, Response.Success<T>, Response.Failure<T>>()
		}.getOrElse {
			it.printStackTrace()
			Napier.e(it.toString(), tag = "Client GET")
			Napier.e(it.message ?: "unknown error", tag = "Client GET")
			if (it.toString().contains("java.net.UnknownHostException")) {
				Response.Failure(
					statusCode = -400,
					listOf(
						Error(
							title = it.message ?: "no title",
							detail = it.message ?: "no detail",
							type = "twitlin"
						)
					)
				)
			} else {
				Response.Failure(
					statusCode = -99,
					listOf(
						Error(
							title = it.message ?: "no title",
							detail = it.message ?: "no detail",
							type = "twitlin"
						)
					)
				)
			}
		}
	}


	suspend inline fun <reified T : Any> post2(
		url: String,
		vararg headerParams: Pair<String, Any?> = emptyArray(),
		overrideOAuthToken: String? = null,
		oauthToken: Boolean = true,
		bearerToken: Boolean = false
	): Response<T> {
		val nonce = com.sorrowblue.twitlin.foundation.generateNonce()
		val timestamp = generateTimestamp()
		val nonNullParams = headerParams.notNullParams()
		val mOauthToken =
			if (oauthToken) overrideOAuthToken ?: accessToken?.oauthToken else null
		return kotlin.runCatching {
			httpClient.post<HttpResponse>(url) {
				val signature = createSignature(
					apiKey, apiSecretKey,
					nonce, timestamp,
					mOauthToken, if (mOauthToken != null) accessToken?.oauthTokenSecret else null,
					nonNullParams
				)
				val headerString =
					buildHeaderString(apiKey, nonce, signature, timestamp, mOauthToken)
				val value =
					if (bearerToken) "Bearer ${this@Client.bearerToken?.accessToken}" else headerString
				header(HttpHeaders.Authorization, value)
			}.onSuccess<T, Response.Success<T>, Response.Failure<T>>()
		}.getOrElse {
			it.printStackTrace()
			Napier.e(it.toString(), tag = "Client POST")
			Napier.e(it.message ?: "unknown error", tag = "Client POST")
			if (it.toString().contains("java.net.UnknownHostException")) {
				Response.Failure(
					statusCode = -400,
					listOf(
						Error(
							title = it.message ?: "no title",
							detail = it.message ?: "no detail",
							type = "twitlin"
						)
					)
				)
			} else {
				Response.Failure(
					statusCode = -99,
					listOf(
						Error(
							title = it.message ?: "no title",
							detail = it.message ?: "no detail",
							type = "twitlin"
						)
					)
				)
			}
		}
	}

	private fun String.combineParams(params: Params) =
		if (params.isEmpty()) this else "$this?${params.formUrlEncode()}"

/*
	suspend inline fun <reified T : Any> post(
		url: String,
		vararg headerParams: Pair<String, Any?> = emptyArray(),
		overrideOAuthToken: String? = null,
		oauthToken: Boolean = true,
		bearerToken: Boolean = false
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
			}.onSuccess<T, Response.Success<T>, Response.Failure<T>>()
		}.getOrElse {
			if (it.toString().contains("java.net.UnknownHostException")) {
				Response.Failure(
					statusCode = -400,
					listOf(Error(title = "it.message", detail = "it.message", type = "twitlin"))
				)
			} else {
				Response.Failure(
					statusCode = -99,
					listOf(Error(title = "it.message", detail = "it.message", type = "twitlin"))
				)
			}
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


	private fun createSignature(
		method: String,
		url: String,
		params: List<Pair<String, String>>,
	): String {
		val parameterStr = params.map { it.first.urlEncode() to it.second.urlEncode() }
			.sortedBy { it.first }
			.joinToString("&") { "${it.first}=${it.second}" }
		val baseString = "$method&${url.urlEncode()}&${parameterStr.urlEncode()}"
		val signingKey = "${apiSecretKey.urlEncode()}&${accessToken?.oauthTokenSecret.orEmpty()}"
		return hmacSHA1(signingKey.encodeToByteArray(), baseString.encodeToByteArray()).also {
			Napier.i(
				"""
				createSignature
				Parameter string: $parameterStr
				base string     : $baseString
				signing key     : $signingKey
				signature       : $it
			""".trimIndent()
			)
		}
	}


	private fun oAuthHeader(
		method: String,
		urlString: String,
		params: List<Pair<String, String>>,
	): String {
		val headerParams = collectingParameters + params
		val signature = createSignature(method, urlString, headerParams)
		Napier.i(
			"""
			Collecting parameters
			${headerParams.joinToString("\n") { it.first + " = " + it.second }}
			oauth_signature = signature
		""".trimIndent()
		)
		return headerParams.plus("oauth_signature" to signature).toList()
			.joinToString(", ") { "${it.first.urlEncode()}=\"${it.second.urlEncode()}\"" }
	}

*/

	internal suspend inline fun <T : Any, reified R : Response.Success<T>, reified F : Response.Failure<T>> HttpResponse.onSuccess(): Response<T> {
		val text = readText(Charsets.UTF_8)
		return if (status.value == 200) {
			Napier.d(
				""" SUCCESS
			method = ${request.method}
			url = ${request.url}
			headers = ${request.headers.toMap()}
			body = $text
			""".trimIndent(), tag = "HttpResponse.onSuccess"
			)
			kotlin.runCatching {
				json.decodeFromString<R>(text)
			}.onFailure { Napier.d(it.message.orEmpty(), tag = "HttpResponse.onSuccess") }
				.getOrElse { json.decodeFromString<F>(text) }
		} else {
			Napier.e(
				""" ERROR
			method = ${request.method}
			url = ${request.url}
			headers = ${request.headers.toMap()}
			text = $text
			""".trimIndent(), tag = "HttpResponse.onSuccess"
			)
			kotlin.runCatching {
				json.decodeFromString<F>(text)
					.also { if (status.value == 401) Twitlin.onInvalidToken() }
			}.getOrElse {
				Response.Failure(status.value, listOf(json.decodeFromString(text)))
			}
		}
	}


}

