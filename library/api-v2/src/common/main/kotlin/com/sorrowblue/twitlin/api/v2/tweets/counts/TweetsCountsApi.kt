package com.sorrowblue.twitlin.api.v2.tweets.counts

import com.sorrowblue.twitlin.api.v2.TwitterAPIv2
import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.core.objects.TweetId
import kotlinx.datetime.LocalDateTime

/**
 * The v2 Tweet counts endpoints allow developers to understand and retrieve the volume of data for a given query.
 */
public interface TweetsCountsApi : TwitterAPIv2 {

    /**
     * The recent Tweet counts endpoint returns count of Tweets from the last seven days that match a search query.
     *
     * @param query One rule for matching Tweets. If you are using a [Standard Project](https://developer.twitter.com/en/docs/projects)
     * at the Basic [access level](https://developer.twitter.com/en/products/twitter-api/early-access/guide.html#na_1),
     * you can use the basic set of [operators](https://developer.twitter.com/en/docs/twitter-api/tweets/search/integrate/build-a-query)
     * and can make queries up to 512 characters long. If you are using an [Academic Research Project](https://developer.twitter.com/en/docs/projects)
     * at the Basic access level, you can use all available operators and can make queries up to 1,024 characters long.
     * @param granularity This is the granularity that you want the timeseries count data to be grouped by. The default
     * granularity, if not specified is hour.
     * @param sinceId Returns results with a Tweet ID greater than (that is, more recent than) the specified ID. The ID
     * specified is exclusive and responses will not include it. If included with the same request as a [startTime]
     * parameter, only [sinceId] will be used.
     * @param untilId Returns results with a Tweet ID less than (that is, older than) the specified ID. The ID specified
     * is exclusive and responses will not include it.
     * @param startTime YYYY-MM-DDTHH:mm:ssZ (ISO 8601/RFC 3339). The oldest UTC timestamp (from most recent seven days)
     * from which the Tweet counts will be provided. Timestamp is in second granularity and is inclusive (for example,
     * 12:00:01 includes the first second of the minute). If included with the same request as a [sinceId] parameter,
     * only [sinceId] will be used. By default, a request will return Tweet counts from up to seven days ago if you do
     * not include this parameter.
     * @param endTime YYYY-MM-DDTHH:mm:ssZ (ISO 8601/RFC 3339). The newest, most recent UTC timestamp to which the Tweet
     * counts will be provided. Timestamp is in second granularity and is exclusive (for example, 12:00:01 excludes the
     * first second of the minute). By default, a request will return Tweet counts from as recent as 30 seconds ago if
     * you do not include this parameter.
     * @return
     */
    public suspend fun recent(
        query: String,
        granularity: String? = null,
        sinceId: TweetId? = null,
        untilId: TweetId? = null,
        startTime: LocalDateTime? = null,
        endTime: LocalDateTime? = null
    ): Response<TweetCounts>

    /**
     * This endpoint is only available to those users who have been approved for the [Academic Research product track](https://developer.twitter.com/en/docs/projects/overview#product-track).
     *
     * The full-archive search endpoint returns the complete history of public Tweets matching a search query; since the
     * first Tweet was created March 26, 2006.
     *
     * @param query One rule for matching Tweets. If you are using a [Standard Project](https://developer.twitter.com/en/docs/projects)
     * at the Basic [access level](https://developer.twitter.com/en/products/twitter-api/early-access/guide.html#na_1),
     * you can use the basic set of [operators](https://developer.twitter.com/en/docs/twitter-api/tweets/search/integrate/build-a-query)
     * and can make queries up to 512 characters long. If you are using an [Academic Research Project](https://developer.twitter.com/en/docs/projects)
     * at the Basic access level, you can use all available operators and can make queries up to 1,024 characters long.
     * @param granularity This is the granularity that you want the timeseries count data to be grouped by. The default
     * granularity, if not specified is hour.
     * @param nextToken This parameter is used to get the next 'page' of results. The value used with the parameter is
     * pulled directly from the response provided by the API, and should not be modified. You can learn more by visiting
     * our page on [pagination](https://developer.twitter.com/en/docs/twitter-api/tweets/search/integrate/paginate).
     * @param sinceId Returns results with a Tweet ID greater than (that is, more recent than) the specified ID. The ID
     * specified is exclusive and responses will not include it. If included with the same request as a [startTime]
     * parameter, only [sinceId] will be used.
     * @param untilId Returns results with a Tweet ID less than (that is, older than) the specified ID. The ID specified
     * is exclusive and responses will not include it.
     * @param startTime YYYY-MM-DDTHH:mm:ssZ (ISO 8601/RFC 3339). The oldest UTC timestamp (from most recent seven days)
     * from which the Tweet counts will be provided. Timestamp is in second granularity and is inclusive (for example,
     * 12:00:01 includes the first second of the minute). If included with the same request as a [sinceId] parameter,
     * only [sinceId] will be used. By default, a request will return Tweet counts from up to seven days ago if you do
     * not include this parameter.
     * @param endTime YYYY-MM-DDTHH:mm:ssZ (ISO 8601/RFC 3339). The newest, most recent UTC timestamp to which the Tweet
     * counts will be provided. Timestamp is in second granularity and is exclusive (for example, 12:00:01 excludes the
     * first second of the minute). By default, a request will return Tweet counts from as recent as 30 seconds ago if
     * you do not include this parameter.
     * @return
     */
    public suspend fun all(
        query: String,
        granularity: String? = null,
        nextToken: String? = null,
        sinceId: TweetId? = null,
        untilId: TweetId? = null,
        startTime: LocalDateTime? = null,
        endTime: LocalDateTime? = null
    ): Response<TweetCounts>
}
