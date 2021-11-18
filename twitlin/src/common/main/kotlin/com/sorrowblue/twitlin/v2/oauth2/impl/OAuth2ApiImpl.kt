package com.sorrowblue.twitlin.v2.oauth2.impl

import com.sorrowblue.twitlin.v2.Endpoints
import com.sorrowblue.twitlin.v2.client.Oauth2Client
import com.sorrowblue.twitlin.v2.oauth2.CodeChallenge
import com.sorrowblue.twitlin.v2.oauth2.OAuth2Api
import com.sorrowblue.twitlin.v2.oauth2.OAuth2Response
import com.sorrowblue.twitlin.v2.oauth2.OAuth2Scopes
import io.ktor.http.Parameters

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class OAuth2ApiImpl(val client: Oauth2Client) : OAuth2Api {

    override fun authorize(
        redirectUri: String,
        scopes: List<OAuth2Scopes>,
        state: String,
        codeChallenge: CodeChallenge
    ): String {
        return "https://twitter.com/i/oauth2/authorize" +
                "?response_type=code" +
                "&client_id=${client.clientId}" +
                "&redirect_uri=$redirectUri" +
                "&scope=${scopes.joinToString("%20") { it.value }}" +
                "&state=$state" +
                "&code_challenge=${codeChallenge.codeChallengeString}" +
                "&code_challenge_method=${codeChallenge.method}"
    }

    override suspend fun token(redirectUri: String, code: String, codeVerifier: String): OAuth2Response {
        return client.submitForm("${Endpoints.OAUTH2}/token", OAuth2Response.serializer(), Parameters.build {
            append("code", code)
            append("grant_type", "authorization_code")
            append("client_id", client.clientId)
            append("redirect_uri", redirectUri)
            append("code_verifier", codeVerifier)
        })
    }
}
