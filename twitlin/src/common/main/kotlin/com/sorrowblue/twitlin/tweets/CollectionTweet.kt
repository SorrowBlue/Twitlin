/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.Coordinates
import com.sorrowblue.twitlin.objects.Entities
import com.sorrowblue.twitlin.objects.Place
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.rfc822ToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class CollectionTweet(
    @SerialName("created_at")
    val _createdAt: String,
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
    val lang: String
) : AndroidParcelable, JvmSerializable {

    val createdAt: LocalDateTime get() = _createdAt.rfc822ToLocalDateTime()

    @AndroidParcelize
    @Serializable
    public data class User(
        val id: Long,
        @SerialName("id_str")
        val idStr: String
    ) : AndroidParcelable, JvmSerializable
}
