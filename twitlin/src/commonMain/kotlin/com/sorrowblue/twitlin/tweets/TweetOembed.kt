/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property url Content permalinks.
 * @property authorName The name of the content author.
 * @property authorUrl Content author's profile page, etc.
 * @property html HTML code for embedding content.
 * @property width Content width.
 * @property height The height of the content.
 * @property type The type of embedded content.
 * @property cacheAge TODO
 * @property providerName The name of the content provider.
 * @property providerUrl The URL of the content provider.
 * @property version oEmbed version
 */
@Serializable
public data class TweetOembed(
    val url: String,
    @SerialName("author_name")
    val authorName: String,
    @SerialName("author_url")
    val authorUrl: String,
    val html: String,
    val width: Int,
    val height: Int?,
    val type: Type,
    @SerialName("cache_age")
    val cacheAge: Long,
    @SerialName("provider_name")
    val providerName: String,
    @SerialName("provider_url")
    val providerUrl: String,
    val version: String
) : JvmSerializable {

    /**
     * The type of embedded content.
     */
    @Serializable
    public enum class Type {
        @SerialName("rich")
        RICH,

        @SerialName("video")
        VIDEO,

        @SerialName("photo")
        PHOTO,

        @SerialName("link")
        LINK,
    }
}
