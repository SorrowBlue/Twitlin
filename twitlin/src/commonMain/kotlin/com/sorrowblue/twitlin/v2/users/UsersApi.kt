package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.Response
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.Field
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField

@RequiresOptIn(level = RequiresOptIn.Level.WARNING)
annotation class TwitterAPIV2

@TwitterAPIV2
interface UsersApi {

	enum class Expansion(override val value: String) : Field {
		PINNED_TWEET_ID("pinned_tweet_id"),
		;

		companion object {
			fun all() = listOf(PINNED_TWEET_ID)
		}
	}

	suspend fun users(
		id: String,
		expansions: List<Expansion>? = null,
		tweetFields: List<TweetField>? = null,
		userFields: List<UserField>? = null,
	): Response<User>

	suspend fun users(
		ids: List<String>,
		expansions: List<Expansion>? = null,
		tweetFields: List<TweetField>? = null,
		userFields: List<UserField>? = null,
	): Response<List<User>>

	suspend fun byUsername(
		usernames: List<String>,
		expansions: List<Expansion>? = null,
		tweetFields: List<TweetField>? = null,
		userFields: List<UserField>? = null,
	): Response<List<User>>

	suspend fun byUsername(
		username: String,
		expansions: List<Expansion>? = null,
		tweetFields: List<TweetField>? = null,
		userFields: List<UserField>? = null,
	): Response<List<User>>
}
