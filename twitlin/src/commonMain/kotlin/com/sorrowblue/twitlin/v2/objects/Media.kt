/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Media(
    @SerialName("media_key")
    val mediaKey: String,
    val type: Type,
    val url: String? = "",
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
) : JvmSerializable {

    @Serializable
    public enum class Type {
        @SerialName("animated_gif")
        ANIMATED_GIF,

        @SerialName("photo")
        PHOTO,

        @SerialName("video")
        VIDEO
    }
}
