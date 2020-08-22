package com.sorrowblue.twitlin.accounts_users.lists

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.PagingTwitterUser
import com.sorrowblue.twitlin.objects.TwitterTweet
import com.sorrowblue.twitlin.objects.TwitterUser

private const val LISTS = "${Urls._1_1}/lists"

internal class ListsApiImp(private val client: Client) : ListsApi {

	override suspend fun list(
		userId: Int?,
		screenName: String?,
		reverse: Boolean?,
	): Response<List<TwitterList>> =
		client.get(
			"$LISTS/list.json",
			"user_id" to userId,
			"screen_name" to screenName,
			"reverse" to reverse
		)

	override suspend fun members(
		listId: Long,
		count: Int?,
		cursor: Long?,
		includeEntities: Boolean?,
		skipStatus: Boolean?,
	): Response<PagingTwitterUser> =
		client.get(
			"$LISTS/members.json",
			"list_id" to listId,
			"count" to count,
			"cursor" to cursor,
			"include_entities" to includeEntities,
			"skip_status" to skipStatus
		)

	override suspend fun members(
		slug: String,
		ownerScreenName: String?,
		ownerId: Long?,
		count: Int?,
		cursor: Long?,
		includeEntities: Boolean?,
		skipStatus: Boolean?,
	): Response<PagingTwitterUser> = client.get(
		"$LISTS/members.json",
		"slug" to slug,
		"owner_screen_name" to ownerScreenName,
		"owner_id" to ownerId,
		"count" to count,
		"cursor" to cursor,
		"include_entities" to includeEntities,
		"skip_status" to skipStatus
	)

	override suspend fun membersShow(
		listId: Long?,
		userId: Long?,
		includeEntities: Boolean?,
		skipStatus: Boolean?,
	): Response<TwitterUser> = client.get(
		"$LISTS/members/show.json",
		"list_id" to listId,
		"user_id" to userId,
		"include_entities" to includeEntities,
		"skip_status" to skipStatus
	)

	override suspend fun membersShow(
		slug: String,
		ownerScreenName: String?,
		ownerId: String?,
		includeEntities: Boolean?,
		skipStatus: Boolean?,
	): Response<TwitterUser> = client.get(
		"$LISTS/members/show.json",
		"slug" to slug,
		"owner_screen_name" to ownerScreenName,
		"owner_id" to ownerId,
		"include_entities" to includeEntities,
		"skip_status" to skipStatus
	)

	override suspend fun membersShow(
		screenName: String,
		includeEntities: Boolean?,
		skipStatus: Boolean?,
	): Response<TwitterUser> = client.get(
		"$LISTS/members/show.json",
		"screen_name" to screenName,
		"include_entities" to includeEntities,
		"skip_status" to skipStatus
	)

	override suspend fun memberships(
		userId: String?,
		screenName: String?,
		count: Long?,
		cursor: Long?,
		filterToOwnedLists: Boolean?,
	): Response<PagingTwitterList> = client.get(
		"$LISTS/memberships.json",
		"user_id" to userId,
		"screen_name" to screenName,
		"count" to count,
		"cursor" to cursor,
		"filter_to_owned_lists" to filterToOwnedLists
	)

	override suspend fun ownerships(
		userId: String?,
		screenName: String?,
		count: Long?,
		cursor: Long?,
	): Response<PagingTwitterList> = client.get(
		"$LISTS/ownerships.json",
		"user_id" to userId,
		"screen_name" to screenName,
		"count" to count,
		"cursor" to cursor
	)

	override suspend fun show(listId: Long): Response<TwitterList> =
		client.get("$LISTS/show.json", "list_id" to listId)

	override suspend fun show(
		slug: String,
		ownerScreenName: String?,
		ownerId: Long?,
	): Response<TwitterList> = client.get(
		"$LISTS/show.json",
		"slug" to slug,
		"owner_screen_name" to ownerScreenName,
		"owner_id" to ownerId
	)

	override suspend fun statuses(
		listId: Long,
		sinceId: Long?,
		maxId: Long?,
		count: Long?,
		includeEntities: Boolean?,
		includeRts: Boolean?
	): Response<List<TwitterTweet>> = client.get(
		"$LISTS/statuses.json",
		"list_id" to listId,
		"since_id" to sinceId,
		"max_id" to maxId,
		"count" to count,
		"include_entities" to includeEntities,
		"include_rts" to includeRts
	)

	override suspend fun statuses(
		slug: String,
		ownerScreenName: String?,
		ownerId: Long?,
		sinceId: Long?,
		maxId: Long?,
		count: Long?,
		includeEntities: Boolean?,
		includeRts: Boolean?
	): Response<List<TwitterTweet>> = client.get(
		"$LISTS/statuses.json",
		"slug" to slug,
		"owner_screen_name" to ownerScreenName,
		"owner_id" to ownerId,
		"since_id" to sinceId,
		"max_id" to maxId,
		"count" to count,
		"include_entities" to includeEntities,
		"include_rts" to includeRts
	)

	override suspend fun subscribers(
		listId: Long,
		count: Long?,
		cursor: Long?,
		includeEntities: Boolean?,
		skipStatus: Boolean?
	): Response<PagingTwitterUser> = client.get(
		"$LISTS/subscribers.json",
		"list_id" to listId,
		"count" to count,
		"cursor" to cursor,
		"include_entities" to includeEntities,
		"skip_status" to skipStatus
	)

	override suspend fun subscribers(
		slug: String,
		ownerScreenName: String?,
		ownerId: Long?,
		count: Long?,
		cursor: Long?,
		includeEntities: Boolean?,
		skipStatus: Boolean?
	): Response<PagingTwitterUser> = client.get(
		"$LISTS/subscribers.json",
		"slug" to slug,
		"owner_screen_name" to ownerScreenName,
		"owner_id" to ownerId,
		"count" to count,
		"cursor" to cursor,
		"include_entities" to includeEntities,
		"skip_status" to skipStatus
	)
}

