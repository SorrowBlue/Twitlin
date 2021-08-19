/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.objects.OptionalData
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import kotlinx.coroutines.flow.Flow

public interface TweetsAppApi {

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
     * Streams about 1% of all Tweets in real-time.
     *
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
     * @return About 1% of all Tweets.
     */
    public fun sampleStream(
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Flow<Response<OptionalData<Tweet>>>

    public suspend fun retweetedBy(
        tweetId: String,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<User>>
}
