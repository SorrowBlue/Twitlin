package com.sorrowblue.twitlin.accounts_users.lists

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.PagingUser

private const val LISTS = "${Urls._1_1}/lists"

internal class ListsApiImp(private val client: Client) : ListsApi {

	override suspend fun list(
		userId: Int?,
		screenName: String?,
		reverse: Boolean
	): Response<List<TwitterList>> {
		val params: List<Pair<String, String>> = mutableListOf("reverse" to reverse.toString()).apply {
			userId?.let { add("user_id" to it.toString()) }
			screenName?.let { add("screen_name" to it) }
		}
		return client.getList("$LISTS/list.json", params)
	}

	override suspend fun members(
		listId: Long,
		count: Int,
		cursor: Long,
		includeEntities: Boolean,
		skipStatus: Boolean
	): Response<PagingUser> {
		val params = listOf(
			"list_id" to listId.toString(),
			"count" to count.toString(),
			"cursor" to cursor.toString(),
			"include_entities" to includeEntities.toString(),
			"skip_status" to skipStatus.toString()
		)
		return client.get("$LISTS/members.json", params)
	}

	override suspend fun members(
		slug: String,
		ownerScreenName: String,
		ownerId: Long,
		count: Int,
		cursor: Long,
		includeEntities: Boolean,
		skipStatus: Boolean
	): Response<PagingUser> {
		val params = listOf(
			"slug" to slug,
			"owner_screen_name" to ownerScreenName,
			"owner_id" to ownerId.toString(),
			"count" to count.toString(),
			"cursor" to cursor.toString(),
			"include_entities" to includeEntities.toString(),
			"skip_status" to skipStatus.toString()
		)
		return client.get("$LISTS/members.json", params)
	}

	override suspend fun ownerships() {
		TODO("Not yet implemented")
	}

	override suspend fun subscriptions() {
		TODO("Not yet implemented")
	}
}

