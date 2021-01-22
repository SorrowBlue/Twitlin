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
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import kotlinx.serialization.builtins.serializer

private const val OAUTH = "${Urls.FQDN}/oauth"

internal class OAuthApiImpl(private val client: UserClient) : OAuthApi {
    override suspend fun accessToken(token: String, verifier: String): Response<AccessToken> {
        return client.postForAuthentication(
            "$OAUTH/access_token",
            Response.serializer(String.serializer()),
            "oauth_verifier" to verifier,
            oauthToken = token
        ).convertData(AccessToken.Companion::fromString)
    }

    override fun authenticate(token: String, forceLogin: Boolean?, screenName: String?): String {
        val login = forceLogin?.let { "&force_login=$it" }.orEmpty()
        val name = screenName?.let { "&screen_name=$it" }.orEmpty()
        return "$OAUTH/authenticate?oauth_token=$token$login$name"
    }

    override fun authorize(token: String, forceLogin: Boolean?, screenName: String?): String {
        val login = forceLogin?.let { "&force_login=$it" }.orEmpty()
        val name = screenName?.let { "&screen_name=$it" }.orEmpty()
        return "$OAUTH/authorize?oauth_token=$token$login$name"
    }

    override suspend fun requestToken(
        callback: String,
        type: XAuthAccessType?
    ): Response<RequestToken> {
        return client.post(
            "$OAUTH/request_token",
            Response.serializer(String.serializer()),
            "oauth_callback" to callback,
            "x_auth_access_type" to type?.name?.toLowerCase()
        ).convertData(RequestToken::fromString)
    }

    override suspend fun invalidateToken(): Response<InvalidateToken> =
        client.post("$OAUTH/invalidate_token", Response.serializer(InvalidateToken.serializer()))
}
