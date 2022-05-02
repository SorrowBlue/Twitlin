package com.sorrowblue.twitlin.api.objects

import com.sorrowblue.twitlin.api.objects.Tweet.ExtendedEntities
import com.sorrowblue.twitlin.api.objects.Tweet.FilterLevel
import com.sorrowblue.twitlin.api.objects.Tweet.WithheldScope
import com.sorrowblue.twitlin.api.tweets.favorites.FavoritesApi
import com.sorrowblue.twitlin.api.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.annotation.KotlinIgnoredOnParcel
import com.sorrowblue.twitlin.core.objects.TweetId
import com.sorrowblue.twitlin.core.objects.UserId
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.rfc822ToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Tweets are the basic atomic building block of all things Twitter. Tweets are also known as
 * “status updates.” The Tweet object has a long list of ‘root-level’ attributes, including
 * fundamental attributes such as [id], [createdAt], and [text]. Tweet objects are also the ‘parent’
 * object to several child objects. Tweet child objects include [user], [entities], and
 * [extendedEntities]. Tweets that are geo-tagged will have a [place] child object.
 *
 * @property createdAt LocalDate time when this Tweet was created.
 * @property id The string representation of the unique identifier for this Tweet.
 * See [Twitter IDs](https://developer.twitter.com/en/docs/twitter-ids) for more information.
 * @property text The actual UTF-8 text of the status update.
 * See [twitter-text](https://github.com/twitter/twitter-text/blob/master/rb/lib/twitter-text/regex.rb) for details on
 * what characters are currently considered valid.
 * @property truncated Indicates whether the value of the [text] parameter was truncated, for example,
 * as a result of a retweet exceeding the original Tweet text length limit of 140 characters.
 * Truncated text will end in ellipsis, like this `...` Since Twitter now rejects long Tweets vs
 * truncating them, the large majority of Tweets will have this set to `false` . Note that while
 * native retweets may have their toplevel `text` property shortened, the original text will be
 * available under the [retweetedStatus] object and the [truncated] parameter will be set to the
 * value of the original status (in most cases, `false`).
 * @property entities Entities which have been parsed out of the text of the Tweet. Additionally see [Entities].
 * @property source Utility used to post the Tweet, as an HTML-formatted string.
 * Tweets from the Twitter website have a source value of `web`.
 * @property inReplyToStatusId Nullable. If the represented Tweet is a reply,
 * this field will contain the integer representation of the original Tweet’s ID.
 * @property inReplyToUserId Nullable. If the represented Tweet is a reply, this field will contain
 * the integer representation of the original Tweet’s author ID. This will not necessarily always
 * be the user directly mentioned in the Tweet.
 * @property inReplyToScreenName Nullable. If the represented Tweet is a reply, this field will
 * contain the screen name of the original Tweet’s author.
 * @property user The user who posted this Tweet. See User data dictionary for complete list of
 * attributes.
 * @property coordinates Nullable. Represents the geographic location of this Tweet as reported by
 * the user or client application. The inner coordinates array is formatted as geoJSON
 * (longitude first, then latitude).
 * @property place Nullable When present, indicates that the tweet is associated (but not
 * necessarily originating from) a [Place].
 * @property quotedStatusId This field only surfaces when the Tweet is a quote Tweet. This field
 * contains the integer value Tweet ID of the quoted Tweet.
 * @property isQuoteStatus Indicates whether this is a Quoted Tweet.
 * @property quotedStatus This field only surfaces when the Tweet is a quote Tweet.
 * This attribute contains the Tweet object of the original Tweet that was quoted.
 * @property retweetedStatus Users can amplify the broadcast of Tweets authored by other users by
 * [StatusesApi.retweet]. Retweets can be distinguished from typical Tweets by the existence of a
 * [retweetedStatus] attribute. This attribute contains a representation of the original Tweet that
 * was retweeted. Note that retweets of retweets do not show representations of the intermediary
 * retweet, but only the original Tweet. (Users can also [StatusesApi.destroy] a retweet they
 * created by deleting their retweet.)
 * @property quoteCount Nullable. Indicates approximately how many times this Tweet has been quoted by Twitter users.
 * @property replyCount Number of times this Tweet has been replied to.
 * @property retweetCount Number of times this Tweet has been retweeted.
 * @property favouriteCount Nullable. Indicates approximately how many times this Tweet has been [FavoritesApi.create] by Twitter users.
 * @property extendedEntities When between one and four native photos or one video or one animated GIF are in Tweet,
 * contains an array 'media' metadata. This is also available in Quote Tweets. Additionally see [ExtendedEntities].
 * @property favorited Nullable. Indicates whether this Tweet has been liked by the authenticating user.
 * @property retweeted Indicates whether this Tweet has been Retweeted by the authenticating user.
 * @property possiblySensitive Nullable. This field only surfaces when a Tweet contains a link.
 * The meaning of the field doesn’t pertain to the Tweet content itself, but instead it is an
 * indicator that the URL contained in the Tweet may contain content or media identified as
 * sensitive content.
 * @property filterLevel Indicates the maximum value of the [filterLevel] parameter which may be
 * used and still stream this Tweet. So a value of [FilterLevel.MEDIUM] will be streamed on
 * [FilterLevel.NONE], [FilterLevel.LOW], and [FilterLevel.MEDIUM] streams.
 * @property lang Nullable. When present, indicates a [BCP 47](http://tools.ietf.org/html/bcp47)
 * language identifier corresponding to the machine-detected language of the Tweet text, or `und`
 * if no language could be detected. See more documentation
 * [HERE](https://developer.twitter.com/en/docs/twitter-api/v1/tweets/filter-realtime/guides/premium-operators).
 * @property matchingRules Present in filtered products such as Twitter Search and PowerTrack.
 * Provides the id and tag associated with the rule that matched the Tweet. With PowerTrack,
 * more than one rule can match a Tweet. See more documentation
 * [HERE](http://support.gnip.com/enrichments/matching_rules.html).
 * @property currentUserRetweet Perspectival Only surfaces on methods supporting the
 * include_my_retweet parameter, when set to true. Details the Tweet ID of the user’s own retweet
 * (if existent) of this Tweet.
 * @property scopes A set of key-value pairs indicating the intended contextual delivery of the containing Tweet.
 * Currently used by Twitter’s Promoted Products.
 * @property withheldCopyright When present and set to `true`, it indicates that this piece of content has been withheld
 * due to a [DMCA complaint](http://en.wikipedia.org/wiki/Digital_Millennium_Copyright_Act).
 * @property withheldInCountries When present, indicates a list of uppercase [two-letter country codes](http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)
 * this content is withheld from. Twitter supports the following non-country values for this field: `XX` - Content is
 * withheld in all countries `XY` - Content is withheld due to a DMCA request.
 * @property withheldScope When present, indicates whether the content being withheld is the [WithheldScope.STATUS] or a [WithheldScope.USER].
 */
@AndroidParcelize
@Serializable
public data class Tweet(
    @SerialName("created_at")
    internal val _createdAt: String,
    @SerialName("id_str")
    val id: TweetId,
    val text: String,
    val truncated: Boolean,
    val entities: Entities? = null,
    val source: String,
    @SerialName("in_reply_to_status_id_str")
    val inReplyToStatusId: TweetId?,
    @SerialName("in_reply_to_user_id_str")
    val inReplyToUserId: UserId?,
    @SerialName("in_reply_to_screen_name")
    val inReplyToScreenName: String?,
    val user: User? = null,
    val coordinates: Coordinates?,
    val place: Place? = null,
    @SerialName("quoted_status_id_str")
    val quotedStatusId: TweetId? = null,
    @SerialName("is_quote_status")
    val isQuoteStatus: Boolean,
    @SerialName("quoted_status")
    val quotedStatus: Tweet? = null,
    @SerialName("retweeted_status")
    val retweetedStatus: Tweet? = null,
    @SerialName("quote_count")
    val quoteCount: Int? = null,
    @SerialName("reply_count")
    val replyCount: Int? = null,
    @SerialName("retweet_count")
    val retweetCount: Int,
    @SerialName("favourite_count")
    val favouriteCount: Int? = null,
    @SerialName("extended_entities")
    val extendedEntities: ExtendedEntities? = null,
    val favorited: Boolean,
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
    val withheldScope: WithheldScope? = null
) : AndroidParcelable, JvmSerializable {

    @KotlinIgnoredOnParcel
    @Transient
    val createdAt: LocalDateTime = _createdAt.rfc822ToLocalDateTime()

    /**
     * Extended entities
     *
     * @property media
     */
    @AndroidParcelize
    @Serializable
    public data class ExtendedEntities(val media: List<Entities.Media>) : AndroidParcelable, JvmSerializable

    /**
     * Details the Tweet ID of the user’s own retweet (if existent) of this Tweet.
     *
     * @property id
     */
    @AndroidParcelize
    @Serializable
    public data class CurrentUserRetweet(
        @SerialName("id_str")
        val id: TweetId
    ) : AndroidParcelable, JvmSerializable

    /**
     * Indicates the maximum value of the [filter_level](https://developer.twitter.com/streaming/overview/request-parameters#filter_level)
     * parameter which may be used and still stream this Tweet.
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
     * Withheld scope
     */
    @Serializable
    public enum class WithheldScope {
        @SerialName("status")
        STATUS,

        @SerialName("user")
        USER
    }

    /**
     * Present in filtered products such as Twitter Search and PowerTrack.
     * Provides the id and tag associated with the rule that matched the Tweet. With PowerTrack, more than one rule can match a Tweet.
     * See more documentation [HERE](http://support.gnip.com/enrichments/matching_rules.html).
     *
     * @property tag
     * @property id
     */
    @AndroidParcelize
    @Serializable
    public data class Rule(
        val tag: String,
        @SerialName("id_str")
        val id: String
    ) : AndroidParcelable, JvmSerializable

    /**
     * Currently used by Twitter’s Promoted Products.
     *
     * @property followers
     */
    @AndroidParcelize
    @Serializable
    public data class Scopes(val followers: Boolean) : AndroidParcelable, JvmSerializable
}
