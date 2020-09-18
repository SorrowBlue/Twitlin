package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.Client
import com.sorrowblue.twitlin.v2.Response
import com.sorrowblue.twitlin.v2.TWITTER_API_V2
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField
import com.sorrowblue.twitlin.v2.tweets.toParameter

private const val USERS_API = "$TWITTER_API_V2/users"

@TwitterAPIV2
internal class UsersApiImp(private val client: Client) : UsersApi {

	override suspend fun users(
		id: String,
		expansions: List<UsersApi.Expansion>?,
		tweetFields: List<TweetField>?,
		userFields: List<UserField>?,
	): Response<User> = client.get("$USERS_API/$id",
		"expansions" to expansions?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(),
		"user.fields" to userFields?.toParameter())

	override suspend fun users(
		ids: List<String>,
		expansions: List<UsersApi.Expansion>?,
		tweetFields: List<TweetField>?,
		userFields: List<UserField>?,
	): Response<List<User>> = client.get(USERS_API,
		"ids" to ids.joinToString(","),
		"expansions" to expansions?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(),
		"user.fields" to userFields?.toParameter())

	override suspend fun byUsername(
		username: String,
		expansions: List<UsersApi.Expansion>?,
		tweetFields: List<TweetField>?,
		userFields: List<UserField>?,
	): Response<User> = client.get("$USERS_API/by/username/$username",
		"expansions" to expansions?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(),
		"user.fields" to userFields?.toParameter())

	override suspend fun byUsername(
		usernames: List<String>,
		expansions: List<UsersApi.Expansion>?,
		tweetFields: List<TweetField>?,
		userFields: List<UserField>?,
	): Response<List<User>> = client.get("$USERS_API/by",
		"usernames" to usernames.joinToString(","),
		"expansions" to expansions?.toParameter(),
		"tweet.fields" to tweetFields?.toParameter(),
		"user.fields" to userFields?.toParameter())
}
