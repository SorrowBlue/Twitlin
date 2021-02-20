/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.users.BlocksApi
import com.sorrowblue.twitlin.users.PagingUser

private const val BLOCKS = "${Urls.V1}/blocks"

internal class BlocksApiImpl(private val client: UserClient) : BlocksApi {
    override suspend fun ids(cursor: Long): Response<PagingIds> {
        return client.get(
            "$BLOCKS/ids.json",
            Response.serializer(PagingIds.serializer()),
            "stringify_ids" to true,
            "cursor" to cursor
        )
    }

    override suspend fun list(
        cursor: String,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<PagingUser> {
        return client.get(
            "$BLOCKS/list.json",
            Response.serializer(PagingUser.serializer()),
            "cursor" to cursor,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus
        )
    }

    override suspend fun create(
        screenName: String?,
        userId: String?,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<User> {
        return client.post(
            "$BLOCKS/create.json",
            Response.serializer(User.serializer()),
            "screen_name" to screenName,
            "user_id" to userId,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus
        )
    }

    override suspend fun destroy(
        screenName: String?,
        userId: String?,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<User> {
        return client.post(
            "$BLOCKS/destroy.json",
            Response.serializer(User.serializer()),
            "screen_name" to screenName,
            "user_id" to userId,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus
        )
    }
}
