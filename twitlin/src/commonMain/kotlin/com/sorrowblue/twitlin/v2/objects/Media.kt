package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Media(
	@SerialName("media_key")
	val mediaKey: String,
	val type: Type,
	@SerialName("duration_ms")
	val durationMs: Int? = null,
	val height: Int? = null,
	@SerialName("non_public_metrics")
	val nonPublicMetrics: NonPublicMetrics? = null,
	@SerialName("organic_metrics")
	val organicMetrics: Metrics? = null,
	@SerialName("preview_image_url")
	val previewImageUrl: String? = null,
	@SerialName("promoted_metrics")
	val promotedMetrics: Metrics? = null,
	@SerialName("public_metrics")
	val publicMetrics: PublicMetrics? = null,
	val width: Int? = null,
) : Parcelable {

	@Serializable
	enum class Type {
		@SerialName("animated_gif")
		ANIMATED_GIF,

		@SerialName("photo")
		PHOTO,

		@SerialName("video")
		VIDEO
	}
}
