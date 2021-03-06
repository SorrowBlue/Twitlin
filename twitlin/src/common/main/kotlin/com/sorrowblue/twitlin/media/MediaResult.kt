/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.media

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property mediaId
 * @property mediaIdString
 * @property mediaKey
 * @property size
 * @property expiresAfterSecs
 * @property video
 * @property image
 * @property processingInfo
 */
@AndroidParcelize
@Serializable
public data class MediaResult(
    @SerialName("media_id") val mediaId: Long,
    @SerialName("media_id_string") val mediaIdString: String,
    @SerialName("media_key") val mediaKey: String? = null,
    val size: Int? = null,
    @SerialName("expires_after_secs") val expiresAfterSecs: Int,
    val video: Video? = null,
    val image: Image? = null,
    val processingInfo: Info? = null
) : AndroidParcelable, JvmSerializable {

    /**
     * TODO
     *
     * @property videoType
     */
    @AndroidParcelize
    @Serializable
    public data class Video(
        @SerialName("video_type") val videoType: String
    ) : AndroidParcelable, JvmSerializable

    /**
     * TODO
     *
     * @property imageType
     * @property w
     * @property h
     */
    @AndroidParcelize
    @Serializable
    public data class Image(
        @SerialName("image_type") val imageType: String,
        val w: Int,
        val h: Int
    ) : AndroidParcelable, JvmSerializable

    /**
     * TODO
     *
     * @property state
     * @property progressPercent
     * @property checkAfterSecs
     * @property error
     */
    @AndroidParcelize
    @Serializable
    public data class Info(
        val state: State,
        @SerialName("progress_percent") val progressPercent: Int? = null,
        @SerialName("check_after_secs") val checkAfterSecs: Int? = null,
        val error: Error? = null
    ) : AndroidParcelable, JvmSerializable {

        /**
         * TODO
         *
         */
        @Serializable
        public enum class State {

            @SerialName("pending")
            PENDING,

            @SerialName("in_progress")
            IN_PROGRESS,

            @SerialName("failed")
            FAILED,

            @SerialName("succeeded")
            SUCCEEDED
        }

        /**
         * TODO
         *
         * @property code
         * @property name
         * @property message
         */
        @AndroidParcelize
        @Serializable
        public data class Error(
            val code: Int,
            val name: String,
            val message: String
        ) : AndroidParcelable, JvmSerializable
    }
}
