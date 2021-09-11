/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.impl

import com.sorrowblue.twitlin.v2.V2Endpoints
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.client.TwitterClient
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.field.toParameter
import com.sorrowblue.twitlin.v2.objects.OptionalData
import com.sorrowblue.twitlin.v2.objects.OptionalListData
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.request.HiddenRequest
import com.sorrowblue.twitlin.v2.tweets.response.HiddenResponse
import io.ktor.client.request.parameter
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import com.sorrowblue.twitlin.v2.users.Expansion as UsersExpansion

internal class TweetsApiImp(private val client: TwitterClient) : TweetsApi {

    override suspend fun tweet(
        id: String,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<Tweet>> =
        client.get("${V2Endpoints.TWEETS}/$id", Response.serializer(OptionalData.serializer(Tweet.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun tweet(
        ids: List<String>,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<Tweet>> =
        client.get(V2Endpoints.TWEETS, Response.serializer(OptionalListData.serializer(Tweet.serializer()))) {
            parameter("ids", ids.joinToString(","))
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override fun sampleStream(
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Flow<Response<OptionalData<Tweet>>> =
        client.streaming(
            "${V2Endpoints.TWEETS}/sample/stream",
            Response.serializer(OptionalData.serializer(Tweet.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun retweetedBy(
        id: String,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<User>> =
        client.get(
            "${V2Endpoints.TWEETS}/$id/retweeted_by",
            Response.serializer(OptionalListData.serializer(User.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun likingUsers(
        id: String,
        expansions: List<UsersExpansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<User>> =
        client.get(
            "${V2Endpoints.TWEETS}/$id/liking_users",
            Response.serializer(OptionalListData.serializer(User.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }

    override suspend fun hidden(id: String, isHidden: Boolean): Response<Boolean> =
        client.post("${V2Endpoints.TWEETS}/$id/hidden", Response.serializer(HiddenResponse.serializer())) {
            contentType(io.ktor.http.ContentType.Application.Json)
            body = HiddenRequest(isHidden)
        }.convertData { it.data.hidden }
}
