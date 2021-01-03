/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BearerToken(
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("access_token")
    val accessToken: String
)
