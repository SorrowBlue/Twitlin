/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.objects


import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeISOSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class User(
    val id: String,
    val name: String,
    val username: String,
    @SerialName("created_at")
    @Serializable(LocalDateTimeISOSerializer::class)
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
) : JvmSerializable {


    @Serializable
    public data class Entities(
        val url: EntitiesUrl? = null,
        val description: Description? = null
    ) : JvmSerializable {


        @Serializable
        public data class EntitiesUrl(
            val urls: List<Url>
        ) : JvmSerializable


        @Serializable
        public data class Description(
            val urls: List<Url>? = null,
            val hashtags: List<Hashtag>? = null,
            val mentions: List<Mention>? = null,
            val cashtags: List<CashTag>? = null
        ) : JvmSerializable


        @Serializable
        public data class Url(
            val start: Int,
            val end: Int,
            val url: String,
            @SerialName("expanded_url") val expandedUrl: String? = null,
            @SerialName("display_url") val displayUrl: String? = null,
        ) : JvmSerializable
    }
}
