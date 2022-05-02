package com.sorrowblue.twitlin.api.v2.oauth2

import com.sorrowblue.twitlin.api.v2.TwitterAPIv2

/**
 * O auth2api
 */
public interface OAuth2Api : TwitterAPIv2 {

    /**
     * Authorize
     *
     * @param redirectUri
     * @param scopes
     * @param state
     * @param codeChallenge
     * @return
     */
    public suspend fun authorize(
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

    /**
     * Refresh
     *
     * @param refreshCode
     * @return
     */
    public suspend fun refresh(refreshCode: String): OAuth2Response
}
