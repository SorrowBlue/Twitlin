package com.sorrowblue.twitlin.v2.tweets.search.impl

import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.objects.toParameter
import com.sorrowblue.twitlin.v2.Endpoints.TWEETS_SEARCH
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.field.toParameter
import com.sorrowblue.twitlin.v2.objects.PagingData
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.tweets.Expansion
import com.sorrowblue.twitlin.v2.tweets.search.AddedSearchStreamRules
import com.sorrowblue.twitlin.v2.tweets.search.DeletedSearchStreamRules
import com.sorrowblue.twitlin.v2.tweets.search.RuleId
import com.sorrowblue.twitlin.v2.tweets.search.SearchStreamRule
import com.sorrowblue.twitlin.v2.tweets.search.SearchStreamRules
import com.sorrowblue.twitlin.v2.tweets.search.TweetsSearchApi
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.encodeToISOString

internal class TweetsSearchApiImpl(private val client: TwitterClient) : TweetsSearchApi {

    override fun stream(
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Flow<Response<Tweet>> {
        return client.streaming("$TWEETS_SEARCH/stream", Response.serializer(Tweet.serializer())) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", expansions?.toParameter())
            parameter("place.fields", expansions?.toParameter())
            parameter("poll.fields", expansions?.toParameter())
            parameter("tweet.fields", expansions?.toParameter())
            parameter("user.fields", expansions?.toParameter())
        }
    }

    override suspend fun streamRules(ids: List<RuleId>): Response<SearchStreamRules> {
        return client.get("$TWEETS_SEARCH/stream/rules", Response.serializer(SearchStreamRules.serializer())) {
            parameter("ids", ids.toParameter())
        }
    }

    override suspend fun addStreamRules(
        rules: List<SearchStreamRule>,
        dryRun: Boolean
    ): Response<AddedSearchStreamRules> {
        return client.post("$TWEETS_SEARCH/stream/rules", Response.serializer(AddedSearchStreamRules.serializer())) {
            parameter("dry_run", dryRun)
            contentType(ContentType.Application.Json)
            body = AddSearchStreamRuleRequest(rules)
        }
    }

    override suspend fun deleteStreamRules(
        ids: List<RuleId>,
        dryRun: Boolean
    ): Response<DeletedSearchStreamRules> {
        return client.post("$TWEETS_SEARCH/stream/rules", Response.serializer(DeletedSearchStreamRules.serializer())) {
            parameter("dry_run", dryRun)
            contentType(ContentType.Application.Json)
            body = DeleteSearchStreamRuleRequest(ids)
        }
    }

    override suspend fun all(
        query: String,
        maxResults: Int,
        nextToken: String?,
        sinceId: TweetId?,
        untilId: TweetId?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> {
        return client.get("$TWEETS_SEARCH/all", Response.serializer(PagingData.serializer(Tweet.serializer()))) {
            parameter("query", query)
            parameter("max_results", maxResults)
            parameter("next_token", nextToken)
            parameter("since_id", sinceId?.id)
            parameter("until_id", untilId?.id)
            parameter("start_time", startTime?.encodeToISOString())
            parameter("end_time", endTime?.encodeToISOString())
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun recent(
        query: String,
        maxResults: Int,
        nextToken: String?,
        sinceId: TweetId?,
        untilId: TweetId?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> {
        return client.get("$TWEETS_SEARCH/recent", Response.serializer(PagingData.serializer(Tweet.serializer()))) {
            parameter("query", query)
            parameter("max_results", maxResults)
            parameter("next_token", nextToken)
            parameter("since_id", sinceId?.id)
            parameter("until_id", untilId?.id)
            parameter("start_time", startTime?.encodeToISOString())
            parameter("end_time", endTime?.encodeToISOString())
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }
}
