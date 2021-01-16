/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.impl

import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.v2.client.AppClient
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.tweets.Expansion
import com.sorrowblue.twitlin.v2.tweets.MediaField
import com.sorrowblue.twitlin.v2.tweets.OptionalData
import com.sorrowblue.twitlin.v2.tweets.PlaceField
import com.sorrowblue.twitlin.v2.tweets.PollField
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.TweetsAppApi
import com.sorrowblue.twitlin.v2.tweets.UserField
import com.sorrowblue.twitlin.v2.tweets.toParameter
import kotlinx.coroutines.flow.Flow

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
    ): Response<OptionalData<Tweet>> = appClient.get(
        "$TWEETS/$id",
        "expansions" to expansions?.toParameter(),
        "media.fields" to mediaFields?.toParameter(),
        "place.fields" to placeFields?.toParameter(),
        "poll.fields" to pollFields?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )

    override suspend fun tweet(
        ids: List<String>,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<List<Tweet>>> = appClient.get(
        TWEETS,
        "ids" to ids.joinToString(","),
        "expansions" to expansions?.toParameter(),
        "media.fields" to mediaFields?.toParameter(),
        "place.fields" to placeFields?.toParameter(),
        "poll.fields" to pollFields?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter(),
    )

    override fun sampleStream(
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Flow<Response<Tweet>> = appClient.getStreaming(
        "$TWEETS/sample/stream",
        "expansions" to expansions?.toParameter(),
        "media.fields" to mediaFields?.toParameter(),
        "place.fields" to placeFields?.toParameter(),
        "poll.fields" to pollFields?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )
}
