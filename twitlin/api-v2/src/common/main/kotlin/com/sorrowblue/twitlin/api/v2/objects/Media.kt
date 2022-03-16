package com.sorrowblue.twitlin.api.v2.objects

import com.sorrowblue.twitlin.api.v2.objects.Media.Type
import com.sorrowblue.twitlin.api.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.objects.MediaKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Media refers to any image, GIF, or video attached to a Tweet. The media object is not a primary
 * object on any endpoint, but can be found and expanded in the Tweet object.
 *
 * The object is available for expansion with `expansions=[Attachments.mediaKeys]` to get the
 * condensed object with only default fields. Use the expansion with the field parameter:
 * [TweetsApi.tweet] mediaFields when requesting additional fields to complete the object.
 *
 * @property mediaKey Unique identifier of the expanded media content.
 * @property type Type of content ([Type.ANIMATED_GIF], [Type.ANIMATED_GIF], [Type.VIDEO]).
 * @property durationMs Available when type is video. Duration in milliseconds of the video.
 * @property height Height of this content in pixels.
 * @property nonPublicMetrics Non-public engagement metrics for the media content at the time of
 * the request. **Requires user context authentication.**
 * @property organicMetrics Engagement metrics for the media content, tracked in an organic context,
 * at the time of the request. **Requires user context authentication.**
 * @property previewImageUrl URL to the static placeholder preview of this content.
 * @property promotedMetrics Engagement metrics for the media content,
 * tracked in a promoted context, at the time of the request.
 * **Requires user context authentication.**
 * @property publicMetrics Public engagement metrics for the media content at the time of
 * the request.
 * @property width Width of this content in pixels.
 * @property url
 */
@AndroidParcelize
@Serializable
public data class Media(
    @SerialName("media_key")
    val mediaKey: MediaKey,
    val type: Type,
    @SerialName("duration_ms")
    val durationMs: Int? = null,
    val height: Int? = null,
    @SerialName("non_public_metrics")
    val nonPublicMetrics: Metrics? = null,
    @SerialName("organic_metrics")
    val organicMetrics: OrganicMetrics? = null,
    @SerialName("preview_image_url")
    val previewImageUrl: String? = null,
    @SerialName("promoted_metrics")
    val promotedMetrics: Metrics? = null,
    @SerialName("public_metrics")
    val publicMetrics: PublicMetrics? = null,
    val width: Int? = null,
    val url: String? = null,
) : AndroidParcelable, JvmSerializable {

    /**
     * Determine video engagement: how many users played through to each quarter of the video.
     *
     * @property playback0Count
     * @property playback100Count
     * @property playback25Count
     * @property playback50Count
     * @property playback75Count
     */
    @AndroidParcelize
    @Serializable
    public data class Metrics(
        @SerialName("playback_0_count")
        val playback0Count: Int,
        @SerialName("playback_100_count")
        val playback100Count: Int,
        @SerialName("playback_25_count")
        val playback25Count: Int,
        @SerialName("playback_50_count")
        val playback50Count: Int,
        @SerialName("playback_75_count")
        val playback75Count: Int,
    ) : AndroidParcelable, JvmSerializable

    /**
     * Determine organic media engagement.
     *
     * @property playback0Count
     * @property playback100Count
     * @property playback25Count
     * @property playback50Count
     * @property playback75Count
     * @property viewCount
     */
    @AndroidParcelize
    @Serializable
    public data class OrganicMetrics(
        @SerialName("playback_0_count")
        val playback0Count: Int,
        @SerialName("playback_100_count")
        val playback100Count: Int,
        @SerialName("playback_25_count")
        val playback25Count: Int,
        @SerialName("playback_50_count")
        val playback50Count: Int,
        @SerialName("playback_75_count")
        val playback75Count: Int,
        @SerialName("view_count")
        val viewCount: Int,
    ) : AndroidParcelable, JvmSerializable

    /**
     * Determine total number of views for the video attached to the Tweet.
     *
     * @property viewCount
     */
    @AndroidParcelize
    @Serializable
    public data class PublicMetrics(
        @SerialName("view_count")
        val viewCount: Int,
    ) : AndroidParcelable, JvmSerializable

    /**
     * Classify the media as a photo, GIF, or video
     */
    @Serializable
    public enum class Type {
        @SerialName("animated_gif")
        ANIMATED_GIF,

        @SerialName("photo")
        PHOTO,

        @SerialName("video")
        VIDEO,
    }
}
