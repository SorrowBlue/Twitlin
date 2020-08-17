package com.sorrowblue.twitlin.basics.oauth2

import com.sorrowblue.twitlin.net.Response

interface OAuth2Api {
	/**
	 * Allows a registered application to obtain an OAuth 2 Bearer Token,
	 * which can be used to make API requests on an application's own behalf,
	 * without a user context. This is called [Application-only authentication](https://developer.twitter.com/en/docs/basics/authentication/overview/application-only).
	 *
	 * A Bearer Token may be invalidated using [OAuth2Api.invalidateToken].
	 * Once a Bearer Token has been invalidated,
	 * new creation attempts will yield a different Bearer Token and usage of the previous token will no longer be allowed.
	 *
	 * Only one bearer token may exist outstanding for an application,
	 * and repeated requests to this method will yield the same already-existent token until it has been invalidated.
	 *
	 * Successful responses include a JSON-structure describing the awarded Bearer Token.
	 *
	 * Tokens received by this method should be cached. If attempted too frequently, requests will be rejected with a HTTP 403 with code 99.
	 *
	 * @return OAuth 2 Bearer Token
	 */
	suspend fun token(): Response<BearerToken>

	/**
	 * Allows a registered application to revoke an issued oAuth 2.0 Bearer Token by presenting its client credentials.
	 * Once a Bearer Token has been invalidated, new creation attempts will yield a different Bearer Token and usage of the invalidated token will no longer be allowed.
	 *
	 * Successful responses include a JSON-structure describing the revoked Bearer Token.
	 *
	 * @param bearerToken Bearer token to revoke
	 * @return Revoked access token string
	 */
	suspend fun invalidateToken(bearerToken: BearerToken): Response<String>
}
