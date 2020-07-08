package com.sorrowblue.twitlin.trends

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Trend(
	val name: String,
	@SerialName("promoted_content")
	val promotedContent: Boolean?,
	val query: String,
	val tweetVolume: Long? = null,
	val url: String
)
