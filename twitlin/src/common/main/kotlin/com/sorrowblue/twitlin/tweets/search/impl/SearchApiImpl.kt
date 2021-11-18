package com.sorrowblue.twitlin.tweets.search.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.tweets.search.ResultType
import com.sorrowblue.twitlin.tweets.search.SearchApi
import com.sorrowblue.twitlin.tweets.search.SearchResults
import com.sorrowblue.twitlin.utils.API_SEARCH
import io.ktor.client.request.parameter
import kotlinx.datetime.LocalDateTime

internal class SearchApiImpl(private val client: TwitterClient) : SearchApi {

    override suspend fun tweets(
        q: String,
        geocode: List<String>?,
        lang: String?,
        local: String?,
        resultType: ResultType,
        count: Int,
        until: LocalDateTime?,
        sinceId: TweetId?,
        maxId: TweetId?,
        includeEntities: Boolean
    ): Response<SearchResults> {
        return client.get("$API_SEARCH/tweets.json", Response.serializer(SearchResults.serializer())) {
            parameter("q", q)
            parameter("geocode", geocode?.joinToString(","))
            parameter("lang", lang)
            parameter("local", local)
            parameter("result_type", resultType.name.lowercase())
            parameter("count", count)
            parameter("until", until)
            parameter("since_id", sinceId?.id)
            parameter("max_id", maxId?.id)
            parameter("include_entities", includeEntities)
        }
    }
}
