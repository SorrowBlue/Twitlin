/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.users.FollowersApi
import com.sorrowblue.twitlin.users.PagingUser

private const val FOLLOWERS = "${Urls.V1}/followers"

internal class FollowersApiImpl(private val client: UserClient) : FollowersApi {
    override suspend fun ids(
        userId: String?,
        screenName: String?,
        cursor: String,
        count: Int?
    ): Response<PagingIds> {
        return client.get(
            "$FOLLOWERS/ids.json",
            Response.serializer(PagingIds.serializer()),
            "user_id" to userId,
            "screen_name" to screenName,
            "cursor" to cursor,
            "stringify_ids" to true,
            "count" to count
        )
    }

    override suspend fun list(
        userId: String?,
        screenName: String?,
        cursor: String,
        count: Int,
        skipStatus: Boolean,
        includeUserEntities: Boolean
    ): Response<PagingUser> {
        return client.get(
            "$FOLLOWERS/list.json",
            Response.serializer(PagingUser.serializer()),
            "user_id" to userId,
            "screen_name" to screenName,
            "cursor" to cursor,
            "count" to count,
            "skip_status" to skipStatus,
            "include_user_entities" to includeUserEntities
        )
    }
}
