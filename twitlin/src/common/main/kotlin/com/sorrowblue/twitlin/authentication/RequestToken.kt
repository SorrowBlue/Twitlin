package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.utils.parameterToMap
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.decodeFromMap

/**
 * Request token
 *
 * @property oauthToken
 * @property oauthTokenSecret
 * @property oauthCallbackConfirmed
 * @constructor Create empty Request token
 */
@AndroidParcelize
@Serializable
public data class RequestToken(
    @SerialName("oauth_token")
    val oauthToken: String,
    @SerialName("oauth_token_secret")
    val oauthTokenSecret: String,
    @SerialName("oauth_callback_confirmed")
    val oauthCallbackConfirmed: Boolean
) : AndroidParcelable,
    JvmSerializable {

    internal companion object {
        @OptIn(ExperimentalSerializationApi::class)
        fun fromString(str: String): RequestToken = Properties.decodeFromMap(str.parameterToMap())
    }
}
