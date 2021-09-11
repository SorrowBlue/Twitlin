/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.authentication.OAuthApi
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.objects.OptionalData
import com.sorrowblue.twitlin.v2.objects.PagingData
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import kotlinx.datetime.LocalDateTime
import com.sorrowblue.twitlin.v2.field.Expansion as FieldExpansion

public interface UsersApi {

    public suspend fun users(
        id: String,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<User>>

    public suspend fun users(
        ids: List<String>,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<List<User>>>

    public suspend fun byUsername(
        username: String,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<User>>

    public suspend fun byUsername(
        usernames: List<String>,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<List<User>>>

    /**
     * Returns Tweets mentioning a single user specified by the requested user ID. By default, the most recent ten
     * Tweets are returned per request. Using pagination, up to the most recent 800 Tweets can be retrieved.
     *
     * The Tweets returned by this endpoint count towards the Project-level [Tweet cap](https://developer.twitter.com/en/docs/projects/overview#tweet-cap).
     *
     * @param id Unique identifier of the user for whom to return Tweets mentioning the user. User ID can be referenced
     * using the [com.sorrowblue.twitlin.v2.users.UsersApi.users] endpoint. More information on Twitter IDs is [here](https://developer.twitter.com/en/docs/twitter-ids).
     * @param endTime YYYY-MM-DDTHH:mm:ssZ (ISO 8601/RFC 3339). The new UTC timestamp from which the Tweets will be
     * provided. Timestamp is in second granularity and is inclusive (for example, 12:00:01 includes the first second of the minute).
     * Please note that this parameter does not support a millisecond value.
     * @param startTime YYYY-MM-DDTHH:mm:ssZ (ISO 8601/RFC 3339). The oldest UTC timestamp from which the Tweets will be provided. Timestamp is in second granularity and is inclusive (for example, 12:00:01 includes the first second of the minute).
     *
     * Please note that this parameter does not support a millisecond value.
     * @param maxResults Specifies the number of Tweets to try and retrieve, up to a maximum of 100 per distinct request.
     * By default, 10 results are returned if this parameter is not supplied. The minimum permitted value is 5. It is
     * possible to receive less than the max_results per request throughout the pagination process.
     * @param paginationToken This parameter is used to move forwards or backwards through 'pages' of results, based on the value of the next_token or previous_token in the response. The value used with the parameter is pulled directly from the response provided by the API, and should not be modified.
     * @param sinceId Returns results with a Tweet ID greater than (that is, more recent than) the specified 'since' Tweet ID. There are limits to the number of Tweets that can be accessed through the API. If the limit of Tweets has occurred since the since_id, the since_id will be forced to the oldest ID available. More information on Twitter IDs is here.
     * @param untilId Returns results with a Tweet ID less less than (that is, older than) the specified 'until' Tweet ID. There are limits to the number of Tweets that can be accessed through the API. If the limit of Tweets has occurred since the until_id, the until_id will be forced to the most recent ID available. More information on Twitter IDs is here.
     * @param expansions Expansions enable you to request additional data objects that relate to the originally returned
     * Tweets. Submit a list of desired expansions in a comma-separated list without spaces. The ID that represents
     * the expanded data object will be included directly in the Tweet data object, but the expanded object metadata
     * will be returned within the includes response object, and will also include the ID so that you can match this
     * data object to the original Tweet object.
     *
     * The following data objects can be expanded using this parameter:
     * * The Tweet author's user object
     * * The user object of the Tweet’s author that the original Tweet is responding to
     * * Any mentioned users’ object
     * * Any referenced Tweets’ author’s user object
     * * Attached media’s object
     * * Attached poll’s object
     * * Attached place’s object
     * * Any referenced Tweets’ object
     * @param mediaFields This fields parameter enables you to select which specific media fields will deliver in each returned Tweet. Specify the desired fields in a comma-separated list without spaces between commas and fields. The Tweet will only return media fields if the Tweet contains media and if you've also included the expansions=attachments.media_keys query parameter in your request. While the media ID will be located in the Tweet object, you will find this ID and all additional media fields in the includes data object.
     * @param placeFields This fields parameter enables you to select which specific place fields will deliver in each returned Tweet. Specify the desired fields in a comma-separated list without spaces between commas and fields. The Tweet will only return place fields if the Tweet contains a place and if you've also included the expansions=geo.place_id query parameter in your request. While the place ID will be located in the Tweet object, you will find this ID and all additional place fields in the includes data object.
     * @param pollFields This fields parameter enables you to select which specific poll fields will deliver in each returned Tweet. Specify the desired fields in a comma-separated list without spaces between commas and fields. The Tweet will only return poll fields if the Tweet contains a poll and if you've also included the expansions=attachments.poll_ids query parameter in your request. While the poll ID will be located in the Tweet object, you will find this ID and all additional poll fields in the includes data object.
     * @param tweetFields This fields parameter enables you to select which specific Tweet fields will deliver in each returned Tweet object. Specify the desired fields in a comma-separated list without spaces between commas and fields. You can also pass the expansions=referenced_tweets.id expansion to return the specified fields for both the original Tweet and any included referenced Tweets. The requested Tweet fields will display in both the original Tweet data object, as well as in the referenced Tweet expanded data object that will be located in the includes data object.
     * @param userFields This fields parameter enables you to select which specific user fields will deliver in each returned Tweet. Specify the desired fields in a comma-separated list without spaces between commas and fields. While the user ID will be located in the original Tweet object, you will find this ID and all additional user fields in the includes data object.
     *
     * You must also pass one of the user expansions to return the desired user fields:
     * * expansions=author_id
     * * expansions=entities.mentions.username
     * * expansions=in_reply_to_user_id
     * * expansions=referenced_tweets.id.author_id
     * @return Tweets mentioning a single user specified by the requested user ID.
     */
    public suspend fun mentions(
        id: String,
        endTime: LocalDateTime? = null,
        startTime: LocalDateTime? = null,
        maxResults: Int = 10,
        paginationToken: String? = null,
        sinceId: String? = null,
        untilId: String? = null,
        expansions: List<com.sorrowblue.twitlin.v2.field.Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<Tweet>>

    public suspend fun tweets(
        id: String,
        endTime: LocalDateTime? = null,
        startTime: LocalDateTime? = null,
        exclude: Exclude? = null,
        maxResults: Int = 10,
        paginationToken: String? = null,
        sinceId: String? = null,
        untilId: String? = null,
        expansions: List<FieldExpansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<Tweet>>

    public suspend fun blocking(
        id: String,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<User>>

    public suspend fun blocking(id: String, targetUserId: String): Response<Boolean>

    public suspend fun unBlocking(sourceUserId: String, targetUserId: String): Response<Boolean>

    /**
     * Causes the user ID identified in the path parameter to Like the target Tweet.
     *
     * @param id The user ID who you are liking a Tweet on behalf of. It must match your own user ID or that of an
     * authenticating user, meaning that you must pass the [OAuthApi.accessToken] associated with the user ID when
     * authenticating your request.
     * @param tweetId The ID of the Tweet that you would like the user `id` to Like.
     * @return
     */
    public suspend fun likes(id: String, tweetId: String): Response<Boolean>

    public suspend fun unLikes(id: String, tweetId: String): Response<Boolean>

    public suspend fun likedTweets(
        id: String,
        expansions: List<FieldExpansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        userFields: List<UserField>? = null,
        tweetFields: List<TweetField>? = null,
        pollFields: List<PollField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<Tweet>>

    public suspend fun following(
        id: String,
        expansions: List<Expansion>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<User>>

    public suspend fun following(id: String, targetUserId: String): Response<Following>

    public suspend fun unFollowing(sourceUserId: String, targetUserId: String): Response<Boolean>

    public suspend fun followers(
        id: String,
        expansions: List<Expansion>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<User>>

    public suspend fun deleteRetweet(id: String, sourceTweetId: String): Response<Boolean>

    public suspend fun retweet(id: String, tweetId: String): Response<Boolean>
}
