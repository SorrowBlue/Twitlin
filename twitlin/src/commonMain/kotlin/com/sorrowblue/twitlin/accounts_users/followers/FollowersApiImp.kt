package com.sorrowblue.twitlin.accounts_users.followers

import com.sorrowblue.twitlin.accounts_users.UserIds
import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.PagingUser

const val FOLLOWERS = "${Urls._1_1}/followers"

internal class FollowersApiImp(private val client: Client) : FollowersApi {

	override suspend fun ids(
		userId: Long?,
		screenName: String?,
		cursor: Long,
		stringifyIds: Boolean,
		count: Int?
	): Response<UserIds> {
		val query: List<Pair<String, String>> =
			mutableListOf("cursor" to cursor.toString(), "stringifyIds" to stringifyIds.toString()).apply {
				count?.let { add("count" to it.toString()) }
				userId?.let { add("user_id" to it.toString()) }
				screenName?.let { add("screen_name" to it) }
			}
		return client.get("$FOLLOWERS/ids.json", query)
	}


	override suspend fun list(
		userId: Long?,
		screenName: String?,
		cursor: Long,
		count: Int,
		skipStatus: Boolean,
		includeUserEntities: Boolean
	): Response<PagingUser> {
		val query: List<Pair<String, String>> = mutableListOf(
			"cursor" to cursor.toString(),
			"count" to count.toString(),
			"skip_status" to skipStatus.toString(),
			"include_user_entities" to includeUserEntities.toString()
		).apply {
			userId?.let { add("user_id" to it.toString()) }
			screenName?.let { add("screen_name" to it) }
		}
		return client.get("$FOLLOWERS/list.json", query)
	}

}
