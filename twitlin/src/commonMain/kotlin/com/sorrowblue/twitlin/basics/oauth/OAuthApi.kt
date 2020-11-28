package com.sorrowblue.twitlin.basics.oauth

import com.sorrowblue.twitlin.net.Response

/**
 * Many endpoints on the Twitter developer platform use the OAuth 1.0a method to act, or make API requests,
 * on behalf of a Twitter account. For example,
 * if you have a Twitter developer app, you can make API requests on behalf of any Twitter account as long as that user authenticates your app.
 */
interface OAuthApi {
    /**
     * Allows a Consumer application to exchange the OAuth Request Token for an OAuth Access Token.
     * This method fulfills [Section 6.3](http://oauth.net/core/1.0/#auth_step3) of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9).
     *
     * @param authenticate
     * @return
     */
    suspend fun accessToken(authenticate: Authenticate): Response<AccessToken>

    /**
     * Allows a Consumer application to use an OAuth [OAuthApi.requestToken] to request user authorization.
     * This method is a replacement of [Section 6.2](http://oauth.net/core/1.0/#auth_step2)
     * of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9) for applications using the callback authentication flow.
     * The method will use the currently logged in user as the account for access authorization unless the [forceLogin] parameter is set to true.
     * This method differs from GET [OAuthApi.authorize] in that if the user has already granted the application permission,
     * the redirect will occur without the user having to re-approve the application.
     * To realize this behavior, you must enable the Use Sign in with Twitter setting on your [application record](https://developer.twitter.com/apps).
     *
     * @param oAuthToken [OAuthToken] created with [OAuthApi.requestToken]
     * @param forceLogin When set to `true` The currently logged in user will be used as the permission account
     * @param screenName Prefills the username input box of the OAuth login screen with the given value
     * @return Url used in web browser
     */
    fun authenticate(
        oAuthToken: OAuthToken,
        forceLogin: Boolean = false,
        screenName: String? = null
    ): String

    /**
     * Allows a Consumer application to use an OAuth Request Token to request user authorization.
     * This method fulfills [Section 6.2](http://oauth.net/core/1.0/#auth_step2)
     * of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9).
     * Desktop applications must use this method (and cannot use [OAuthApi.authenticate]).
     *
     * **Usage Note: ** An `oauthCallback` is never sent to this method, provide it to [OAuthApi.requestToken] instead.
     *
     * @param oAuthToken [OAuthToken] created with [OAuthApi.requestToken]
     * @param forceLogin When set to `true` The currently logged in user will be used as the permission account
     * @param screenName Prefills the username input box of the OAuth login screen with the given value
     * @return Url used in web browser
     */
    fun authorize(
        oAuthToken: OAuthToken,
        forceLogin: Boolean = false,
        screenName: String? = null
    ): String

    /**
     * Allows a Consumer application to obtain an OAuth Request Token to request user authorization.
     * This method fulfills [Section 6.1](https://oauth.net/core/1.0/#auth_step1) of the [OAuth 1.0 authentication flow](http://oauth.net/core/1.0/#anchor9).
     *
     * @param oauthCallback For OAuth 1.0a compliance this parameter is **required**.
     * The value you specify here will be used as the URL a user is redirected to should they approve your application's access to their account.
     * Set this to `oob` for out-of-band pin mode.
     * This is also how you specify custom callbacks for use in desktop/mobile applications.
     * Always send an [oauthCallback] on this step, regardless of a pre-registered callback.
     *
     * We require that any callback URL used with this endpoint will have to be whitelisted within the app settings on developer.twitter.com*.
     *
     * @return Returns an Oauth token
     */
    suspend fun requestToken(oauthCallback: String): Response<OAuthToken>

    /**
     * Allows a registered application to revoke an issued OAuth [AccessToken.oauthToken] by presenting its client credentials.
     * Once an [AccessToken.oauthToken] has been invalidated, new creation attempts will yield a different Access Token and usage
     * of the invalidated token will no longer be allowed.
     *
     * @return
     */
    suspend fun invalidateToken(): Response<Unit>
}
