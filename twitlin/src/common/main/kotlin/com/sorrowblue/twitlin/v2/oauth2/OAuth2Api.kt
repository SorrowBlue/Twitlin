package com.sorrowblue.twitlin.v2.oauth2

import com.sorrowblue.twitlin.v2.V2Endpoints
import com.sorrowblue.twitlin.v2.client.OAuth2Client
import io.ktor.http.Parameters

public class OAuth2Api(private val client2: OAuth2Client) {

    public fun authorize(
        redirectUri: String,
        scopes: List<OAuth2Scope>,
        state: String,
        codeChallenge: CodeChallenge
    ): String {
        val scope = scopes.joinToString("%20", transform = OAuth2Scope::value)
        return "${V2Endpoints.OAUTH2}/authorize?response_type=code&client_id=${client2.clientId}&redirect_uri=$redirectUri&scope=${scope}&state=$state&code_challenge=${codeChallenge.codeChallenge}&code_challenge_method=${codeChallenge.method}"
    }

    @Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
    public suspend fun token(code: String, redirectUri: String, codeVerifier: String): OAuth2Token {
        return client2.submitForm(
            "https://api.twitter.com/2/oauth2/token", OAuth2Token.serializer(), Parameters.build {
                append("code", code)
                append("grant_type", "authorization_code")
                append("client_id", client2.clientId)
                append("redirect_uri", redirectUri)
                append("code_verifier", codeVerifier)
            }
        )
    }
}
