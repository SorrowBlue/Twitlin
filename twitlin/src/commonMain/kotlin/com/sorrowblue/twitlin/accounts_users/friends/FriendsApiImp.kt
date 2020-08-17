package com.sorrowblue.twitlin.accounts_users.friends

import com.sorrowblue.twitlin.accounts_users.UserIds
import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.PagingTwitterUser

const val FRIENDS = "https://api.twitter.com/1.1/friends"

internal class FriendsApiImp(private val client: Client) : FriendsApi {
	override suspend fun ids(
		userId: Long,
		cursor: Long?,
		stringifyIds: Boolean?,
		count: Int?
	): Response<UserIds> = client.get(
		"$FRIENDS/ids.json",
		"user_id" to userId,
		"cursor" to cursor,
		"stringify_ids" to stringifyIds,
		"count" to count
	)

	override suspend fun ids(
		screenName: String,
		cursor: Long?,
		stringifyIds: Boolean?,
		count: Int?
	): Response<UserIds> = client.get(
		"$FRIENDS/ids.json",
		"screen_name" to screenName,
		"cursor" to cursor,
		"stringify_ids" to stringifyIds,
		"count" to count
	)

	override suspend fun list(
		userId: Long,
		cursor: Long?,
		count: Int?,
		skipStatus: Boolean?,
		includeUserEntities: Boolean?
	): Response<PagingTwitterUser> = client.get(
	"$FRIENDS/list.json",
		"user_id" to userId,
		"cursor" to cursor,
		"count" to count,
		"skipStatus" to skipStatus,
		"include_user_entities" to includeUserEntities,
	)

	override suspend fun list(
		screenName: String?,
		cursor: Long?,
		count: Int?,
		skipStatus: Boolean?,
		includeUserEntities: Boolean?
	): Response<PagingTwitterUser> = client.get(
		"$FRIENDS/list.json",
		"screen_name" to screenName,
		"cursor" to cursor,
		"count" to count,
		"skipStatus" to skipStatus,
		"include_user_entities" to includeUserEntities,
	)
}
