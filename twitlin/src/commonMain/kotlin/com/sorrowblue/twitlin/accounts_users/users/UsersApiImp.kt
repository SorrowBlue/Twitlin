package com.sorrowblue.twitlin.accounts_users.users

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.TwitterUser

internal class UsersApiImp(private val client: Client) : UsersApi {

	override suspend fun lookup(
		vararg screenNames: String,
		includeEntities: Boolean?,
		tweetMode: UsersApi.Mode?
	): Response<List<TwitterUser>> = client.get(
		"${Urls.USERS}/lookup.json",
		"screen_name" to screenNames.joinToString(","),
		"include_entities" to includeEntities,
		"tweet_mode" to tweetMode?.value
	)

	override suspend fun lookup(
		vararg userIds: Long,
		includeEntities: Boolean?,
		tweetMode: UsersApi.Mode?
	): Response<List<TwitterUser>> = client.get(
		"${Urls.USERS}/lookup.json",
		"user_id" to userIds.joinToString(","),
		"include_entities" to includeEntities,
		"tweet_mode" to tweetMode?.value
	)

	override suspend fun profileBanner(userId: Long): Response<ProfileBanner> =
		client.get("${Urls.USERS}/profile_banner.json", "user_id" to userId)
}
