package com.sorrowblue.twitlin.basics

data class Authenticate(
	val oauthToken: String,
	val oauthVerifier: String
)
