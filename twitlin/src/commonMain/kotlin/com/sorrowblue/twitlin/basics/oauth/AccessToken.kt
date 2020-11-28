package com.sorrowblue.twitlin.basics.oauth

import kotlinx.serialization.Serializable

/**
 * TODO
 * AccessToken
 *
 * @property oauthToken
 * @property oauthTokenSecret
 */
@Serializable
data class AccessToken(
    val oauthToken: String,
    val oauthTokenSecret: String
) {

    internal companion object {
        fun fromString(str: String): AccessToken {
            val data = str.split("&").map {
                it.split("=").getOrNull(0) to it.split("=").getOrNull(1)
            }.toMap()
            return AccessToken(data["oauth_token"].orEmpty(), data["oauth_token_secret"].orEmpty())
        }
    }
}
