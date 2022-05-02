package com.sorrowblue.twitlin.api.v2.tweets.counts.impl

import com.sorrowblue.twitlin.api.v2.Endpoints.TWEETS_COUNTS
import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.tweets.counts.TweetCounts
import com.sorrowblue.twitlin.api.v2.tweets.counts.TweetsCountsApi
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.TweetId
import io.ktor.client.request.parameter
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.encodeToISOString

internal class TweetsCountsApiImpl(private val client: TwitterClient) : TweetsCountsApi {

    override suspend fun all(
        query: String,
        granularity: String?,
        nextToken: String?,
        sinceId: TweetId?,
        untilId: TweetId?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?
    ): Response<TweetCounts> {
        return client.get("$TWEETS_COUNTS/all", Response.serializer(TweetCounts.serializer())) {
            parameter("query", query)
            parameter("granularity", granularity)
            parameter("next_token", nextToken)
            parameter("since_id", sinceId?.id)
            parameter("until_id", untilId?.id)
            parameter("start_time", startTime?.encodeToISOString())
            parameter("end_time", endTime?.encodeToISOString())
        }
    }

    override suspend fun recent(
        query: String,
        granularity: String?,
        sinceId: TweetId?,
        untilId: TweetId?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?
    ): Response<TweetCounts> {
        return client.get("$TWEETS_COUNTS/recent", Response.serializer(TweetCounts.serializer())) {
            parameter("query", query)
            parameter("granularity", granularity)
            parameter("since_id", sinceId?.id)
            parameter("until_id", untilId?.id)
            parameter("start_time", startTime?.encodeToISOString())
            parameter("end_time", endTime?.encodeToISOString())
        }
    }
}
