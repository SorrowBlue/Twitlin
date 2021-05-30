/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.tweets.ResultType
import com.sorrowblue.twitlin.tweets.SearchApi
import com.sorrowblue.twitlin.tweets.SearchResults
import kotlinx.datetime.LocalDateTime

private const val SEARCH = "${Urls.V1}/search"

internal class SearchApiImpl(private val client: UserClient) : SearchApi {
    override suspend fun tweets(
        q: String,
        geocode: List<String>?,
        lang: String?,
        local: String?,
        resultType: ResultType,
        count: Int,
        until: LocalDateTime?,
        sinceId: String?,
        maxId: String?,
        includeEntities: Boolean
    ): Response<SearchResults> {
        return client.get(
            "$SEARCH/tweets.json",
            Response.serializer(SearchResults.serializer()),
            "q" to q,
            "geocode" to geocode?.joinToString(","),
            "lang" to lang,
            "local" to local,
            "result_type" to resultType.name.lowercase(),
            "count" to count,
            "until" to until,
            "since_id" to sinceId,
            "max_id" to maxId,
            "include_entities" to includeEntities
        )
    }
}
