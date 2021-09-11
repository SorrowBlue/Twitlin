/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.users.impl

import com.sorrowblue.twitlin.v2.V2Endpoints.USERS
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.client.TwitterClient
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.field.toParameter
import com.sorrowblue.twitlin.v2.objects.OptionalData
import com.sorrowblue.twitlin.v2.objects.PagingData
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.users.Exclude
import com.sorrowblue.twitlin.v2.users.Expansion
import com.sorrowblue.twitlin.v2.users.Following
import com.sorrowblue.twitlin.v2.users.UsersApi
import com.sorrowblue.twitlin.v2.users.request.BlockingRequest
import com.sorrowblue.twitlin.v2.users.request.FollowingRequest
import com.sorrowblue.twitlin.v2.users.request.LikesRequest
import com.sorrowblue.twitlin.v2.users.request.RetweetRequest
import com.sorrowblue.twitlin.v2.users.response.BlockingResponse
import com.sorrowblue.twitlin.v2.users.response.FollowingResponse
import com.sorrowblue.twitlin.v2.users.response.LikesResponse
import com.sorrowblue.twitlin.v2.users.response.RetweetResponse
import com.sorrowblue.twitlin.v2.users.response.UnFollowingResponse
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.encodeToISOString
import kotlinx.serialization.builtins.ListSerializer
import com.sorrowblue.twitlin.v2.field.Expansion as FieldExpansion

internal class UsersApiImp(private val client: TwitterClient) : UsersApi {
    override suspend fun users(
        id: String,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<OptionalData<User>> =
        client.get("$USERS/$id", Response.serializer(OptionalData.serializer(User.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun users(
        ids: List<String>,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<OptionalData<List<User>>> =
        client.get(USERS, Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))) {
            parameter("ids", ids.joinToString(","))
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun byUsername(
        username: String,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<User>> =
        client.get("$USERS/by/username/$username", Response.serializer(OptionalData.serializer(User.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun byUsername(
        usernames: List<String>,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<List<User>>> =
        client.get("$USERS/by", Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))) {
            parameter("usernames", usernames.joinToString(","))
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun mentions(
        id: String,
        endTime: LocalDateTime?,
        startTime: LocalDateTime?,
        maxResults: Int,
        paginationToken: String?,
        sinceId: String?,
        untilId: String?,
        expansions: List<com.sorrowblue.twitlin.v2.field.Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> =
        client.get("$USERS/$id/mentions", Response.serializer(PagingData.serializer(Tweet.serializer()))) {
            parameter("end_time", endTime?.encodeToISOString())
            parameter("start_time", startTime?.encodeToISOString())
            parameter("max_results", maxResults)
            parameter("expansions", expansions?.toParameter())
            parameter("pagination_token", paginationToken)
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("since_id", sinceId)
            parameter("until_id", untilId)
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun tweets(
        id: String,
        endTime: LocalDateTime?,
        startTime: LocalDateTime?,
        exclude: Exclude?,
        maxResults: Int,
        paginationToken: String?,
        sinceId: String?,
        untilId: String?,
        expansions: List<com.sorrowblue.twitlin.v2.field.Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> =
        client.get("$USERS/$id/tweets", Response.serializer(PagingData.serializer(Tweet.serializer()))) {
            parameter("end_time", endTime?.encodeToISOString())
            parameter("exclude", exclude?.value)
            parameter("expansions", expansions?.toParameter())
            parameter("max_results", maxResults)
            parameter("media.fields", mediaFields?.toParameter())
            parameter("pagination_token", paginationToken)
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("since_id", sinceId)
            parameter("start_time", startTime?.encodeToISOString())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("until_id", untilId)
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun blocking(
        id: String,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
        maxResults: Int,
        paginationToken: String?,
    ): Response<PagingData<User>> =
        client.get("$USERS/$id/blocking", Response.serializer(PagingData.serializer(User.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }

    override suspend fun blocking(id: String, targetUserId: String): Response<Boolean> =
        client.post("$USERS/$id/blocking", Response.serializer(BlockingResponse.serializer())) {
            contentType(ContentType.Application.Json)
            body = BlockingRequest(targetUserId)
        }.convertData { it.data.blocking }

    override suspend fun unBlocking(sourceUserId: String, targetUserId: String): Response<Boolean> =
        client.delete("$USERS/$sourceUserId/blocking/$targetUserId", Response.serializer(BlockingResponse.serializer()))
            .convertData { it.data.blocking }

    override suspend fun likes(id: String, tweetId: String): Response<Boolean> =
        client.post("$USERS/$id/likes", Response.serializer(LikesResponse.serializer())) {
            contentType(ContentType.Application.Json)
            body = LikesRequest(tweetId)
        }.convertData { it.data.liked }

    override suspend fun unLikes(id: String, tweetId: String): Response<Boolean> =
        client.delete("$USERS/$id/likes/$tweetId", Response.serializer(LikesResponse.serializer()))
            .convertData { it.data.liked }

    override suspend fun likedTweets(
        id: String,
        expansions: List<FieldExpansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        userFields: List<UserField>?,
        tweetFields: List<TweetField>?,
        pollFields: List<PollField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<Tweet>> =
        client.get("$USERS/$id/liked_tweets", Response.serializer(PagingData.serializer(Tweet.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }

    override suspend fun following(
        id: String,
        expansions: List<Expansion>?,
        maxResults: Int,
        paginationToken: String?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<User>> =
        client.get("$USERS/$id/following", Response.serializer(PagingData.serializer(User.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun following(id: String, targetUserId: String): Response<Following> =
        client.post("$USERS/$id/following", Response.serializer(FollowingResponse.serializer())) {
            contentType(ContentType.Application.Json)
            body = FollowingRequest(targetUserId)
        }.convertData { it.data }

    override suspend fun unFollowing(sourceUserId: String, targetUserId: String): Response<Boolean> =
        client.delete(
            "$USERS/$sourceUserId/following/$targetUserId",
            Response.serializer(UnFollowingResponse.serializer())
        ).convertData { it.data.following }

    override suspend fun followers(
        id: String,
        expansions: List<Expansion>?,
        maxResults: Int,
        paginationToken: String?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<User>> =
        client.get("$USERS/$id/followers", Response.serializer(PagingData.serializer(User.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun deleteRetweet(id: String, sourceTweetId: String): Response<Boolean> =
        client.delete("$USERS/$id/retweets/$sourceTweetId", Response.serializer(RetweetResponse.serializer()))
            .convertData { it.data.retweeted }

    override suspend fun retweet(id: String, tweetId: String): Response<Boolean> {
        return client.post("$USERS/$id/retweets", Response.serializer(RetweetResponse.serializer())) {
            contentType(ContentType.Application.Json)
            body = RetweetRequest(tweetId)
        }.convertData { it.data.retweeted }
    }
}
