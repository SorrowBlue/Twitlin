package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class TwitterCard(
	val title: String,
	val url: String,
	val description: String,
	val image: String,
	val type: CardType,
	val site: String
) : Parcelable {
	enum class CardType {
		SUMMARY,
		SUMMARY_LARGE_IMAGE,
		APP,
		PLAYER,
	}
}
