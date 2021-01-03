/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.users.FollowersApi
import com.sorrowblue.twitlin.users.PagingIds
import com.sorrowblue.twitlin.users.PagingUser

private const val FOLLOWERS = "${Urls.V1}/followers"

internal class FollowersApiImpl(private val client: UserClient) : FollowersApi {

    override suspend fun ids(
        userId: String?,
        screenName: String?,
        cursor: String,
        count: Int?
    ): Response<PagingIds> = client.get(
        "$FOLLOWERS/ids.json",
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
        "$FOLLOWERS/list.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "cursor" to cursor,
        "count" to count,
        "skip_status" to skipStatus,
        "include_user_entities" to includeUserEntities
    )

}
