package com.sorrowblue.twitlin.basics.oauth

/**
 * Access the URL returned by [OAuthApi.authenticate] or [OAuthApi.authorize] and set the parameters
 * that will be returned when the user completes the authentication.
 *
 * **example: ** `https://example.com/authenticate/response?oauth_token=NPcudxy0yU5T3tBzho7iCotZ3cnetKwcTIRlX0iwRl0&oauth_verifier=uw7NjWHT6OJ1MpJOXsHfNxoAhPKpgI8BlYDhxEjIBY`
 *
 * @property oauthToken Set the value of `oauth_token`
 * @property oauthVerifier Set the value of `oauth_verifier`
 */
data class Authenticate(
    val oauthToken: String,
    val oauthVerifier: String
)
