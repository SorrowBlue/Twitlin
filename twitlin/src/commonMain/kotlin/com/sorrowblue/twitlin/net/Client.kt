package com.sorrowblue.twitlin.net

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.basics.AccessToken
import com.sorrowblue.twitlin.basics.oauth2.BearerToken
import com.soywiz.klock.DateTime
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.client.statement.request
import io.ktor.http.*
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import io.ktor.util.toMap
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.parse
import kotlinx.serialization.parseList
import kotlin.random.Random

@OptIn(UnstableDefault::class, ExperimentalStdlibApi::class, ImplicitReflectionSerializer::class)
internal open class Client(
	internal val apiKey: String,
	internal val apiSecretKey: String,
	var accessToken: AccessToken? = null
) {

	internal var bearerToken: BearerToken? = null
	private var accessTokens: AccessToken? = null

	val json = Json(JsonConfiguration.Stable.copy(ignoreUnknownKeys = true, isLenient = true))

	val httpClient get() = HttpClient(clientEngine) { install(JsonFeature) { serializer = KotlinxSerializer(json) } }

	suspend inline fun <reified T : Any> get(
		url: String,
		params: List<Pair<String, String>> = emptyList(),
		useOAuth2: Boolean = false
	): Response<T> =
		httpClient.get<HttpResponse>(if (params.isEmpty()) url else "$url?${params.formUrlEncode()}") {
			if (useOAuth2) {
				val token = oAuth2Token()
				header(HttpHeaders.Authorization, "Bearer ${token?.accessToken}")
			} else {
				header(HttpHeaders.Authorization, "OAuth ${oAuthHeader(method, url, params)}")
			}
		}.onSuccess {
			Napier.d("The request for \"$url\" was successful. The response body is \"$it\".", tag = "Twitlin Client")
			json.parse<T>(it)
		}

	suspend inline fun <reified T : Any> getList(
		url: String,
		params: List<Pair<String, String>> = emptyList(),
		useOAuth2: Boolean = false
	): Response<List<T>> =
		httpClient.get<HttpResponse>(if (params.isEmpty()) url else "$url?${params.formUrlEncode()}") {
			val authorizationValue =
				if (useOAuth2) "Bearer ${oAuth2Token()?.accessToken}" else "OAuth ${oAuthHeader(method, url, params)}"
			header(HttpHeaders.Authorization, authorizationValue)
		}.onSuccess {
			Napier.d("The request for \"$url\" was successful. The response body is \"$it\".", tag = "Twitlin Client")
			json.parseList<T>(it)
		}

	suspend inline fun <reified T : Any> post(
		url: String,
		headerParams: List<Pair<String, String>> = emptyList(),
		overrideOAuthToken: String? = null,
		oauthEnabled: Boolean = true,
		useOAuth2: Boolean = false
	): Response<T> = httpClient.post<HttpResponse>(url) {
		val authHeaderParams = headerParams.toMap().toMutableMap()
		if (overrideOAuthToken != null) authHeaderParams += "oauth_token" to overrideOAuthToken
		if (!oauthEnabled) authHeaderParams -= "oauth_token"
		val authorizationValue =
			if (useOAuth2) "Bearer ${oAuth2Token()?.accessToken}"
			else "OAuth ${oAuthHeader(method, url, authHeaderParams.toList())}"
		header(HttpHeaders.Authorization, authorizationValue)
	}.onSuccess {
		if (T::class == String::class) it as T else json.parse(it)
	}

	suspend fun <R> HttpResponse.onSuccess(parse: (String) -> R) =
		if (status.value == 200) {
			Napier.d("headers = ${request.headers.toMap()}")
			Napier.d("method = ${request.method}")
			Napier.d("url = ${request.url}")
			Response.SUCCESS(parse(readText()))
		} else {
			Napier.e("headers = ${request.headers.toMap()}")
			Napier.e("method = ${request.method}")
			Napier.e("url = ${request.url}")
			content.readUTF8Line()?.let { json.parse<ErrorMessages>(it) }?.let { messages ->
				if (messages.errors.any { it.code == 89 }) {
					accessToken = null
					Twitlin.onInvalidToken.invoke()
				}
				Response.Error<R>(messages.errors)
			} ?: Response.Error<R>(emptyList())
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

	private fun genSignature(method: HttpMethod, url: String, params: List<Pair<String, String>>): String {
		val parameterStr = params.map { it.first.urlEncode() to it.second.urlEncode() }
			.sortedBy { it.first }.joinToString("&") { "${it.first}=${it.second}" }
		val baseString = "${method.value}&${url.urlEncode()}&${parameterStr.urlEncode()}"
		val signingKey = "${apiSecretKey.urlEncode()}&${accessToken?.oauthTokenSecret.orEmpty()}"
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

	fun oAuthHeader(method: HttpMethod, urlString: String, params: List<Pair<String, String>>): String {
		val headerParams = collectingParameters + params
		val signature = genSignature(method, urlString, headerParams)
		return headerParams.plus("oauth_signature" to signature).toList()
			.joinToString(", ") { "${it.first.urlEncode()}=\"${it.second.urlEncode()}\"" }
	}

	private var _bearerToken: BearerToken? = null

	@OptIn(ImplicitReflectionSerializer::class, InternalAPI::class, UnstableDefault::class)
	suspend fun oAuth2Token() = _bearerToken ?: kotlin.run {
		val bearer = "${apiKey.urlEncode()}:${apiSecretKey.urlEncode()}".encodeBase64()
		val response = httpClient.post<HttpResponse>("${Urls.OAUTH2}/token") {
			header(HttpHeaders.Authorization, "Basic $bearer")
			contentType(ContentType.parse("application/x-www-form-urlencoded;charset=UTF-8"))
			body = "grant_type=client_credentials"
		}
		(if (response.status.isSuccess()) response.content.readUTF8Line()
			?.let { Json.parse<BearerToken>(it) } else null)?.also { _bearerToken = it }
	}
}

