/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.annotation.JvmSerializable
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
 * @property userId TODO
 * @property screenName TODO
 */
@Serializable
public data class AccessToken(
    @SerialName("oauth_token")
    val oauthToken: String,
    @SerialName("oauth_token_secret")
    val oauthTokenSecret: String,
    @SerialName("user_id")
    val userId: String,
    @SerialName("screen_name")
    val screenName: String
) : JvmSerializable {
    internal companion object {
        @OptIn(ExperimentalSerializationApi::class)
        fun fromString(str: String): AccessToken = Properties.decodeFromMap(str.toMap())
    }
}
