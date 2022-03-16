package com.sorrowblue.twitlin.core.authentication

import com.sorrowblue.twitlin.core.client.Response

/**
 * ### OAuth 1.0a
 *
 * Many endpoints on the Twitter developer platform use the OAuth 1.0a method to act, or make API requests,
 * on behalf of a Twitter account. For example, if you have a Twitter developer app,
 * you can make API requests on behalf of any Twitter account as long as that user authenticates your app.
 *
 * Please note: if you are not are not familiar with concepts such as HMAC-SHA1 and percent encoding,
 * we recommend that you check out the "useful tools" section below that lists some API clients
 * that greatly simplify the authentication process.
 *
 * @see <a href="https://developer.twitter.com/en/docs/authentication/oauth-1-0a">OAuth 1.0a</a>
 */
public interface OAuthApi {
    /**
     * Allows a Consumer application to exchange the OAuth Request Token for an OAuth Access Token.
     * This method fulfills [Section 6.3](http://oauth.net/core/1.0/#auth_step3) of the
     * [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9).
     *
     * @param oauthToken The oauth_token here must be the same as the oauth_token returned in the
     * request_token step.
     * @param oauthVerifier If using the OAuth web-flow,
     * set this parameter to the value of the oauth_verifier returned in the callback URL. If you
     * are using out-of-band OAuth, set this value to the pin-code. For OAuth 1.0a compliance this
     * parameter is **required**. OAuth 1.0a is strictly enforced and applications not using the
     * oauth_verifier will fail to complete the OAuth flow.
     * @return Authenticated access token
     */
    public suspend fun accessToken(oauthToken: String, oauthVerifier: String): Response<AccessToken>

    /**
     * Allows a Consumer application to use an OAuth request_token to request user authorization.
     *
     * This method is a replacement of [Section 6.2](http://oauth.net/core/1.0/#auth_step2) of the
     * [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9)
     * for applications using the callback authentication flow. The method will use the currently
     * logged in user as the account for access authorization unless the [forceLogin] parameter is
     * set to `true`.
     *
     * This method differs from [OAuthApi.authorize] in that if the user has already granted the
     * application permission, the redirect will occur without the user having to re-approve the
     * application. To realize this behavior, you must enable the Use Sign in with Twitter setting
     * on your [application record](https://developer.twitter.com/apps).
     *
     * @param oauthToken
     * @param forceLogin Forces the user to enter their credentials to ensure the correct users
     * account is authorized.
     * @param screenName Prefills the username input box of the OAuth login screen with the given
     * value.
     * @return
     */
    public fun authenticate(
        oauthToken: String,
        forceLogin: Boolean? = null,
        screenName: String? = null
    ): String

    /**
     * Allows a Consumer application to use an OAuth Request Token to request user authorization.
     * This method fulfills [Section 6.2](http://oauth.net/core/1.0/#auth_step2) of the
     * [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9). Desktop applications
     * must use this method (and cannot use [OAuthApi.requestToken]).
     *
     * ***Usage Note:*** An `oauth_Callback` is never sent to this method, provide it to
     * [OAuthApi.requestToken] instead.
     *
     * @param oauthToken
     * @param forceLogin Forces the user to enter their credentials to ensure the correct users
     * account is authorized.
     * @param screenName Prefills the username input box of the OAuth login screen with the given
     * value.
     * @return
     */
    public fun authorize(
        oauthToken: String,
        forceLogin: Boolean? = null,
        screenName: String? = null
    ): String

    /**
     * Allows a Consumer application to obtain an OAuth Request Token to request user authorization.
     * This method fulfills [Section 6.1](https://oauth.net/core/1.0/#auth_step1) of the
     * [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9).
     *
     * ***We require you use HTTPS for all OAuth authorization steps.***
     *
     * ***Usage Note:*** Only ASCII values are accepted for the `oauth_nonce`
     *
     * @param oauthCallback
     * @param xAuthAccessType
     * @return
     */
    public suspend fun requestToken(
        oauthCallback: String,
        xAuthAccessType: XAuthAccessType? = null
    ): Response<RequestToken>

    /**
     * Allows a registered application to revoke an issued OAuth access_token by presenting its
     * client credentials. Once an access_token has been invalidated, new creation attempts will
     * yield a different Access Token and usage of the invalidated token will no longer be allowed.
     *
     * @return
     */
    public suspend fun invalidateToken(): Response<InvalidateToken>
}
