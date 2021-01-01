/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication.impl

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.InvalidateToken
import com.sorrowblue.twitlin.authentication.OAuthApi
import com.sorrowblue.twitlin.authentication.RequestToken
import com.sorrowblue.twitlin.authentication.XAuthAccessType
import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.client.Urls

private const val OAUTH = "${Urls.FQDN}/oauth"

internal class OAuthApiImpl(private val client: TwitlinClient) : OAuthApi {

    override suspend fun accessToken(
        oauthToken: String,
        oauthVerifier: String
    ): Response<AccessToken> = client.post<String>(
        "$OAUTH/access_token",
        "oauth_verifier" to oauthVerifier,
        oauthToken = oauthToken
    ).fold({ Response.Success(AccessToken.fromString(it.value), it.statusCode) },
        { Response.Error(it.errorMessages) })

    override fun authenticate(
        oauthToken: String,
        forceLogin: Boolean?,
        screenName: String?
    ): String {
        val login = forceLogin?.let { "&force_login=$it" }.orEmpty()
        val name = screenName?.let { "&screen_name=$it" }.orEmpty()
        return "$OAUTH/authenticate?oauth_token=$oauthToken$login$name"
    }

    override fun authorize(
        oauthToken: String,
        forceLogin: Boolean?,
        screenName: String?
    ): String {
        val login = forceLogin?.let { "&force_login=$it" }.orEmpty()
        val name = screenName?.let { "&screen_name=$it" }.orEmpty()
        return "$OAUTH/authorize?oauth_token=$oauthToken$login$name"
    }

    override suspend fun requestToken(
        oauthCallback: String,
        xAuthAccessType: XAuthAccessType?
    ): Response<RequestToken> =
        client.post<String>(
            "$OAUTH/request_token",
            "oauth_callback" to oauthCallback,
            "x_auth_access_type" to xAuthAccessType?.name?.toLowerCase()
        ).fold({ Response.Success(RequestToken.fromString(it.value), it.statusCode) },
            { Response.Error(it.errorMessages) })

    override suspend fun invalidateToken(): Response<InvalidateToken> =
        client.post("$OAUTH/invalidate_token")

}
