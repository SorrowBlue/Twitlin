package com.sorrowblue.twitlin.objects

import kotlinx.serialization.Serializable

@Serializable
data class Language(
	val code: String,
	val status: String,
	val name: String
)
