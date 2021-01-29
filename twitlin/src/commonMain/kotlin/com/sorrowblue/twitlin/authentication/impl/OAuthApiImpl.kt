/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication.impl

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.InvalidateToken
import com.sorrowblue.twitlin.authentication.OAuthApi
import com.sorrowblue.twitlin.authentication.RequestToken
import com.sorrowblue.twitlin.authentication.XAuthAccessType
import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import kotlinx.serialization.builtins.serializer

private const val OAUTH = "${Urls.FQDN}/oauth"

internal class OAuthApiImpl(private val client: UserClient) : OAuthApi {
    override suspend fun accessToken(
        oauthToken: String,
        oauthVerifier: String
    ): Response<AccessToken> {
        return client.postForAuthentication(
            "$OAUTH/access_token",
            Response.serializer(String.serializer()),
            "oauth_verifier" to oauthVerifier,
            oauthToken = oauthToken
        ).convertData(AccessToken.Companion::fromString)
    }

    override fun authenticate(
        oauthToken: String,
        forceLogin: Boolean?,
        screenName: String?
    ): String {
        val login = forceLogin?.let { "&force_login=$it" }.orEmpty()
        val name = screenName?.let { "&screen_name=$it" }.orEmpty()
        return "$OAUTH/authenticate?oauth_token=$oauthToken$login$name"
    }

    override fun authorize(oauthToken: String, forceLogin: Boolean?, screenName: String?): String {
        val login = forceLogin?.let { "&force_login=$it" }.orEmpty()
        val name = screenName?.let { "&screen_name=$it" }.orEmpty()
        return "$OAUTH/authorize?oauth_token=$oauthToken$login$name"
    }

    override suspend fun requestToken(
        oauthCallback: String,
        xAuthAccessType: XAuthAccessType?
    ): Response<RequestToken> {
        return client.post(
            "$OAUTH/request_token",
            Response.serializer(String.serializer()),
            "oauth_callback" to oauthCallback,
            "x_auth_access_type" to xAuthAccessType?.name?.toLowerCase()
        ).convertData(RequestToken::fromString)
    }

    override suspend fun invalidateToken(): Response<InvalidateToken> =
        client.post("$OAUTH/invalidate_token", Response.serializer(InvalidateToken.serializer()))
}
