package com.sorrowblue.twitlin.authentication.impl

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.InvalidateToken
import com.sorrowblue.twitlin.authentication.OAuthApi
import com.sorrowblue.twitlin.authentication.RequestToken
import com.sorrowblue.twitlin.authentication.XAuthAccessType
import com.sorrowblue.twitlin.client.Oauth1aClient
import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.utils.API_OAUTH
import io.ktor.http.Parameters
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.serializer

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class OAuthApiImpl(private val client: Oauth1aClient) : OAuthApi {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun accessToken(oauthToken: String, oauthVerifier: String): Response<AccessToken> {
        return client.submitForm("$API_OAUTH/access_token", Response.serializer(String.serializer()), Parameters.build {
            append("oauth_token", oauthToken)
            append("oauth_verifier", oauthVerifier)
        }).convertData(AccessToken.Companion::fromString)
    }

    override fun authenticate(oauthToken: String, forceLogin: Boolean?, screenName: String?): String {
        val login = forceLogin?.let { "&force_login=$it" }.orEmpty()
        val name = screenName?.let { "&screen_name=$it" }.orEmpty()
        return "$API_OAUTH/authenticate?oauth_token=$oauthToken$login$name"
    }

    override fun authorize(oauthToken: String, forceLogin: Boolean?, screenName: String?): String {
        val login = forceLogin?.let { "&force_login=$it" }.orEmpty()
        val name = screenName?.let { "&screen_name=$it" }.orEmpty()
        return "$API_OAUTH/authorize?oauth_token=$oauthToken$login$name"
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun requestToken(
        oauthCallback: String,
        xAuthAccessType: XAuthAccessType?
    ): Response<RequestToken> {
        return client.submitForm(
            "$API_OAUTH/request_token",
            Response.serializer(String.serializer()),
            Parameters.build {
                append("oauth_callback", oauthCallback)
                xAuthAccessType?.let { append("x_auth_access_type", it.name.lowercase()) }
            }).convertData(RequestToken.Companion::fromString)
    }

    override suspend fun invalidateToken(): Response<InvalidateToken> =
        client.post("$API_OAUTH/invalidate_token", Response.serializer(InvalidateToken.serializer()))
}

