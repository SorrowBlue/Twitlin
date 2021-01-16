/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search.impl

import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.v2.client.AppClient
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.tweets.Expansion
import com.sorrowblue.twitlin.v2.tweets.MediaField
import com.sorrowblue.twitlin.v2.tweets.PagingTweet
import com.sorrowblue.twitlin.v2.tweets.PlaceField
import com.sorrowblue.twitlin.v2.tweets.PollField
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField
import com.sorrowblue.twitlin.v2.tweets.search.AddedSearchStreamRules
import com.sorrowblue.twitlin.v2.tweets.search.DeletedSearchStreamRules
import com.sorrowblue.twitlin.v2.tweets.search.SearchStreamRule
import com.sorrowblue.twitlin.v2.tweets.search.SearchStreamRules
import com.sorrowblue.twitlin.v2.tweets.search.TweetsSearchAppApi
import com.sorrowblue.twitlin.v2.tweets.search.request.AddSearchStreamRuleRequest
import com.sorrowblue.twitlin.v2.tweets.search.request.DeleteSearchStreamRuleRequest
import com.sorrowblue.twitlin.v2.tweets.toParameter
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.encodeToISOString

private const val SEARCH = "${Urls.V2}/tweets/search"

internal class TweetsSearchAppApiImpl(private val appClient: AppClient) : TweetsSearchAppApi {

    override fun stream(
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Flow<Response<Tweet>> = appClient.getStreaming(
        "$SEARCH/stream",
        "expansions" to expansions?.toParameter(),
        "media.fields" to expansions?.toParameter(),
        "place.fields" to expansions?.toParameter(),
        "poll.fields" to expansions?.toParameter(),
        "tweet.fields" to expansions?.toParameter(),
        "user.fields" to expansions?.toParameter()
    )

    override suspend fun streamRules(ids: List<String>?): Response<SearchStreamRules> =
        appClient.get("$SEARCH/stream/rules", "ids" to ids?.joinToString(","))

    override suspend fun addStreamRules(
        rules: List<SearchStreamRule>,
        dryRun: Boolean?
    ): Response<AddedSearchStreamRules> = appClient.postJson(
        "$SEARCH/stream/rules",
        AddSearchStreamRuleRequest(rules),
        "dry_run" to dryRun
    )

    override suspend fun deleteStreamRules(
        ids: List<String>,
        dryRun: Boolean?
    ): Response<DeletedSearchStreamRules> = appClient.postJson(
        "$SEARCH/stream/rules",
        DeleteSearchStreamRuleRequest(ids),
        "dry_run" to dryRun
    )

    override suspend fun all(
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
    ): Response<PagingTweet> = appClient.post(
        "$SEARCH/all",
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
    ): Response<PagingTweet> = appClient.get(
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
