/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.client.Response

/**
 * ### OAuth 2.0 Bearer Token
 * OAuth 2.0 Bearer Token authenticates requests on behalf of your
 * [developer App](https://developer.twitter.com/en/docs/apps). As this method is specific to the
 * App, it does not involve any users. This method is typically for developers that need read-only
 * access to public information.
 *
 * This authentication method requires for you to pass a Bearer Token with your request, which you
 * can generate within the Keys and tokens section of your developer Apps. Here is an example of
 * what a request looks like with a fake bearer token plugged in:
 *
 * ```
 * ```
 *
 * API calls using app-only authentication are rate limited per endpoint at the App level.
 *
 * To use this method, you'll need a bearer token, which you can generate by passing your consumer
 * key and secret through the [OAuth2Api.token] endpoint or from the keys and token section of your
 * App settings in the developer portal. If you'd like to revoke a bearer token, you can use the
 * [OAuth2Api.invalidateToken] endpoint or click where it says revoke next to the bearer token in
 * the keys and tokens section of your App settings.
 *
 * ### Next steps
 * * [See more about app-only authentication](https://developer.twitter.com/en/docs/authentication/oauth-2-0/application-only)
 * * [Learn about bearer tokens](https://developer.twitter.com/en/docs/authentication/oauth-2-0/bearer-tokens)
 *
 * @see <a href="https://developer.twitter.com/en/docs/authentication/oauth-2-0">OAuth 2.0 Bearer Token</a>
 */
public interface OAuth2Api {
    /**
     * Allows a registered application to obtain an OAuth 2 Bearer Token, which can be used to make
     * API requests on an application's own behalf, without a user context. This is called
     * [Application-only authentication](https://developer.twitter.com/en/docs/basics/authentication/overview/application-only).
     *
     * A Bearer Token may be invalidated using oauth2/invalidate_token. Once a Bearer Token has
     * been invalidated, new creation attempts will yield a different Bearer Token and usage of the
     * previous token will no longer be allowed.
     *
     * Only one bearer token may exist outstanding for an application, and repeated requests to this
     * method will yield the same already-existent token until it has been invalidated.
     *
     * Successful responses include a JSON-structure describing the awarded Bearer Token.
     *
     * Tokens received by this method should be cached. If attempted too frequently, requests will
     * be rejected with a HTTP 403 with code 99.
     *
     * @return
     */
    public suspend fun token(): Response<BearerToken>

    /**
     * Allows a registered application to revoke an issued oAuth 2.0 Bearer Token by presenting its
     * client credentials. Once a Bearer Token has been invalidated, new creation attempts will
     * yield a different Bearer Token and usage of the invalidated token will no longer be allowed
     *
     * Successful responses include a JSON-structure describing the revoked Bearer Token.
     *
     * @return TODO
     */
    public suspend fun invalidateToken(): Response<InvalidateToken>
}
