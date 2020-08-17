package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.TwitterTweet

interface StatusesApi {

	suspend fun homeTimeline(
		count: Int = 20,
		sinceId: Long? = null,
		maxId: Long? = null,
		trimUser: Boolean? = null,
		excludeReplies: Boolean? = null,
		includeEntities: Boolean? = null,
		includeCard: Boolean? = null
	): Response<List<TwitterTweet>>

	suspend fun userTimeline(
		userId: Long,
		sinceId: Long? = null,
		maxId: Long? = null,
		count: Int? = null,
		trimUser: Boolean? = null,
		excludeReplies: Boolean? = null,
		includeRetweet: Boolean? = null,
		includeCard: Boolean? = null
	): Response<List<TwitterTweet>>

	suspend fun userTimeline(
		screenName: String,
		sinceId: Long? = null,
		maxId: Long? = null,
		count: Int? = null,
		trimUser: Boolean? = null,
		excludeReplies: Boolean? = null,
		includeRetweet: Boolean? = null,
		includeCard: Boolean? = null
	): Response<List<TwitterTweet>>

	suspend fun lookup(id: List<Long>): Response<List<TwitterTweet>>
}
