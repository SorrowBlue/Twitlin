package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.utils.parameterToMap
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.properties.Properties
import kotlinx.serialization.properties.decodeFromMap

/**
 * Access token
 *
 * @property oauthToken
 * @property oauthTokenSecret
 * @property userId
 * @property screenName
 * @constructor Create empty Access token
 */
@AndroidParcelize
@Serializable
public data class AccessToken(
    @SerialName("oauth_token")
    val oauthToken: String,
    @SerialName("oauth_token_secret")
    val oauthTokenSecret: String,
    @SerialName("user_id")
    val userId: UserId,
    @SerialName("screen_name")
    val screenName: String
) : AndroidParcelable, JvmSerializable {

    internal companion object {
        @OptIn(ExperimentalSerializationApi::class)
        fun fromString(str: String): AccessToken = Properties.decodeFromMap(str.parameterToMap())
    }
}
