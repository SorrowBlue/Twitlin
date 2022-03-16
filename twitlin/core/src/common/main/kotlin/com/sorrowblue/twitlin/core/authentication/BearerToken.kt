package com.sorrowblue.twitlin.core.authentication

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Bearer token
 *
 * @property tokenType
 * @property accessToken
 */
@AndroidParcelize
@Serializable
public data class BearerToken(
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("access_token")
    val accessToken: String
) : AndroidParcelable, JvmSerializable
