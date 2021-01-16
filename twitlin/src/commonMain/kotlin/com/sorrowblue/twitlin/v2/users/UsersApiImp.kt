/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.TWITTER_API_V2
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.client.UserClient
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.MediaField
import com.sorrowblue.twitlin.v2.tweets.PlaceField
import com.sorrowblue.twitlin.v2.tweets.PollField
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField
import com.sorrowblue.twitlin.v2.tweets.toParameter
import kotlinx.datetime.LocalDateTime

private const val USERS_API = "$TWITTER_API_V2/users"

internal class UsersApiImp(private val client: UserClient) : UsersApi {

    override suspend fun users(
        id: String,
        expansions: List<UsersApi.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<User> = client.get(
        "$USERS_API/$id",
        "expansions" to expansions?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )

    override suspend fun users(
        ids: List<String>,
        expansions: List<UsersApi.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<List<User>> = client.get(
        USERS_API,
        "ids" to ids.joinToString(","),
        "expansions" to expansions?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )

    override suspend fun byUsername(
        username: String,
        expansions: List<UsersApi.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<User> = client.get(
        "$USERS_API/by/username/$username",
        "expansions" to expansions?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )

    override suspend fun byUsername(
        usernames: List<String>,
        expansions: List<UsersApi.Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<List<User>> = client.get(
        "$USERS_API/by",
        "usernames" to usernames.joinToString(","),
        "expansions" to expansions?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )

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
    ): Response<List<Tweet>> = client.get(
        "$USERS_API/$id/tweets",
        "expansions" to expansions?.toParameter(),
        "media.fields" to mediaFields?.toParameter(),
        "place.fields" to placeFields?.toParameter(),
        "poll.fields" to pollFields?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )
}
