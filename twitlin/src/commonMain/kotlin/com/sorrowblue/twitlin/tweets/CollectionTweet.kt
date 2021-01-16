/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.objects.Coordinates
import com.sorrowblue.twitlin.objects.Entities
import com.sorrowblue.twitlin.objects.Place
import com.sorrowblue.twitlin.utilities.LanguageCode
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeRFC822Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class CollectionTweet(
    @Serializable(LocalDateTimeRFC822Serializer::class)
    @SerialName("created_at")
    val createdAt: LocalDateTime,
    val id: Long,
    @SerialName("id_str")
    val idStr: String,
    val text: String,
    val truncated: Boolean,
    val entities: Entities? = null,
    @SerialName("extended_entities")
    val extendedEntities: Entities? = null,
    val source: String,
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
    val user: User? = null,
    val geo: Unit? = null,
    val coordinates: Coordinates? = null,
    val place: Place? = null,
    val contributors: Unit? = null,
    @SerialName("is_quote_status")
    val isQuoteStatus: Boolean,
    @SerialName("retweet_count")
    val retweetCount: Int,
    @SerialName("favorite_count")
    val favouriteCount: Int,
    val favorited: Boolean,
    val retweeted: Boolean,
    @SerialName("possibly_sensitive")
    val possiblySensitive: Boolean,
    val lang: LanguageCode
) {

    @Serializable
    public data class User(
        val id: Long,
        @SerialName("id_str")
        val idStr: String
    )
}
