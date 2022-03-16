package com.sorrowblue.twitlin.api.v2.oauth2

/**
 * O auth2api
 *
 * @constructor Create empty O auth2api
 */
public interface OAuth2Api {

    /**
     * Authorize
     *
     * @param redirectUri
     * @param scopes
     * @param state
     * @param codeChallenge
     * @return
     */
    public fun authorize(
        redirectUri: String,
        scopes: List<OAuth2Scopes>,
        state: String,
        codeChallenge: CodeChallenge
    ): String

    /**
     * Token
     *
     * @param redirectUri
     * @param code
     * @param codeVerifier
     * @return
     */
    public suspend fun token(redirectUri: String, code: String, codeVerifier: String): OAuth2Response
}
