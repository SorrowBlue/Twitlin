package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import com.sorrowblue.twitlin.v2.serializer.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String,
    val username: String,
    @SerialName("created_at")
    @Serializable(LocalDateTimeSerializer::class)
    val createdAt: LocalDateTime? = null,
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
) {

    @Parcelize
    @Serializable
    data class Entities(
        val url: EntitiesUrl,
        val description: Description? = null
    ) : Parcelable {

        @Parcelize
        @Serializable
        data class EntitiesUrl(
            val urls: List<Url>
        ) : Parcelable

        @Parcelize
        @Serializable
        data class Description(
            val urls: List<Url>? = null,
            val hashtags: List<Hashtag>? = null,
            val mentions: List<Mention>? = null,
            val cashtags: List<CashTag>? = null
        ) : Parcelable

        @Parcelize
        @Serializable
        data class Url(
            val start: Int,
            val end: Int,
            val url: String,
            @SerialName("expanded_url") val expandedUrl: String,
            @SerialName("display_url") val displayUrl: String,
        ) : Parcelable
    }
}
