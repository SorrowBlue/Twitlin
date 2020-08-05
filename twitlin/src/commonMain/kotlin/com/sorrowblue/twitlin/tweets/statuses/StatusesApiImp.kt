package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.objects.TwitterTweet
import com.sorrowblue.twitlin.utils.TweetUtil

private const val ROOT = "${Urls._1_1}/statuses"

internal class StatusesApiImp(private val client: Client) :
	StatusesApi {

	override suspend fun homeTimeline(
		count: Int,
		sinceId: Long?,
		maxId: Long?,
		trimUser: Boolean,
		excludeReplies: Boolean,
		includeEntities: Boolean,
		includeCard: Boolean
	): Response<List<TwitterTweet>> {
		val query: List<Pair<String, String>> = mutableListOf(
			"count" to count.toString(),
			"trim_user" to trimUser.toString(),
			"exclude_replies" to excludeReplies.toString(),
			"include_entities" to includeEntities.toString()
		).apply {
			sinceId?.let { add("since_id" to it.toString()) }
			maxId?.let { add("max_id" to it.toString()) }
		}
		return client.getList<TwitterTweet>("$ROOT/home_timeline.json", query).resolveCard(includeCard)
	}

	override suspend fun userTimeline(
		userId: Long,
		sinceId: Long?,
		maxId: Long?,
		count: Int,
		trimUser: Boolean,
		excludeReplies: Boolean,
		includeRetweet: Boolean,
		includeCard: Boolean
	): Response<List<TwitterTweet>> {
		val query: List<Pair<String, String>> = mutableListOf(
			"user_id" to userId.toString(),
			"count" to count.toString(),
			"trim_user" to trimUser.toString(),
			"exclude_replies" to excludeReplies.toString(),
			"include_rts" to includeRetweet.toString()
		).apply {
			sinceId?.let { add("since_id" to it.toString()) }
			maxId?.let { add("max_id" to it.toString()) }
		}
		return client.getList<TwitterTweet>("$ROOT/user_timeline.json", query).resolveCard(includeCard)
	}

	override suspend fun userTimeline(
		screenName: String,
		sinceId: Long?,
		maxId: Long?,
		count: Int,
		trimUser: Boolean,
		excludeReplies: Boolean,
		includeRetweet: Boolean,
		includeCard: Boolean
	): Response<List<TwitterTweet>> {
		val query: List<Pair<String, String>> = mutableListOf(
			"screen_name" to screenName,
			"count" to count.toString(),
			"trim_user" to trimUser.toString(),
			"exclude_replies" to excludeReplies.toString(),
			"include_rts" to includeRetweet.toString()
		).apply {
			sinceId?.let { add("since_id" to it.toString()) }
			maxId?.let { add("max_id" to it.toString()) }
		}
		return client.getList<TwitterTweet>("$ROOT/user_timeline.json", query).resolveCard(includeCard)
	}

	private suspend fun Response<List<TwitterTweet>>.resolveCard(includeCard: Boolean): Response<List<TwitterTweet>> {
		return getOrNull()?.map { value ->
			val tweet = Tweet.valueOf(value)
			if (tweet is Tweet.Normal && includeCard) {
				tweet.source.retweetedStatus?.let { twitterTweet ->
					twitterTweet.entities?.urls?.firstOrNull()?.expandedUrl
						?.let { TweetUtil.twitterCard(it) }
						?.let { value.copy(retweetedStatus = value.retweetedStatus?.copy(card = it)) } ?: value
				} ?: kotlin.run {
					tweet.source.entities?.urls?.firstOrNull()?.expandedUrl
						?.let { TweetUtil.twitterCard(it) }
						?.let { value.copy(card = it) } ?: value
				}
			} else value
		}?.let(Response.Companion::success) ?: this
	}
}
