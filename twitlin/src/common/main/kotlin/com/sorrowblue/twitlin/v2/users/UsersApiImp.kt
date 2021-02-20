/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.TWITTER_API_V2
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.client.UserClient
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.field.toParameter
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.OptionalData
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.builtins.ListSerializer

private const val USERS_API = "$TWITTER_API_V2/users"

internal class UsersApiImp(private val client: UserClient) : UsersApi {
    override suspend fun users(
        id: String,
        expansions: List<UsersApi.Expansion>?,
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
        expansions: List<UsersApi.Expansion>?,
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
        expansions: List<UsersApi.Expansion>?,
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
        expansions: List<UsersApi.Expansion>?,
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
        expansions: List<UsersApi.Expansion>?,
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
}
