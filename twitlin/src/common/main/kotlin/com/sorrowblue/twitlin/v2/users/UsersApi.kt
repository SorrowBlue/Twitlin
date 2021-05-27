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
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.OptionalData
import com.sorrowblue.twitlin.v2.tweets.PagingData
import kotlinx.datetime.LocalDateTime

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

    public suspend fun tweets(
        id: String,
        endTime: LocalDateTime? = null,
        start_time: LocalDateTime? = null,
        exclude: String? = null,
        max_results: Int = 10,
        pagination_token: String? = null,
        since_id: String? = null,
        until_id: String? = null,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<List<Tweet>>>

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
        expansions: List<com.sorrowblue.twitlin.v2.field.Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        userFields: List<UserField>? = null,
        tweetFields: List<TweetField>? = null,
        pollFields: List<PollField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<Tweet>>
}
