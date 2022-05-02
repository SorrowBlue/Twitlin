package com.sorrowblue.twitlin.api.v2.oauth2.impl

import com.sorrowblue.twitlin.api.v2.Endpoints
import com.sorrowblue.twitlin.api.v2.client.Oauth2Client
import com.sorrowblue.twitlin.api.v2.oauth2.CodeChallenge
import com.sorrowblue.twitlin.api.v2.oauth2.OAuth2Api
import com.sorrowblue.twitlin.api.v2.oauth2.OAuth2Response
import com.sorrowblue.twitlin.api.v2.oauth2.OAuth2Scopes
import io.ktor.http.HttpHeaders
import io.ktor.http.Parameters
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol

internal class OAuth2ApiImpl(val client: Oauth2Client) : OAuth2Api {

    override suspend fun authorize(
        redirectUri: String,
        scopes: List<OAuth2Scopes>,
        state: String,
        codeChallenge: CodeChallenge
    ): String {
        return URLBuilder(
            protocol = URLProtocol.HTTPS,
            "twitter.com",
            pathSegments = listOf("i", "oauth2", "authorize"),
            parameters = ParametersBuilder().apply {
                append("response_type", "code")
                append("client_id", client.clientId)
                append("redirect_uri", redirectUri)
                append("scope", scopes.joinToString(" ") { it.value })
                append("state", state)
                append("code_challenge", codeChallenge.codeChallengeString())
                append("code_challenge_method", codeChallenge.method)
            }.build()
        ).buildString()
    }

    override suspend fun token(redirectUri: String, code: String, codeVerifier: String): OAuth2Response {
        return client.submitForm(
            "${Endpoints.OAUTH2}/token",
            OAuth2Response.serializer(),
            Parameters.build {
                append("code", code)
                append("grant_type", "authorization_code")
                append("client_id", client.clientId)
                append("redirect_uri", redirectUri)
                append("code_verifier", codeVerifier)
            }
        )
    }

    override suspend fun refresh(refreshCode: String): OAuth2Response {
        return client.submitForm(
            "${Endpoints.OAUTH2}/token",
            OAuth2Response.serializer(),
            Parameters.build {
                append("refresh_token", refreshCode)
                append("grant_type", "refresh_token")
                append("client_id", client.clientId)
            }
        ) {
            headers.remove(HttpHeaders.Authorization)
        }
    }
}
