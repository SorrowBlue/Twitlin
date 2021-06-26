package com.sorrowblue.twitlin.v2.tweets.counts.impl

import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.v2.client.AppClient
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.tweets.counts.TweetCounts
import com.sorrowblue.twitlin.v2.tweets.counts.TweetsCountsAppApi
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.encodeToISOString

private const val COUNTS = "${Urls.V2}/tweets/counts"

internal class TweetsCountsAppApiImpl(private val appClient: AppClient) : TweetsCountsAppApi {

    override suspend fun recent(
        query: String,
        granularity: String?,
        sinceId: String?,
        untilId: String?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?
    ): Response<TweetCounts> {
        return appClient.get(
            "$COUNTS/recent",
            Response.serializer(TweetCounts.serializer()),
            "query" to query,
            "granularity" to granularity,
            "since_id" to sinceId,
            "until_id" to untilId,
            "start_time" to startTime?.encodeToISOString(),
            "end_time" to endTime?.encodeToISOString(),
        )
    }

    override suspend fun all(
        query: String,
        granularity: String?,
        nextToken: String?,
        sinceId: String?,
        untilId: String?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?
    ): Response<TweetCounts> {
        return appClient.get(
            "$COUNTS/all",
            Response.serializer(TweetCounts.serializer()),
            "query" to query,
            "granularity" to granularity,
            "next_token" to nextToken,
            "since_id" to sinceId,
            "until_id" to untilId,
            "start_time" to startTime?.encodeToISOString(),
            "end_time" to endTime?.encodeToISOString(),
        )
    }
}
