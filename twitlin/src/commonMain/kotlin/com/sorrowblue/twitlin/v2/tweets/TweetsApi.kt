/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.client.Error
import com.sorrowblue.twitlin.v2.client.Includes
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.objects.Tweet
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class OptionalData<T>(
    val data: T,
    val includes: Includes? = null,
    val errors: List<Error>? = null,
) : JvmSerializable

@Serializable
public data class PagingTweet(
    val data: List<Tweet>,
    val includes: Includes? = null,
    val meta: Meta,
    val errors: List<Error>? = null
) : JvmSerializable {
    @Serializable
    public data class Meta(
        @SerialName("oldest_id")
        val oldestId: String,
        @SerialName("newest_id")
        val newestId: String,
        @SerialName("result_count")
        val resultCount: Int,
        @SerialName("next_token")
        val nextToken: String
    ) : JvmSerializable

}

/**
 * TODO
 *
 */
public interface TweetsApi {

    /**
     * Returns a variety of information about a single Tweet specified by the requested ID.
     *
     * @param id Unique identifier of the Tweet to request.
     * @param expansions List of extensions. [Expansion] enable requests to expand an ID into a full object in the `includes` response object.
     * @param mediaFields List of additional fields to return in the [Media](com.sorrowblue.twitlin.v2.objects.Media) object.
     * The response will contain the selected fields only if a Tweet contains media attachments.
     * @param placeFields List of additional fields to return in the [Place](com.sorrowblue.twitlin.v2.objects.Place) object.
     * The response will contain the selected fields only if location data is present in any of the response objects.
     * @param pollFields List of additional fields to return in the [Poll](com.sorrowblue.twitlin.v2.objects.Poll) object.
     * The response will contain the selected fields only if a Tweet contains a poll.
     * @param tweetFields List of additional fields to return in the [Tweet] object. By default, the endpoint only returns `id` and `text`.
     * @param userFields List of additional fields to return in the [User](com.sorrowblue.twitlin.v2.objects.User) object.
     * By default, the endpoint does not return any user field. To use this parameter, you must include the [TweetField.AUTHOR_ID] expansion parameter in the request.
     * @return A single Tweet specified by the requested ID.
     */
    public suspend fun tweet(
        id: String,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<Tweet>>

    /**
     * Returns a variety of information about the Tweet specified by the requested ID or list of IDs.
     *
     * @param ids List of Tweet IDs. Up to 100 are allowed in one request.
     * @param expansions List of extensions. [Expansion] enable requests to expand an ID into a full object in the `includes` response object.
     * @param mediaFields List of additional fields to return in the [Media](com.sorrowblue.twitlin.v2.objects.Media) object.
     * The response will contain the selected fields only if a Tweet contains media attachments.
     * @param placeFields List of additional fields to return in the [Place](com.sorrowblue.twitlin.v2.objects.Place) object.
     * The response will contain the selected fields only if location data is present in any of the response objects.
     * @param pollFields List of additional fields to return in the [Poll](com.sorrowblue.twitlin.v2.objects.Poll) object.
     * The response will contain the selected fields only if a Tweet contains a poll.
     * @param tweetFields List of additional fields to return in the [Tweet] object. By default, the endpoint only returns `id` and `text`.
     * @param userFields List of additional fields to return in the [User](com.sorrowblue.twitlin.v2.objects.User) object.
     * By default, the endpoint does not return any user field. To use this parameter, you must include the [TweetField.AUTHOR_ID] expansion parameter in the request.
     * @return Tweet specified by the requested ID or list of IDs.
     */
    public suspend fun tweet(
        ids: List<String>,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<List<Tweet>>>

    /**
     * Hides or unhides a reply to a Tweet.
     *
     * @param id Unique identifier of the Tweet to hide or unhide. The Tweet must belong to a conversation initiated by the authenticating user.
     * @param isHidden Indicates the action to perform. Specify `true` to hide the Tweet, `false` to unhide.
     * Trying to hide a Tweet that's already hidden (or unhide a Tweet that is not hidden) will result in a successful call.
     * If [isHidden] is not specified, it will be hidden.
     * @return `true` if the reply is visible, `false` if hidden.
     */
    public suspend fun hidden(id: String, isHidden: Boolean = true): Response<Boolean>

    public suspend fun mentions(
        id: String,
        endTime: LocalDateTime,
        startTime: LocalDateTime,
        maxResults: Int = 10,
        paginationToken: String? = null,
        sinceId: String? = null,
        untilId: String? = null,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingTweet>
}
