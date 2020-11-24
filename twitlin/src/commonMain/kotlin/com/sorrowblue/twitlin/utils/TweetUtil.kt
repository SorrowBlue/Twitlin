package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.net.clientEngine
import com.sorrowblue.twitlin.objects.TwitterCard
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

object TweetUtil {

    suspend fun twitterCard(url: String): TwitterCard? {
        val response = HttpClient(clientEngine).get<HttpResponse>(url)
        return if (response.status.isSuccess()) kotlin.runCatching {
            resolveCard(response.readText())
        }.getOrNull() else null
    }

    suspend fun tweetCardType(url: String): TweetCardType {
        val response = HttpClient(clientEngine).get<HttpResponse>(url)
        return if (response.status.isSuccess()) kotlin.runCatching {
            resolveTweetCardType(response.readText())
        }.getOrElse { TweetCardType.SUMMARY } else TweetCardType.SUMMARY
    }

}

expect fun resolveCard(source: String): TwitterCard?

expect fun resolveTweetCardType(source: String): TweetCardType

enum class TweetCardType {
    SUMMARY,
    SUMMARY_LARGE_IMAGE,
    APP,
    PLAYER
}
