package com.sorrowblue.twitlin.v2

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import com.sorrowblue.twitlin.basics.oauth2.BearerToken
import com.sorrowblue.twitlin.foundation.authentication.bodyForTwitter
import com.sorrowblue.twitlin.foundation.authentication.headerForTwitter
import com.sorrowblue.twitlin.foundation.authentication.notNullParams
import com.sorrowblue.twitlin.net.clientEngine
import com.sorrowblue.twitlin.net.combineParams
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.content.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.utils.io.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal open class Client(
	private val apiKey: String,
	private val secretKey: String,
	var accessToken: AccessToken? = null,
	var bearerToken: BearerToken? = null
) {


	val json: Json = Json {
		isLenient = true
		ignoreUnknownKeys = true
		encodeDefaults = false
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
		useBearerToken: Boolean = false
	): Response<T> =
		httpClient.get<HttpResponse>(url.combineParams(params.notNullParams)) {
			if (useBearerToken) {
				headerForTwitter(this@Client.bearerToken)
			} else {
				headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
			}
		}.toResponse()

	suspend inline fun <reified T : Any, reified V : Any> getCustom(
		url: String,
		vararg params: Pair<String, Any?> = emptyArray(),
		useBearerToken: Boolean = false,
		converter: (V, HttpResponse) -> Response<T>
	): Response<T> =
		httpClient.get<HttpResponse>(url.combineParams(params.notNullParams)) {
			if (useBearerToken) {
				headerForTwitter(this@Client.bearerToken)
			} else {
				headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
			}
		}.toCustomResponse(converter)

	suspend inline fun <reified T : Any> post(
		url: String,
		vararg params: Pair<String, Any?> = emptyArray(),
		oauthToken: String? = null,
		useBearerToken: Boolean = false
	): Response<T> =
		httpClient.post<HttpResponse>(url) {
			if (useBearerToken) {
				headerForTwitter(this@Client.bearerToken)
			} else {
				val accessToken = oauthToken?.let { accessToken?.copy(oauthToken = it) }
				headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
			}
			bodyForTwitter(params.notNullParams)
		}.toResponse()

	suspend inline fun <reified V : Any, reified T : Any> postJson(
		url: String,
		vararg params: Pair<String, Any?> = emptyArray(),
		body: V,
		oauthToken: String? = null,
		useBearerToken: Boolean = false
	): Response<T> = httpClient.post<HttpResponse>(url) {
		if (useBearerToken) {
			headerForTwitter(this@Client.bearerToken)
		} else {
			val accessToken = oauthToken?.let { accessToken?.copy(oauthToken = it) }
			headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
		}
		this.body =
			TextContent(json.encodeToString(body), contentType = ContentType.Application.Json)
	}.toResponse()

	suspend inline fun <reified V : Any, reified T : Any, reified B : Any> postJsonCustom(
		url: String,
		vararg params: Pair<String, Any?> = emptyArray(),
		body: B,
		oauthToken: String? = null,
		useBearerToken: Boolean = false,
		converter: (V, HttpResponse) -> Response<T>
	): Response<T> = httpClient.post<HttpResponse>(url) {
		if (useBearerToken) {
			headerForTwitter(this@Client.bearerToken)
		} else {
			val accessToken = oauthToken?.let { accessToken?.copy(oauthToken = it) }
			headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
		}
		this.body =
			TextContent(json.encodeToString(body), contentType = ContentType.Application.Json)
	}.toCustomResponse(converter)

	suspend inline fun <reified T : Any, reified V : Any> put(url: String, body: V): Response<T> =
		httpClient.put<HttpResponse>(url) {
			headerForTwitter(apiKey, secretKey, emptyList(), accessToken)
			this.body =
				TextContent(json.encodeToString(body), contentType = ContentType.Application.Json)
		}.toResponse()

	@ExperimentalCoroutinesApi
	inline fun <reified T : Any> streaming(
		url: String,
		vararg params: Pair<String, Any?> = emptyArray(),
		useBearerToken: Boolean = false
	): Flow<Response<T>> {
		return channelFlow {
			httpClient.get<HttpStatement>(url.combineParams(params.notNullParams)) {
				if (useBearerToken) {
					headerForTwitter(this@Client.bearerToken)
				} else {
					headerForTwitter(apiKey, secretKey, params.notNullParams, accessToken)
				}
			}.execute { response ->
				do {
					kotlin.runCatching {
						val text: String
						if (response.status.value == 200) {
							text = response.readText()
							json.decodeFromString<Response.Success<T>>(text)
						} else {
							text = response.content.readUTF8Line()!!
							json.decodeFromString<Response.Failure<T>>(text)
						}.also {
							Napier.i(
								"""
							method = ${response.request.method}
							url = ${response.request.url}
							headers = ${response.request.headers.toMap()}
							body = $text
							""".trimIndent(), tag = "TwitlinClient"
							)
						}
					}.getOrElse {
						val statusCode =
							if (it.toString()
									.contains("java.net.UnknownHostException")
							) -400 else -99
						Response.Failure(
							statusCode = statusCode,
							Error(
								title = it.message ?: "Unknown error",
								detail = it.stackTraceToString(),
								type = "TwitlinClient"
							)
						)
					}.let(channel::offer)
				} while (isClosedForSend.not())
			}
		}
	}
}

suspend inline fun <reified T : Any> HttpResponse.toResponse(): Response<T> = kotlin.runCatching {
	val content: String
	if (status.isSuccess()) {
		content  = readText()
		Napier.i(
			"""
		status  = $status
		method  = ${request.method}
		url     = ${request.url}
		headers = ${request.headers.toMap()}
		content = $content
		""".trimIndent(), tag = TAG
		)
		Json.decodeFromString<Response.Success<T>>(content)
	} else {
		content  = this.content.readUTF8Line()!!
		Napier.i(
			"""
		status  = $status
		method  = ${request.method}
		url     = ${request.url}
		headers = ${request.headers.toMap()}
		content = $content
		""".trimIndent(), tag = TAG
		)
		Json.decodeFromString<Response.Failure<T>>(content)
	}
}.getOrElse {
	Napier.e(it.stackTraceToString(), it, TAG)
	val statusCode =
		if (it.toString().contains("java.net.UnknownHostException")) STATUS_CODE_NO_NETWORK
		else STATUS_CODE_CLIENT_ERROR
	Response.Failure(
		statusCode,
		Error(it.message.orEmpty(), it.stackTraceToString(), "Client error")
	)
}

suspend inline fun <reified T : Any, V : Any> HttpResponse.toCustomResponse(converter: (T, HttpResponse) -> Response<V>): Response<V> {
	return kotlin.runCatching {
		val content: String = content.readUTF8Line()!!
		Napier.i(
			"""
			method  = ${request.method}
			url     = ${request.url}
			headers = ${request.headers.toMap()}
			content = $content
			""".trimIndent(), tag = TAG
		)
		if (status.isSuccess()) {
			converter(Json.decodeFromString(content), this)
		} else {
			Json.decodeFromString<Response.Failure<V>>(content)
		}
	}.getOrElse {
		Napier.e(it.stackTraceToString(), it, TAG)
		val statusCode =
			if (it.toString().contains("java.net.UnknownHostException")) STATUS_CODE_NO_NETWORK
			else STATUS_CODE_CLIENT_ERROR
		Response.Failure(
			statusCode,
			Error(it.message.orEmpty(), it.stackTraceToString(), "Client error")
		)
	}
}

const val TAG = "TwitlinClient"
const val STATUS_CODE_NO_NETWORK = -400
const val STATUS_CODE_CLIENT_ERROR = -401
