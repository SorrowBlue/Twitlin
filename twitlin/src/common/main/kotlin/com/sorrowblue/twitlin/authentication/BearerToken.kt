package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Bearer token
 *
 * @property tokenType
 * @property accessToken
 * @constructor Create empty Bearer token
 */
@AndroidParcelize
@Serializable
public data class BearerToken(
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("access_token")
    val accessToken: String
) : AndroidParcelable, JvmSerializable
