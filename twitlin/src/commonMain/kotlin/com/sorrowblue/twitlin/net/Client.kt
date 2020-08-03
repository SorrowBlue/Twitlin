package com.sorrowblue.twitlin.net

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Account
import com.sorrowblue.twitlin.basics.oauth2.BearerToken
import com.sorrowblue.twitlin.utils.onSuccess
import com.sorrowblue.twitlin.utils.urlEncode
import com.soywiz.klock.DateTime
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.random.Random

typealias Params = List<Pair<String, String>>

internal open class Client(
	internal val apiKey: String,
	internal val apiSecretKey: String,
	var account: Account? = null
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
		url: String, params: Params = emptyList(), useOAuth2: Boolean = false
	): Response<T> = httpClient.get<HttpResponse>(url.combineParams(params)) {
		val value =
			if (useOAuth2) "Bearer ${oAuth2Token()?.accessToken}"
			else "OAuth ${oAuthHeader(method, url, params)}"
		header(HttpHeaders.Authorization, value)
	}.onSuccess(json::decodeFromString)

	suspend inline fun <reified T : Any> getList(
		url: String, params: Params = emptyList(), useOAuth2: Boolean = false
	): Response<List<T>> =
		httpClient.get<HttpResponse>(url.combineParams(params)) {
			val value =
				if (useOAuth2) "Bearer ${oAuth2Token()?.accessToken}"
				else "OAuth ${oAuthHeader(method, url, params)}"
			header(HttpHeaders.Authorization, value)
		}.onSuccess {
			Napier.d(
				"The request for \"$url\" was successful. The response body is \"$it\".",
				tag = "Twitlin Client"
			)
			json.decodeFromString(it)
		}

	suspend inline fun <reified T : Any> post(
		url: String,
		headerParams: Params = emptyList(),
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
		if (T::class == String::class) it as T else json.decodeFromString(it)
	}

	private val collectingParameters
		get() = mutableListOf(
			"oauth_consumer_key" to apiKey,
			"oauth_nonce" to generateNonce(),
			"oauth_signature_method" to "HMAC-SHA1",
			"oauth_timestamp" to (DateTime.nowUnixLong() / 1000).toString(),
			"oauth_version" to "1.0"
		).apply {
			account?.let { add("oauth_token" to it.accessToken.oauthToken) }
		}

	private fun String.combineParams(params: Params) =
		if (params.isEmpty()) this else "$this?${params.formUrlEncode()}"

	private fun genSignature(
		method: HttpMethod,
		url: String,
		params: List<Pair<String, String>>
	): String {
		val parameterStr = params.map { it.first.urlEncode() to it.second.urlEncode() }
			.sortedBy { it.first }.joinToString("&") { "${it.first}=${it.second}" }
		val baseString = "${method.value}&${url.urlEncode()}&${parameterStr.urlEncode()}"
		val signingKey =
			"${apiSecretKey.urlEncode()}&${account?.accessToken?.oauthTokenSecret.orEmpty()}"
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

	fun oAuthHeader(
		method: HttpMethod,
		urlString: String,
		params: List<Pair<String, String>>
	): String {
		val headerParams = collectingParameters + params
		val signature = genSignature(method, urlString, headerParams)
		return headerParams.plus("oauth_signature" to signature).toList()
			.joinToString(", ") { "${it.first.urlEncode()}=\"${it.second.urlEncode()}\"" }
	}

	private var _bearerToken: BearerToken? = null

	@OptIn(InternalAPI::class)
	suspend fun oAuth2Token() = _bearerToken ?: kotlin.run {
		val bearer = "${apiKey.urlEncode()}:${apiSecretKey.urlEncode()}".encodeBase64()
		val response = httpClient.post<HttpResponse>("${Urls.OAUTH2}/token") {
			header(HttpHeaders.Authorization, "Basic $bearer")
			contentType(ContentType.parse("application/x-www-form-urlencoded;charset=UTF-8"))
			body = "grant_type=client_credentials"
		}
		(if (response.status.isSuccess()) response.content.readUTF8Line()
			?.let { Json.decodeFromString<BearerToken>(it) } else null)?.also { _bearerToken = it }
	}
}

