/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BearerToken(
    @SerialName("")
    val tokenType: String,
    @SerialName("access_token")
    val accessToken: String
)
