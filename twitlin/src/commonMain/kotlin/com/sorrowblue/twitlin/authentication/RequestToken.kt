/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.utils.toMap
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.decodeFromMap

/**
 * TODO
 *
 * @property oauthToken TODO
 * @property oauthTokenSecret TODO
 * @property oauthCallbackConfirmed TODO
 */
@Serializable
public data class RequestToken(
    @SerialName("oauth_token")
    val oauthToken: String,
    @SerialName("oauth_token_secret")
    val oauthTokenSecret: String,
    @SerialName("oauth_callback_confirmed")
    val oauthCallbackConfirmed: Boolean
) {

    internal companion object {

        /**
         * TODO
         *
         * @param str TODO
         * @return TODO
         */
        @OptIn(ExperimentalSerializationApi::class)
        fun fromString(str: String): RequestToken =
            Properties.decodeFromMap(str.toMap())

    }

}
