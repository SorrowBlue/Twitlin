/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.serializers.LocalDateTimeRFC822Serializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class TwitterTweet(
    @Serializable(LocalDateTimeRFC822Serializer::class)
    @SerialName("created_at")
    val createdAt: LocalDateTime,
    val id: Long,
    @SerialName("id_str")
    val idStr: String,
    val text: String = "",
    @SerialName("full_text")
    val fullText: String = "",
    val source: String,
    val truncated: Boolean,
    @SerialName("in_reply_to_status_id")
    val inReplyToStatusId: Long? = null,
    @SerialName("in_reply_to_status_id_str")
    val inReplyToStatusIdStr: String? = null,
    @SerialName("in_reply_to_user_id")
    val inReplyToUserId: Long? = null,
    @SerialName("in_reply_to_user_id_str")
    val inReplyToUserIdStr: String? = null,
    @SerialName("in_reply_to_screen_name")
    val inReplyToScreenName: String? = null,
    val user: TwitterUser? = null,
    val coordinates: Coordinates? = null,
    val place: Place? = null,
    @SerialName("quoted_status_id")
    val quotedStatusId: Long? = null,
    @SerialName("quoted_status_id_str")
    val quotedStatusIdStr: String? = null,
    @SerialName("is_quote_status")
    val isQuoteStatus: Boolean,
    @SerialName("quoted_status")
    val quotedStatus: TwitterTweet? = null,
    @SerialName("retweeted_status")
    val retweetedStatus: TwitterTweet? = null,
    @SerialName("quote_count")
    val quoteCount: Int? = null,
    @SerialName("reply_count")
    val replyCount: Int? = null,
    @SerialName("retweet_count")
    val retweetCount: Int,
    @SerialName("favourite_count")
    val favouriteCount: Int? = null,
    val entities: Entities? = null,
    @SerialName("extended_entities")
    val extendedEntities: Entities? = null,
    val favorited: Boolean? = null,
    val retweeted: Boolean,
    @SerialName("possibly_sensitive")
    val possiblySensitive: Boolean? = null,
    @SerialName("filter_level")
    val filterLevel: FilterLevel? = null,
    val lang: String? = null,
    @SerialName("matching_rules")
    val matchingRules: List<Rule>? = null,
    @SerialName("current_user_retweet")
    val currentUserRetweet: CurrentUserRetweet? = null,
    val scopes: Scopes? = null,
    @SerialName("withheld_copyright")
    val withheldCopyright: Boolean? = null,
    @SerialName("withheld_in_countries")
    val withheldInCountries: List<String>? = null,
    @SerialName("withheld_scope")
    val withheldScope: String? = null,
    val card: TwitterCard? = null
) : JvmSerializable {

    /**
     * Details the Tweet ID of the user’s own retweet (if existent) of this Tweet.
     *
     * @since 0.0.1
     * @author SorrowBlue
     *
     * @property id
     * @property idStr
     */

    @Serializable
    public data class CurrentUserRetweet(
        val id: Long = -1,
        @SerialName("id_str") val idStr: String = ""
    ) : JvmSerializable

    /**
     * Indicates the maximum value of the [filter_level](https://developer.twitter.com/streaming/overview/request-parameters#filter_level)
     * parameter which may be used and still stream this Tweet.
     *
     * @since 0.0.1
     * @author SorrowBlue
     */
    @Serializable
    public enum class FilterLevel {
        @SerialName("low")
        LOW,

        @SerialName("medium")
        MEDIUM,

        @SerialName("none")
        NONE
    }

    /**
     * Present in filtered products such as Twitter Search and PowerTrack.
     * Provides the id and tag associated with the rule that matched the Tweet. With PowerTrack, more than one rule can match a Tweet.
     * See more documentation [HERE](http://support.gnip.com/enrichments/matching_rules.html).
     *
     * @since 0.0.1
     * @author SorrowBlue
     *
     * @property tag
     * @property id
     * @property idStr
     */

    @Serializable
    public data class Rule(
        val tag: String = "",
        val id: Long = -1,
        @SerialName("id_str") val idStr: String = ""
    ) : JvmSerializable

    /**
     * Currently used by Twitter’s Promoted Products.
     *
     * @since 0.0.1
     * @author SorrowBlue

     * @property followers
     */

    @Serializable
    public data class Scopes(val followers: Boolean = false) : JvmSerializable
}
