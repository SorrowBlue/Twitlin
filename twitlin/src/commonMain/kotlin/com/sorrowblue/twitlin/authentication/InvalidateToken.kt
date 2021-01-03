/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property accessToken TODO
 */
@Serializable
public data class InvalidateToken(
    @SerialName("access_token")
    val accessToken: String
)
