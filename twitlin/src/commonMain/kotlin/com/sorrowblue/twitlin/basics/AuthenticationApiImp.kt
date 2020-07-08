package com.sorrowblue.twitlin.basics

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.net.*
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.parse
import kotlinx.serialization.stringify
import set


internal class AuthenticationApiImp(private val client: Client) : AuthenticationApi {

	private var token: OAuthToken? = null


	override suspend fun authenticate(callbackUrl: String): Response<String> {
		return when (val res = requestToken(callbackUrl)) {
			is Response.SUCCESS -> (res.value).also { token = it }
			is Response.Error -> return Response.Error(res.errors)
		}.let { Response.SUCCESS("${Urls.OAUTH}/authenticate?oauth_token=${it.oauthToken}") }
	}

	@OptIn(ImplicitReflectionSerializer::class, UnstableDefault::class)
	override suspend fun accessToken(authenticate: Authenticate): Response<Unit> {
		return if (authenticate.oauthToken != token?.oauthToken) {
			Response.error(ErrorMessages.Error("OAuth token does not match", -202))
		} else {
			client.post<String>(
				"${Urls.OAUTH}/access_token",
				listOf("oauth_verifier" to authenticate.oauthVerifier),
				overrideOAuthToken = authenticate.oauthToken
			).fold({ s ->
				AccessToken.fromString(s).let {
					client.accessToken = it
					Twitlin.settings["twitlin_access_token"] = Json.stringify(it)
					Response.success(Unit)
				}
			}, { Response.error<Unit>(it) })
		}
	}

	private var _oAuth2Token: OAuth2Token? = null

	@OptIn(ImplicitReflectionSerializer::class, InternalAPI::class, UnstableDefault::class)
	override suspend fun oauth2Token(): Response<OAuth2Token> {
		val bearer = "${client.apiKey.urlEncode()}:${client.apiSecretKey.urlEncode()}".encodeBase64()
		val response = client.httpClient.post<HttpResponse>("${Urls.OAUTH2}/token") {
			header(HttpHeaders.Authorization, "Basic $bearer")
			contentType(ContentType.parse("application/x-www-form-urlencoded;charset=UTF-8"))
			body = "grant_type=client_credentials"
		}
		return if (response.status.isSuccess()) {
			response.content.readUTF8Line()?.let { Json.parse<OAuth2Token>(it) }?.let { Response.success(it) }
				?: Response.error<OAuth2Token>()
		} else {
			Response.error<OAuth2Token>()
		}
	}

	private suspend fun requestToken(callbackUrl: String): Response<OAuthToken> =
		client.post<String>("${Urls.OAUTH}/request_token", listOf("oauth_callback" to callbackUrl), oauthEnabled = false)
			.fold({ Response.SUCCESS(OAuthToken.fromString(it)) }, { Response.Error(it) })
}
