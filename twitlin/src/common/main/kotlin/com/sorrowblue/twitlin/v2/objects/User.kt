/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.annotation.KotlinIgnoredOnParcel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class User(
    val id: String,
    val name: String,
    val username: String,
    @SerialName("created_at")
    val _createdAt: String? = null,
    val description: String? = null,
    val entities: Entities? = null,
    val location: String? = null,
    @SerialName("pinned_tweet_id")
    val pinnedTweetId: String? = null,
    @SerialName("profile_image_url")
    val profileImageUrl: String? = null,
    val protected: Boolean? = null,
    @SerialName("public_metrics")
    val publicMetrics: PublicMetrics? = null,
    val url: String? = null,
    val verified: Boolean? = null,
    val withheld: Withheld? = null
) : AndroidParcelable, JvmSerializable {

    @KotlinIgnoredOnParcel
    val createdAt: LocalDateTime?
        get() = _createdAt?.isoToLocalDateTime()

    @AndroidParcelize
    @Serializable
    public data class Entities(
        val url: EntitiesUrl? = null,
        val description: Description? = null
    ) : AndroidParcelable, JvmSerializable {

        @AndroidParcelize
        @Serializable
        public data class EntitiesUrl(
            val urls: List<Url>
        ) : AndroidParcelable, JvmSerializable

        @AndroidParcelize
        @Serializable
        public data class Description(
            val urls: List<Url>? = null,
            val hashtags: List<Hashtag>? = null,
            val mentions: List<Mention>? = null,
            val cashtags: List<CashTag>? = null
        ) : AndroidParcelable, JvmSerializable

        @AndroidParcelize
        @Serializable
        public data class Url(
            val start: Int,
            val end: Int,
            val url: String,
            @SerialName("expanded_url") val expandedUrl: String? = null,
            @SerialName("display_url") val displayUrl: String? = null,
        ) : AndroidParcelable, JvmSerializable
    }
}
