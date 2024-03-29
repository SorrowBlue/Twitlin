package com.sorrowblue.twitlin.api.oauth.impl

import com.sorrowblue.twitlin.api.API_OAUTH
import com.sorrowblue.twitlin.api.client.Oauth1aClient
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.ktx.decodeFromString
import com.sorrowblue.twitlin.api.oauth.AccessToken
import com.sorrowblue.twitlin.api.oauth.OAuthApi
import com.sorrowblue.twitlin.api.oauth.RequestToken
import com.sorrowblue.twitlin.api.oauth.XAuthAccessType
import com.sorrowblue.twitlin.api.oauth.response.OauthInvalidateToken
import io.ktor.http.Parameters
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.properties.Properties

internal class OAuthApiImpl(private val client: Oauth1aClient) : OAuthApi {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun accessToken(oauthToken: String, oauthVerifier: String): Response<AccessToken> {
        return client.submitForm(
            "$API_OAUTH/access_token",
            Response.serializer(String.serializer()),
            Parameters.build {
                append("oauth_token", oauthToken)
                append("oauth_verifier", oauthVerifier)
            }
        ).convertData(Properties::decodeFromString)
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
            }
        ).convertData(Properties::decodeFromString)
    }

    override suspend fun invalidateToken(): Response<String> =
        client.post("$API_OAUTH/invalidate_token", Response.serializer(OauthInvalidateToken.serializer()))
            .convertData(OauthInvalidateToken::access_token)
}
