package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.objects.UserId
import kotlin.collections.List
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * User
 *
 * @property id
 * @property name
 * @property username
 * @property _createdAt
 * @property description
 * @property entities
 * @property location
 * @property pinnedTweetId
 * @property profileImageUrl
 * @property protected
 * @property publicMetrics
 * @property url
 * @property verified
 * @property withheld
 * @constructor Create empty User
 */
@AndroidParcelize
@Serializable
public data class User(
    @SerialName("id")
    val id: UserId,
    val name: String,
    val username: String,
    @SerialName("created_at")
    val _createdAt: String? = null,
    val description: String? = null,
    val entities: Entities? = null,
    val location: String? = null,
    @SerialName("pinned_tweet_id")
    val pinnedTweetId: TweetId? = null,
    @SerialName("profile_image_url")
    val profileImageUrl: String? = null,
    val protected: Boolean? = null,
    @SerialName("public_metrics")
    val publicMetrics: PublicMetrics? = null,
    val url: String? = null,
    val verified: Boolean? = null,
    val withheld: Withheld? = null
) : AndroidParcelable, JvmSerializable {

    val createdAt: LocalDateTime? get() = _createdAt?.isoToLocalDateTime()

    /**
     * Entities
     *
     * @property url
     * @property description
     * @constructor Create empty Entities
     */
    @AndroidParcelize
    @Serializable
    public data class Entities(
        val url: EntitiesUrl? = null,
        val description: Description? = null
    ) : AndroidParcelable, JvmSerializable {

        /**
         * Entities url
         *
         * @property urls
         * @constructor Create empty Entities url
         */
        @AndroidParcelize
        @Serializable
        public data class EntitiesUrl(
            val urls: List<Url>
        ) : AndroidParcelable, JvmSerializable

        /**
         * Description
         *
         * @property urls
         * @property hashtags
         * @property mentions
         * @property cashtags
         * @constructor Create empty Description
         */
        @AndroidParcelize
        @Serializable
        public data class Description(
            val urls: List<Url>? = null,
            val hashtags: List<Hashtag>? = null,
            val mentions: List<Mention>? = null,
            val cashtags: List<CashTag>? = null
        ) : AndroidParcelable, JvmSerializable

        /**
         * Url
         *
         * @property start
         * @property end
         * @property url
         * @property expandedUrl
         * @property displayUrl
         * @constructor Create empty Url
         */
        @AndroidParcelize
        @Serializable
        public data class Url(
            override val start: Int,
            override val end: Int,
            val url: String,
            @SerialName("expanded_url")
            val expandedUrl: String? = null,
            @SerialName("display_url")
            val displayUrl: String? = null,
        ) : TextEntity(), AndroidParcelable, JvmSerializable
    }

    /**
     * Public metrics
     *
     * @property followersCount
     * @property followingCount
     * @property tweetCount
     * @property listedCount
     * @constructor Create empty Public metrics
     */
    @AndroidParcelize
    @Serializable
    public data class PublicMetrics(
        @SerialName("followers_count")
        val followersCount: Int,
        @SerialName("following_count")
        val followingCount: Int,
        @SerialName("tweet_count")
        val tweetCount: Int,
        @SerialName("listed_count")
        val listedCount: Int,
    ) : AndroidParcelable, JvmSerializable
}
