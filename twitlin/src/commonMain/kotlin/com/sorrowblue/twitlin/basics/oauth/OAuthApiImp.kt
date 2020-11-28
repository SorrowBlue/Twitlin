package com.sorrowblue.twitlin.basics.oauth

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls

private const val OAUTH = "${Urls.ROOT}/oauth"

internal class OAuthApiImp(private val client: Client) : OAuthApi {

    override suspend fun accessToken(authenticate: Authenticate): Response<AccessToken> =
        client.post<String>(
            "$OAUTH/access_token",
            "oauth_verifier" to authenticate.oauthVerifier,
            oauthToken = authenticate.oauthToken
        ).fold({ Response.success(AccessToken.fromString(it)) }, { Response.error(it) })

    override fun authenticate(oAuthToken: OAuthToken, forceLogin: Boolean, screenName: String?) =
        "$OAUTH/authenticate?oauth_token=${oAuthToken.oauthToken}&force_login=$forceLogin${screenName?.let { "screen_name=$it" } ?: ""}"

    override fun authorize(oAuthToken: OAuthToken, forceLogin: Boolean, screenName: String?) =
        "$OAUTH/authorize?oauth_token=${oAuthToken.oauthToken}&force_login=$forceLogin${screenName?.let { "screen_name=$it" } ?: ""}"

    override suspend fun requestToken(oauthCallback: String): Response<OAuthToken> =
        client.post<String>("$OAUTH/request_token", "oauth_callback" to oauthCallback)
            .fold({ Response.success(OAuthToken.fromString(it)) }, { Response.Error(it) })

    override suspend fun invalidateToken(): Response<Unit> = client.post("$OAUTH/invalidate_token")
}
