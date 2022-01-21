package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.authentication.OAuthApi
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.objects.OptionalData
import com.sorrowblue.twitlin.v2.objects.OptionalListData
import com.sorrowblue.twitlin.v2.objects.PagingData
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.counts.TweetsCountsApi
import com.sorrowblue.twitlin.v2.tweets.impl.TweetOption
import com.sorrowblue.twitlin.v2.tweets.search.TweetsSearchApi
import com.sorrowblue.twitlin.v2.users.Exclude
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime
import com.sorrowblue.twitlin.v2.users.Expansion as UserExpansion

/**
 * TODO
 */
public interface TweetsApi {

    // Filtered stream

    /**
     * Filtered stream
     * Search Tweets
     *
     * @see [TweetsSearchApi]
     */
    public val search: TweetsSearchApi


    // Hide replies

    /**
     * Hides or unhides a reply to a Tweet.
     *
     * @param id Unique identifier of the Tweet to hide or unhide. The Tweet must belong to a conversation initiated by the authenticating user.
     * @param isHidden Indicates the action to perform. Specify `true` to hide the Tweet, `false` to unhide.
     * Trying to hide a Tweet that's already hidden (or unhide a Tweet that is not hidden) will result in a successful call.
     * If [isHidden] is not specified, it will be hidden.
     * @return `true` if the reply is visible, `false` if hidden.
     */
    public suspend fun hidden(id: TweetId, isHidden: Boolean = true): Response<Boolean>

    // Likes

    /**
     * Un likes
     *
     * @param userId
     * @param tweetId
     * @return
     */
    public suspend fun unLikes(userId: UserId, tweetId: TweetId): Response<Boolean>

    /**
     * Liking users
     *
     * @param id
     * @param expansions
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun likingUsers(
        id: TweetId,
        expansions: List<UserExpansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<User>>

    /**
     * Liked tweets
     *
     * @param id
     * @param expansions
     * @param mediaFields
     * @param placeFields
     * @param userFields
     * @param tweetFields
     * @param pollFields
     * @param maxResults
     * @param paginationToken
     * @return
     */
    public suspend fun likedTweets(
        id: UserId,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        userFields: List<UserField>? = null,
        tweetFields: List<TweetField>? = null,
        pollFields: List<PollField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<Tweet>>

    /**
     * Causes the user ID identified in the path parameter to Like the target Tweet.
     *
     * @param userId The user ID who you are liking a Tweet on behalf of. It must match your own user ID or that of an
     * authenticating user, meaning that you must pass the [OAuthApi.accessToken] associated with the user ID when
     * authenticating your request.
     * @param tweetId The ID of the Tweet that you would like the user `id` to Like.
     * @return
     */
    public suspend fun likes(userId: UserId, tweetId: TweetId): Response<Boolean>

    // Retweets

    /**
     * Un retweet
     *
     * @param userId
     * @param sourceTweetId
     * @return
     */
    public suspend fun unRetweet(userId: UserId, sourceTweetId: TweetId): Response<Boolean>

    /**
     * Retweeted by
     *
     * @param tweetId
     * @param expansions
     * @param mediaFields
     * @param placeFields
     * @param pollFields
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun retweetedBy(
        tweetId: TweetId,
        expansions: List<UserExpansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<User>>

    /**
     * Retweet
     *
     * @param userId
     * @param tweetId
     * @return
     */
    public suspend fun retweet(userId: UserId, tweetId: TweetId): Response<Boolean>

    // Sampled stream

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

    // Search Tweets
    /**
     * Defined in [search]
     */

    // Timelines

    /**
     * Mentions
     *
     * @param id
     * @param endTime
     * @param startTime
     * @param maxResults
     * @param paginationToken
     * @param sinceId
     * @param untilId
     * @param expansions
     * @param mediaFields
     * @param placeFields
     * @param pollFields
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun mentions(
        id: UserId,
        endTime: LocalDateTime? = null,
        startTime: LocalDateTime? = null,
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
    ): Response<PagingData<Tweet>>

    /**
     * Tweets
     *
     * @param id
     * @param endTime
     * @param startTime
     * @param exclude
     * @param maxResults
     * @param paginationToken
     * @param sinceId
     * @param untilId
     * @param expansions
     * @param mediaFields
     * @param placeFields
     * @param pollFields
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun tweets(
        id: UserId,
        endTime: LocalDateTime? = null,
        startTime: LocalDateTime? = null,
        exclude: Exclude? = null,
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
    ): Response<PagingData<Tweet>>

    // Tweet counts
    /**
     * Tweet counts
     *
     * @see [TweetsCountsApi]
     */
    public val tweetsCountsApi: TweetsCountsApi

    // Tweet lookup

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
        id: TweetId,
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
        ids: List<TweetId>,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<Tweet>>


    public suspend fun tweet(
        text: String? = null,
        forSuperFollowersOnly: Boolean = false,
        replySettings: Tweet.ReplySettings = Tweet.ReplySettings.EVERYONE,
        reply: TweetOption.Reply? = null,
        quoteTweetId: TweetId? = null,
        media: TweetOption.Media? = null,
        poll: TweetOption.Poll? = null,
        geo: TweetOption.Geo? = null,
        directMessageUserId: UserId? = null
    ): Response<OptionalData<Tweet>>

    public suspend fun delete(id: TweetId): Response<Boolean>
}
