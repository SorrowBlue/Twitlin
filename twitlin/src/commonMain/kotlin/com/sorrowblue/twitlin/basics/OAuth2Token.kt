package com.sorrowblue.twitlin.basics

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OAuth2Token(
	@SerialName("token_type")
	val tokenType: String,
	@SerialName("access_token")
	val accessToken: String
)
