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
		trimUser: Boolean?,
		excludeReplies: Boolean?,
		includeEntities: Boolean?,
		includeCard: Boolean?
	): Response<List<TwitterTweet>> = client.getList<TwitterTweet>(
		"$ROOT/home_timeline.json",
		"count" to count,
		"since_id" to sinceId,
		"max_id" to maxId,
		"trim_user" to trimUser,
		"exclude_replies" to excludeReplies,
		"include_entities" to includeEntities
	).resolveCard(includeCard == true)

	override suspend fun userTimeline(
		userId: Long,
		sinceId: Long?,
		maxId: Long?,
		count: Int?,
		trimUser: Boolean?,
		excludeReplies: Boolean?,
		includeRetweet: Boolean?,
		includeCard: Boolean?
	): Response<List<TwitterTweet>> = client.getList<TwitterTweet>(
		"$ROOT/user_timeline.json",
		"user_id" to userId,
		"since_id" to sinceId,
		"max_id" to maxId,
		"count" to count,
		"trim_user" to trimUser,
		"exclude_replies" to excludeReplies,
		"include_rts" to includeRetweet
	).resolveCard(includeCard == true)

	override suspend fun userTimeline(
		screenName: String,
		sinceId: Long?,
		maxId: Long?,
		count: Int?,
		trimUser: Boolean?,
		excludeReplies: Boolean?,
		includeRetweet: Boolean?,
		includeCard: Boolean?
	): Response<List<TwitterTweet>> = client.getList<TwitterTweet>(
		"$ROOT/user_timeline.json",
		"screenName" to screenName,
		"since_id" to sinceId,
		"max_id" to maxId,
		"count" to count,
		"trim_user" to trimUser,
		"exclude_replies" to excludeReplies,
		"include_rts" to includeRetweet
	).resolveCard(includeCard == true)

	override suspend fun lookup(id: List<Long>): Response<List<TwitterTweet>> = client.get<List<TwitterTweet>>("$ROOT/lookup.json", "id" to id.joinToString(","))

	private suspend fun Response<List<TwitterTweet>>.resolveCard(includeCard: Boolean): Response<List<TwitterTweet>> {
		return getOrNull()?.map { value ->
			val tweet = Tweet.valueOf(value)
			if (tweet is Tweet.Normal && includeCard) {
				tweet.source.retweetedStatus?.let { twitterTweet ->
					twitterTweet.entities?.urls?.firstOrNull()?.expandedUrl
						?.let { TweetUtil.twitterCard(it) }
						?.let { value.copy(retweetedStatus = value.retweetedStatus?.copy(card = it)) }
						?: value
				} ?: kotlin.run {
					tweet.source.entities?.urls?.firstOrNull()?.expandedUrl
						?.let { TweetUtil.twitterCard(it) }
						?.let { value.copy(card = it) } ?: value
				}
			} else value
		}?.let(Response.Companion::success) ?: this
	}
}
