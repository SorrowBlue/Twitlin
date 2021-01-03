/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.TwitterUser
import com.sorrowblue.twitlin.users.BlocksApi
import com.sorrowblue.twitlin.users.PagingIds
import com.sorrowblue.twitlin.users.PagingUser

private const val BLOCKS = "${Urls.V1}/blocks"

internal class BlocksApiImpl(private val client: UserClient) : BlocksApi {

    override suspend fun ids(cursor: Long): Response<PagingIds> =
        client.get("$BLOCKS/ids.json", "stringify_ids" to true, "cursor" to cursor)

    override suspend fun list(
        cursor: String,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<PagingUser> = client.get(
        "$BLOCKS/list.json",
        "cursor" to cursor,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus
    )

    override suspend fun create(
        screenName: String?,
        userId: String?,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<TwitterUser> = client.post(
        "$BLOCKS/create.json",
        "screen_name" to screenName,
        "user_id" to userId,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus
    )

    override suspend fun destroy(
        screenName: String?,
        userId: String?,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<TwitterUser> = client.post(
        "$BLOCKS/destroy.json",
        "screen_name" to screenName,
        "user_id" to userId,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus
    )

}
