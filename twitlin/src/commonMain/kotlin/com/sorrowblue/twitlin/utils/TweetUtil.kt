/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.client.clientEngineFactory
import com.sorrowblue.twitlin.objects.TwitterCard
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.isSuccess

public object TweetUtil {

    public suspend fun twitterCard(url: String): TwitterCard? {
        val response = HttpClient(clientEngineFactory).get<HttpResponse>(url)
        return if (response.status.isSuccess()) kotlin.runCatching {
            resolveCard(response.readText())
        }.getOrNull() else null
    }

    public suspend fun tweetCardType(url: String): TwitterCard.Type {
        val response = HttpClient(clientEngineFactory).get<HttpResponse>(url)
        return if (response.status.isSuccess()) kotlin.runCatching {
            resolveTweetCardType(response.readText())
        }.getOrElse { TwitterCard.Type.SUMMARY } else TwitterCard.Type.SUMMARY
    }
}

public expect fun resolveCard(source: String): TwitterCard

public expect fun resolveTweetCardType(source: String): TwitterCard.Type

public enum class TweetCardType {
    SUMMARY,
    SUMMARY_LARGE_IMAGE,
    APP,
    PLAYER
}


