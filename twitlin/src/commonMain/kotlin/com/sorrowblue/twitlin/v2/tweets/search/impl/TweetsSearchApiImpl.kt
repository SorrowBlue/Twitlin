/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search.impl

import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.client.UserClient
import com.sorrowblue.twitlin.v2.tweets.Expansion
import com.sorrowblue.twitlin.v2.tweets.MediaField
import com.sorrowblue.twitlin.v2.tweets.PagingTweet
import com.sorrowblue.twitlin.v2.tweets.PlaceField
import com.sorrowblue.twitlin.v2.tweets.PollField
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField
import com.sorrowblue.twitlin.v2.tweets.search.TweetsSearchApi
import com.sorrowblue.twitlin.v2.tweets.toParameter
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.encodeToISOString

private const val SEARCH = "${Urls.V2}/tweets/search"

internal class TweetsSearchApiImpl(val client: UserClient) : TweetsSearchApi {

    override suspend fun searchRecent(
        query: String,
        maxResults: Int?,
        nextToken: String?,
        sinceId: String?,
        startTime: LocalDateTime?,
        untilId: String?,
        endTime: LocalDateTime?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingTweet> = client.get(
        "$SEARCH/recent",
        "query" to query,
        "max_results" to maxResults,
        "next_token" to nextToken,
        "since_id" to sinceId,
        "until_id" to untilId,
        "start_time" to startTime?.encodeToISOString(),
        "end_time" to endTime?.encodeToISOString(),
        "expansions" to expansions?.toParameter(),
        "media.fields" to mediaFields?.toParameter(),
        "place.fields" to placeFields?.toParameter(),
        "poll.fields" to pollFields?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter()
    )
}
