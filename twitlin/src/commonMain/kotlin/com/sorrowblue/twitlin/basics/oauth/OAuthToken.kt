package com.sorrowblue.twitlin.basics.oauth

/**
 * An [oauthToken] and [oauthTokenSecret] are user-specific credentials used to authenticate OAuth 1.0a API requests.
 * They specify the Twitter account the request is made on behalf of.
 *
 * You can generate your own access token and token secret
 * if you would like your app to make requests on behalf of the same Twitter account associated
 * with your developer account on the [Twitter developer app's](https://developer.twitter.com/en/docs/basics/apps/overview) details page.
 *
 * @property oauthToken oauthToken used in Twitter API
 * @property oauthTokenSecret oauthTokenSecret used in Twitter API
 * @property oauthCallbackConfirmed true if the oauth callback was confirmed
 */
data class OAuthToken(
    val oauthToken: String,
    val oauthTokenSecret: String,
    val oauthCallbackConfirmed: Boolean
) {
    internal companion object {
        fun fromString(string: String): OAuthToken = string.split("&").map {
            it.split("=").run { getOrNull(0) to getOrNull(1) }
        }.toMap().let {
            OAuthToken(
                it["oauth_token"].orEmpty(),
                it["oauth_token_secret"].orEmpty(),
                it["oauth_callback_confirmed"].toBoolean()
            )
        }
    }
}
