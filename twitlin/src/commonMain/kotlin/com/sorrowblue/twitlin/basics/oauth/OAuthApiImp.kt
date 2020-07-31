package com.sorrowblue.twitlin.basics.oauth

import com.sorrowblue.twitlin.Account
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.basics.OAuthToken
import com.sorrowblue.twitlin.net.*
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.stringify

internal class OAuthApiImp(private val client: Client) :
	OAuthApi {

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
				AccessToken.fromString(s).let { accessToken ->
					client.account = Account(0, "", "", "", accessToken)
					Twitlin.Api.account.verifyCredentials(
						includeEntities = false,
						skipStatus = false,
						includeEmail = false
					).getOrNull()?.let {
						Twitlin.account = Account(it.id, it.profileImageUrlHttps, it.name, it.screenName, accessToken)
						Response.success(Unit)
					} ?: Response.error<Unit>(ErrorMessages.Error("アカウント情報が首都デキませんでした", -203))
				}
			}, { Response.error<Unit>(it) })
		}
	}

	override suspend fun authorize(forceLogin: Boolean, screenName: String) =
		requestToken("snsmate://dawd.com").fold({ Response.success("${Urls.OAUTH}/authorize?oauth_token=${it.oauthToken}") },
			{ Response.error<String>(it) })

	override suspend fun invalidateToken() {
		TODO("Not yet implemented")
	}

	private suspend fun requestToken(callbackUrl: String): Response<OAuthToken> =
		client.post<String>(
			"${Urls.OAUTH}/request_token",
			listOf("oauth_callback" to callbackUrl),
			oauthEnabled = false
		)
			.fold({ Response.SUCCESS(OAuthToken.fromString(it)) }, { Response.Error(it) })
}
