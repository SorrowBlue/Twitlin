package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.AndroidParcel
import com.sorrowblue.twitlin.AndroidParcelize
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
data class TwitterCard(
	val title: String,
	val url: String,
	val description: String,
	val image: String,
	val type: CardType,
	val site: String
): AndroidParcel {
	enum class CardType {
		SUMMARY,
		SUMMARY_LARGE_IMAGE,
		APP,
		PLAYER,
		UNDEFINED;

		companion object {
			fun valueOf2(value: String) =
				kotlin.runCatching { valueOf(value.toUpperCase()) }.getOrElse { UNDEFINED }
		}
	}
}
