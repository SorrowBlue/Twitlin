/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.users.ListsApi
import com.sorrowblue.twitlin.users.PagingUser
import com.sorrowblue.twitlin.users.PagingUserList
import com.sorrowblue.twitlin.users.UserList

private const val LISTS = "${Urls.V1}/lists"

internal class ListsApiImpl(private val client: UserClient) : ListsApi {

    override suspend fun list(
        userId: String?,
        screenName: String?,
        reverse: Boolean
    ): Response<List<UserList>> = client.get(
        "$LISTS/list.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "reverse" to reverse
    )

    override suspend fun members(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        count: Int,
        cursor: String,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<PagingUser> = client.get(
        "$LISTS/members.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "count" to count,
        "cursor" to cursor,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus
    )

    override suspend fun showMembers(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        userId: String?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> = client.get(
        "$LISTS/members/show.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "user_id" to userId,
        "screen_name" to screenName,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus
    )

    override suspend fun memberships(
        userId: String?,
        screenName: String?,
        count: Int,
        cursor: String,
        filterToOwnedLists: Boolean
    ): Response<PagingUserList> = client.get(
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
        count: Int,
        cursor: String
    ): Response<PagingUserList> = client.get(
        "$LISTS/ownerships.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "count" to count,
        "cursor" to cursor
    )

    override suspend fun show(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?
    ): Response<UserList> = client.get(
        "$LISTS/show.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId
    )

    override suspend fun statuses(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        sinceId: String?,
        maxId: String?,
        count: Int,
        includeEntities: Boolean,
        includeRts: Boolean
    ): Response<List<Tweet>> = client.get(
        "$LISTS/statuses.json",
        "list_id" to listId,
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
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        count: Int,
        cursor: String,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<PagingUser> = client.get(
        "$LISTS/subscribers.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "count" to count,
        "cursor" to cursor,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus
    )

    override suspend fun showSubscribers(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        userId: String?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> = client.get(
        "$LISTS/subscribers/show.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "user_id" to userId,
        "screen_name" to screenName,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus
    )

    override suspend fun subscriptions(
        userId: String?,
        screenName: String?,
        count: Int,
        cursor: String
    ): Response<PagingUserList> = client.get(
        "$LISTS/subscriptions.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "count" to count,
        "cursor" to cursor
    )

    override suspend fun create(
        name: String,
        mode: UserList.Mode,
        description: String?
    ): Response<UserList> = client.post(
        "$LISTS/create.json",
        "name" to name,
        "mode" to mode.name.toLowerCase(),
        "description" to description
    )

    override suspend fun destroy(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?
    ): Response<UserList> = client.post(
        "$LISTS/destroy.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId
    )

    override suspend fun createMembers(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        userId: String?,
        screenName: String?
    ): Response<UserList> = client.post(
        "$LISTS/members/create.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "user_id" to userId,
        "screen_name" to screenName
    )

    override suspend fun createAllMembers(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        userIds: List<String>?,
        screenNames: List<String>?
    ): Response<UserList> = client.post(
        "$LISTS/members/create_all.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "user_id" to userIds?.joinToString(","),
        "screen_name" to screenNames?.joinToString(",")
    )

    override suspend fun destroyMembers(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        userId: String?,
        screenName: String?
    ): Response<UserList> = client.post(
        "$LISTS/members/destroy.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "user_id" to userId,
        "screen_name" to screenName
    )

    override suspend fun destroyAllMembers(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        userIds: List<String>?,
        screenNames: List<String>?
    ): Response<UserList> = client.post(
        "$LISTS/members/destroy_all.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "user_id" to userIds?.joinToString(","),
        "screen_name" to screenNames?.joinToString(",")
    )

    override suspend fun createSubscribers(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?
    ): Response<UserList> = client.post(
        "$LISTS/subscribers/create.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId
    )

    override suspend fun destroySubscribers(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?
    ): Response<UserList> = client.post(
        "$LISTS/subscribers/destroy.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId
    )

    override suspend fun update(
        listId: String?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        name: String?,
        mode: UserList.Mode?,
        description: String?
    ): Response<UserList> = client.post(
        "$LISTS/update.json",
        "list_id" to listId,
        "slug" to slug,
        "owner_screen_name" to ownerScreenName,
        "owner_id" to ownerId,
        "name" to name,
        "mode" to mode?.name?.toLowerCase(),
        "description" to description
    )
}
