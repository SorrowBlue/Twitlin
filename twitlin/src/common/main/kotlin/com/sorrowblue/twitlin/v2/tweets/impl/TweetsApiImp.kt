/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.impl

import com.sorrowblue.twitlin.core.Urls
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
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.request.HiddenRequest
import com.sorrowblue.twitlin.v2.tweets.response.HiddenResponse
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.encodeToISOString
import kotlinx.serialization.builtins.ListSerializer
import com.sorrowblue.twitlin.v2.users.Expansion as UsersExpansion

private const val TWEETS = "${Urls.V2}/tweets"
private const val USERS = "${Urls.V2}/users"

internal class TweetsApiImp(private val userClient: UserClient) : TweetsApi {

    override suspend fun tweet(
        id: String,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<Tweet>> {
        return userClient.get(
            "$TWEETS/$id",
            Response.serializer(OptionalData.serializer(Tweet.serializer())),
            "expansions" to expansions?.toParameter(),
            "media.fields" to mediaFields?.toParameter(),
            "place.fields" to placeFields?.toParameter(),
            "poll.fields" to pollFields?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun tweet(
        ids: List<String>,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<List<Tweet>>> = userClient.get(
        TWEETS,
        Response.serializer(OptionalData.serializer(ListSerializer(Tweet.serializer()))),
        "ids" to ids.joinToString(","),
        "expansions" to expansions?.toParameter(),
        "media.fields" to mediaFields?.toParameter(),
        "place.fields" to placeFields?.toParameter(),
        "poll.fields" to pollFields?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )

    override suspend fun hidden(id: String, isHidden: Boolean): Response<Boolean> =
        userClient.putJson(
            "$TWEETS/$id/hidden",
            Response.serializer(HiddenResponse.serializer()),
            HiddenRequest(isHidden)
        ).convertData { it.data.hidden }

    override suspend fun mentions(
        id: String,
        endTime: LocalDateTime?,
        startTime: LocalDateTime?,
        maxResults: Int,
        paginationToken: String?,
        sinceId: String?,
        untilId: String?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> {
        return userClient.get(
            "$USERS/$id/mentions",
            serializer = Response.serializer(PagingData.serializer(Tweet.serializer())),
            "end_time" to endTime?.encodeToISOString(),
            "start_time" to startTime?.encodeToISOString(),
            "max_results" to maxResults,
            "expansions" to expansions?.toParameter(),
            "pagination_token" to paginationToken,
            "media.fields" to mediaFields?.toParameter(),
            "place.fields" to placeFields?.toParameter(),
            "poll.fields" to pollFields?.toParameter(),
            "since_id" to sinceId,
            "until_id" to untilId,
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter(),
        )
    }

    override suspend fun likingUsers(
        id: String,
        expansions: List<UsersExpansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<User>> {
        return userClient.get(
            "$TWEETS/$id/liking_users",
            serializer = Response.serializer(PagingData.serializer(User.serializer())),
            "expansions" to expansions?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }
}
