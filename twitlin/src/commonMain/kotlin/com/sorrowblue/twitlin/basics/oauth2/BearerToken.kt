package com.sorrowblue.twitlin.basics.oauth2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BearerToken(
	@SerialName("token_type") val tokenType: String,
	@SerialName("access_token") val accessToken: String
)
