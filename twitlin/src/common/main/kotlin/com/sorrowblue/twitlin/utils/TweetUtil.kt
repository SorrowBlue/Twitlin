/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.core.clientEngineFactory
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.isSuccess

public object TweetUtil {

    public suspend fun tweetCardType(url: String): TweetCardType {
        val response = HttpClient(clientEngineFactory).get<HttpResponse>(url)
        return if (response.status.isSuccess()) kotlin.runCatching {
            resolveTweetCardType(response.readText())
        }.getOrElse { TweetCardType.SUMMARY } else TweetCardType.SUMMARY
    }
}

public expect fun resolveTweetCardType(source: String): TweetCardType

public enum class TweetCardType {
    SUMMARY,
    SUMMARY_LARGE_IMAGE,
    APP,
    PLAYER;

    public companion object {
        public fun parse(value: String): TweetCardType =
            runCatching { valueOf(value.uppercase()) }.getOrDefault(SUMMARY)
    }
}
