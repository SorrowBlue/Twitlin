/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.utilities.LanguageCode
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Tweets are the basic building block of all things Twitter.
 * The Tweet object has a long list of ‘root-level’ fields, such as [id], [text], and [createdAt].
 * Tweet objects are also the ‘parent’ object to several child objects including `user`, `media`, `poll`, and `place`.
 * Use the field parameter `tweet.fields` when requesting these root-level fields on the Tweet object.
 * The Tweet object is the primary object returned on the following endpoints:
 *
 * * Tweet Lookup
 * * Recent Search
 * * Filtered Stream
 * * Sampled Stream
 *
 * The Tweet object that can be found and expanded in the user resource.
 * Additional Tweets related to the requested Tweet can also be found and expanded in the Tweet resource.
 * The object is available for expansion with [UserField.PINNED_TWEET_ID] in the user resource or
 * [Expansion.REFERENCED_TWEETS_ID] in the Tweet resource to get the object with only default fields.
 * Use the expansion with the field parameter: [TweetField] when requesting additional fields to complete the object.
 *
 * @property id The unique identifier of the requested Tweet.
 * @property text The actual UTF-8 text of the Tweet.
 * See [twitter-text](https://github.com/twitter/twitter-text/) for details on what characters are currently considered valid.
 * @property attachments Specifies the type of attachments (if any) present in this Tweet.
 * @property authorId The unique identifier of the User who posted this Tweet.
 * @property contextAnnotations Contains context annotations for the Tweet.
 * @property conversationId The Tweet ID of the original Tweet of the conversation (which includes direct replies, replies of replies).
 * @property createdAt Creation time of the Tweet.
 * @property entities Entities which have been parsed out of the text of the Tweet. Additionally see entities in Twitter Objects.
 * @property geo Contains details about the location tagged by the user in this Tweet, if they specified one.
 * @property inReplyToUserId If the represented Tweet is a reply, this field will contain the original Tweet’s author ID.
 * This will not necessarily always be the user directly mentioned in the Tweet.
 * @property lang Language of the Tweet, if detected by Twitter. Returned as a BCP47 language tag.
 * @property nonPublicMetrics Non-public engagement metrics for the Tweet at the time of the request.
 * Requires user context authentication.
 * @property organicMetrics Engagement metrics, tracked in an organic context, for the Tweet at the time of the request.
 * Requires user context authentication.
 * @property possibySensitive This field only surfaces when a Tweet contains a link.
 * The meaning of the field does not pertain to the Tweet content itself,
 * but instead it is an indicator that the URL contained in the Tweet may contain content or media identified as sensitive content.
 * @property promotedMetrics Engagement metrics, tracked in a promoted context, for the Tweet at the time of the request.
 * Requires user context authentication.
 * @property publicMetrics Public engagement metrics for the Tweet at the time of the request.
 * @property referencedTweets A list of Tweets this Tweet refers to.
 * For example, if the parent Tweet is a Retweet, a Retweet with comment (also known as Quoted Tweet) or a Reply,
 * it will include the related Tweet referenced to by its parent.
 * @property source The name of the app the user Tweeted from.
 * @property withheld When present, contains withholding details for [withheld content](https://help.twitter.com/en/rules-and-policies/tweet-withheld-by-country).
 */
@AndroidParcelize
@Serializable
public data class Tweet(
    val id: String,
    val text: String,
    val attachments: Attachments? = null,
    @SerialName("author_id") val authorId: String? = null,
    @SerialName("context_annotations") val contextAnnotations: List<ContextAnnotation>? = null,
    @SerialName("conversation_id") val conversationId: String? = null,
    @SerialName("created_at")
    val _createdAt: String? = null,
    val entities: Entities? = null,
    val geo: Geo? = null,
    @SerialName("in_reply_to_user_id") val inReplyToUserId: String? = null,
    val lang: LanguageCode? = null,
    @SerialName("non_public_metrics")
    val nonPublicMetrics: NonPublicMetrics? = null,
    @SerialName("organic_metrics")
    val organicMetrics: Metrics? = null,
    @SerialName("possibly_sensitive")
    val possibySensitive: Boolean? = null,
    @SerialName("promoted_metrics")
    val promotedMetrics: Metrics? = null,
    @SerialName("public_metrics")
    val publicMetrics: PublicMetrics? = null,
    @SerialName("referenced_tweets")
    val referencedTweets: List<ReferenceTweet>? = null,
    val source: String? = null,
    val withheld: Withheld? = null,
) : AndroidParcelable, JvmSerializable {

    val createdAt: LocalDateTime? get() = _createdAt?.isoToLocalDateTime()

    @AndroidParcelize
    @Serializable
    public data class Entities(
        val annotations: List<Annotation>? = null,
        val cashtags: List<CashTag>? = null,
        val hashtags: List<Hashtag>? = null,
        val mentions: List<Mention>? = null,
        val urls: List<Url>? = null,
    ) : AndroidParcelable, JvmSerializable {

        @AndroidParcelize
        @Serializable
        public data class Annotation(
            val start: Int,
            val end: Int,
            val probability: Float,
            val type: String,
            @SerialName("normalized_text")
            val normalizedText: String,
        ) : AndroidParcelable, JvmSerializable

        @AndroidParcelize
        @Serializable
        public data class Url(
            val start: Int,
            val end: Int,
            val url: String,
            @SerialName("expanded_url") val expandedUrl: String? = null,
            @SerialName("display_url") val displayUrl: String? = null,
            val images: List<Image>? = null,
            val status: Int? = null,
            val title: String? = null,
            val description: String? = null,
            @SerialName("unwound_url") val unwoundUrl: String? = null,
        ) : AndroidParcelable, JvmSerializable {

            @AndroidParcelize
            @Serializable
            public data class Image(
                val url: String,
                val width: Int,
                val height: Int
            ) : AndroidParcelable, JvmSerializable
        }
    }

    @AndroidParcelize
    @Serializable
    public data class PublicMetrics(
        @SerialName("like_count") val likeCount: Int,
        @SerialName("reply_count") val replyCount: Int,
        @SerialName("retweet_count") val retweetCount: Int,
        @SerialName("quote_count") val quoteCount: Int,
    ) : AndroidParcelable, JvmSerializable
}

@AndroidParcelize
@Serializable
public data class Mention(
    val start: Int,
    val end: Int,
    val username: String,
) : AndroidParcelable, JvmSerializable

@AndroidParcelize
@Serializable
public data class CashTag(
    val start: Int,
    val end: Int,
    val tag: String,
) : AndroidParcelable, JvmSerializable

@AndroidParcelize
@Serializable
public data class Hashtag(
    val start: Int,
    val end: Int,
    val tag: String,
) : AndroidParcelable, JvmSerializable

@AndroidParcelize
@Serializable
public data class Withheld(
    val copyright: Boolean,
    @SerialName("country_codes")
    val countryCodes: List<String>,
) : AndroidParcelable, JvmSerializable

@AndroidParcelize
@Serializable
public data class ReferenceTweet(
    val type: Type,
    val id: String,
) : AndroidParcelable, JvmSerializable {

    @Serializable
    public enum class Type {
        @SerialName("replied_to")
        REPLIED_TO,

        @SerialName("quoted")
        QUOTED,

        @SerialName("retweeted")
        RETWEETED
    }
}

@AndroidParcelize
@Serializable
public data class Attachments(
    @SerialName("poll_ids")
    val pollIds: List<String>? = null,
    @SerialName("media_keys")
    val media_keys: List<String>? = null
) : AndroidParcelable, JvmSerializable

@AndroidParcelize
@Serializable
public data class ContextAnnotation(
    val domain: Domain,
    val entity: Entity
) : AndroidParcelable, JvmSerializable {

    @AndroidParcelize
    @Serializable
    public data class Domain(
        val id: String,
        val name: String,
        val description: String
    ) : AndroidParcelable, JvmSerializable

    @AndroidParcelize
    @Serializable
    public data class Entity(
        val id: String,
        val name: String,
    ) : AndroidParcelable, JvmSerializable
}

@AndroidParcelize
@Serializable
public data class Metrics(
    @SerialName("impression_count") val impressionCount: Int,
    @SerialName("like_count") val likeCount: Int,
    @SerialName("reply_count") val replyCount: Int,
    @SerialName("retweet_count") val retweetCount: Int,
    @SerialName("url_link_clicks") val urlLinkClicks: Int,
    @SerialName("user_profile_clicks") val userProfileClicks: Int,
) : AndroidParcelable, JvmSerializable

@AndroidParcelize
@Serializable
public data class NonPublicMetrics(
    @SerialName("impression_count")
    val impressionCount: Int,
    @SerialName("urlLinkClicks")
    val urlLinkClicks: Int,
    @SerialName("user_profile_clicks")
    val userProfileClicks: Int,
) : AndroidParcelable, JvmSerializable

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

@AndroidParcelize
@Serializable
public data class Geo(
    val coordinates: Coordinates? = null,
    @SerialName("place_id")
    val placeId: String,
) : AndroidParcelable, JvmSerializable {

    @AndroidParcelize
    @Serializable
    public data class Coordinates(
        val type: String,
        val coordinates: List<Double>,
    ) : AndroidParcelable, JvmSerializable
}
