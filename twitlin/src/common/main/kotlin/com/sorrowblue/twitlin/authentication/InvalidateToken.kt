/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property accessToken TODO
 */
@AndroidParcelize
@Serializable
public data class InvalidateToken(
    @SerialName("access_token") val accessToken: String
) : AndroidParcelable, JvmSerializable
