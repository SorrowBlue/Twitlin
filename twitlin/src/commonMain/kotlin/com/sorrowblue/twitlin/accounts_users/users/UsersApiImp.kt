package com.sorrowblue.twitlin.accounts_users.users

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.TwitterUser

internal class UsersApiImp(private val client: Client) : UsersApi {

	override suspend fun lookup(
		vararg screenNames: String,
		includeEntities: Boolean,
		tweetMode: UsersApi.Mode
	): Response<List<TwitterUser>> {
		return client.getList(
			"${Urls.USERS}/lookup.json", listOf(
				"screen_name" to screenNames.joinToString(","),
				"include_entities" to includeEntities.toString(),
				"tweet_mode" to tweetMode.value
			)
		)
	}

	override suspend fun lookup(
		vararg userIds: Long,
		includeEntities: Boolean,
		tweetMode: UsersApi.Mode
	): Response<List<TwitterUser>> {
		return client.getList(
			"${Urls.USERS}/lookup.json", listOf(
				"user_id" to userIds.joinToString(","),
				"include_entities" to includeEntities.toString(),
				"tweet_mode" to tweetMode.value
			)
		)
	}
}
