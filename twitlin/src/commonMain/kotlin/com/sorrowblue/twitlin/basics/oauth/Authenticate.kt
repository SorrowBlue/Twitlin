package com.sorrowblue.twitlin.basics.oauth

data class Authenticate(
	val oauthToken: String,
	val oauthVerifier: String
)
