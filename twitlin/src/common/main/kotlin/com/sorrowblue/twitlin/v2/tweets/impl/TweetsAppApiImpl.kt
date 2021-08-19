/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.impl

import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.v2.client.AppClient
import com.sorrowblue.twitlin.v2.client.Response
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
import com.sorrowblue.twitlin.v2.tweets.TweetsAppApi
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.builtins.ListSerializer

private const val TWEETS = "${Urls.V2}/tweets"

internal class TweetsAppApiImpl(private val appClient: AppClient) : TweetsAppApi {
    override suspend fun tweet(
        id: String,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<Tweet>> {
        return appClient.get(
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
    ): Response<OptionalData<List<Tweet>>> {
        return appClient.get(
            TWEETS,
            Response.serializer(OptionalData.serializer(ListSerializer(Tweet.serializer()))),
            "ids" to ids.joinToString(","),
            "expansions" to expansions?.toParameter(),
            "media.fields" to mediaFields?.toParameter(),
            "place.fields" to placeFields?.toParameter(),
            "poll.fields" to pollFields?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter(),
        )
    }

    override fun sampleStream(
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Flow<Response<OptionalData<Tweet>>> {
        return appClient.streaming(
            "$TWEETS/sample/stream",
            Response.serializer(OptionalData.serializer(Tweet.serializer())),
            "expansions" to expansions?.toParameter(),
            "media.fields" to mediaFields?.toParameter(),
            "place.fields" to placeFields?.toParameter(),
            "poll.fields" to pollFields?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun retweetedBy(
        tweetId: String,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<User>> {
        return appClient.get(
            "$TWEETS/$tweetId/retweeted_by",
            serializer = Response.serializer(OptionalData.serializer(User.serializer())),
            "expansions" to expansions?.toParameter(),
            "media.fields" to mediaFields?.toParameter(),
            "place.fields" to placeFields?.toParameter(),
            "poll.fields" to pollFields?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

}
