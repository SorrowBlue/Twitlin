/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.users.impl

import com.sorrowblue.twitlin.v2.TWITTER_API_V2
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.client.UserClient
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.field.toParameter
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.OptionalData
import com.sorrowblue.twitlin.v2.tweets.PagingData
import com.sorrowblue.twitlin.v2.users.request.LikesRequest
import com.sorrowblue.twitlin.v2.users.response.LikesResponse
import com.sorrowblue.twitlin.v2.users.UsersApi
import com.sorrowblue.twitlin.v2.users.request.BlockingRequest
import com.sorrowblue.twitlin.v2.users.response.BlockingResponse
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.builtins.ListSerializer

private const val USERS_API = "$TWITTER_API_V2/users"

internal class UsersApiImp(private val client: UserClient) : UsersApi {
    override suspend fun users(
        id: String,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<OptionalData<User>> {
        return client.get(
            "$USERS_API/$id",
            Response.serializer(OptionalData.serializer(User.serializer())),
            "expansions" to expansions?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun users(
        ids: List<String>,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<OptionalData<List<User>>> {
        return client.get(
            USERS_API,
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer()))),
            "ids" to ids.joinToString(","),
            "expansions" to expansions?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun byUsername(
        username: String,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<User>> {
        return client.get(
            "$USERS_API/by/username/$username",
            Response.serializer(OptionalData.serializer(User.serializer())),
            "expansions" to expansions?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun byUsername(
        usernames: List<String>,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<List<User>>> {
        return client.get(
            "$USERS_API/by",
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer()))),
            "usernames" to usernames.joinToString(","),
            "expansions" to expansions?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun tweets(
        id: String,
        endTime: LocalDateTime?,
        start_time: LocalDateTime?,
        exclude: String?,
        max_results: Int,
        pagination_token: String?,
        since_id: String?,
        until_id: String?,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<List<Tweet>>> {
        return client.get(
            "$USERS_API/$id/tweets",
            Response.serializer(OptionalData.serializer(ListSerializer(Tweet.serializer()))),
            "expansions" to expansions?.toParameter(),
            "media.fields" to mediaFields?.toParameter(),
            "place.fields" to placeFields?.toParameter(),
            "poll.fields" to pollFields?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun blocking(
        id: String,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
        maxResults: Int,
        paginationToken: String?,
    ): Response<PagingData<User>> {
        return client.get(
            "$USERS_API/$id/blocking",
            serializer = Response.serializer(PagingData.serializer(User.serializer())),
            "expansions" to expansions?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter(),
            "max_results" to maxResults,
            "pagination_token" to paginationToken
        )
    }

    override suspend fun blocking(id: String, targetUserId: String): Response<Boolean> {
        return client.postJson(
            "$USERS_API/$id/blocking",
            serializer = Response.serializer(BlockingResponse.serializer()),
            clazz = BlockingRequest(targetUserId)
        ).convertData { it.data.blocking }
    }

    override suspend fun unBlocking(sourceUserId: String, targetUserId: String): Response<Boolean> {
        return client.delete(
            "$USERS_API/$sourceUserId/blocking/$targetUserId",
            serializer = Response.serializer(BlockingResponse.serializer()),
        ).convertData { it.data.blocking }
    }

    override suspend fun likes(id: String, tweetId: String): Response<Boolean> {
        return client.postJson(
            "$USERS_API/$id/likes",
            LikesRequest(tweetId),
            serializer = Response.serializer(LikesResponse.serializer())
        ).convertData { it.data.liked }
    }

    override suspend fun unLikes(id: String, tweetId: String): Response<Boolean> {
        return client.delete(
            "$USERS_API/$id/likes/$tweetId",
            serializer = Response.serializer(LikesResponse.serializer())
        ).convertData { it.data.liked }
    }

    override suspend fun likedTweets(
        id: String,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        userFields: List<UserField>?,
        tweetFields: List<TweetField>?,
        pollFields: List<PollField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<Tweet>> {
        return client.get(
            "$USERS_API/$id/liked_tweets",
            serializer = Response.serializer(PagingData.serializer(Tweet.serializer())),
            "expansions" to expansions?.toParameter(),
            "media.fields" to mediaFields?.toParameter(),
            "place.fields" to placeFields?.toParameter(),
            "poll.fields" to pollFields?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter(),
            "max_results" to maxResults,
            "pagination_token" to paginationToken
        )
    }
}
