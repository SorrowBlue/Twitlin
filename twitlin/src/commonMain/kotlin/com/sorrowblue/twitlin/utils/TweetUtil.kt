package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.net.clientEngine
import com.sorrowblue.twitlin.objects.TwitterCard
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.isSuccess

object TweetUtil {

	suspend fun twitterCard(url: String): TwitterCard? {
		val response = HttpClient(clientEngine).get<HttpResponse>(url)
		return if (response.status.isSuccess()) kotlin.runCatching {
			resolveCard(response.readText())
		}.getOrNull() else null
	}

}

expect fun resolveCard(source: String): TwitterCard?
