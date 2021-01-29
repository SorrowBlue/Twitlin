/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.users.FriendsApi
import com.sorrowblue.twitlin.users.PagingUser

private const val FRIENDS = "${Urls.V1}/friends"

internal class FriendsApiImpl(private val client: UserClient) : FriendsApi {
    override suspend fun ids(
        userId: String?,
        screenName: String?,
        cursor: String,
        count: Int?
    ): Response<PagingIds> = client.get(
        "$FRIENDS/ids.json",
        Response.serializer(PagingIds.serializer()),
        "user_id" to userId,
        "screen_name" to screenName,
        "cursor" to cursor,
        "stringify_ids" to true,
        "count" to count
    )

    override suspend fun list(
        userId: String?,
        screenName: String?,
        cursor: String,
        count: Int,
        skipStatus: Boolean,
        includeUserEntities: Boolean
    ): Response<PagingUser> = client.get(
        "$FRIENDS/list.json",
        Response.serializer(PagingUser.serializer()),
        "user_id" to userId,
        "screen_name" to screenName,
        "cursor" to cursor,
        "count" to count,
        "skip_status" to skipStatus,
        "include_user_entities" to includeUserEntities,
    )
}
