package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.TwitterTweet

interface StatusesApi {

	suspend fun homeTimeline(
		count: Int = 20,
		sinceId: Long? = null,
		maxId: Long? = null,
		trimUser: Boolean = false,
		excludeReplies: Boolean = false,
		includeEntities: Boolean = false,
		includeCard: Boolean = false
	): Response<List<TwitterTweet>>

	suspend fun userTimeline(
		userId: Long,
		sinceId: Long? = null,
		maxId: Long? = null,
		count: Int = 20,
		trimUser: Boolean = false,
		excludeReplies: Boolean = false,
		includeRetweet: Boolean = true,
		includeCard: Boolean = false
	): Response<List<TwitterTweet>>

	suspend fun userTimeline(
		screenName: String,
		sinceId: Long? = null,
		maxId: Long? = null,
		count: Int = 20,
		trimUser: Boolean = false,
		excludeReplies: Boolean = false,
		includeRetweet: Boolean = true,
		includeCard: Boolean = false
	): Response<List<TwitterTweet>>
}
