package com.sorrowblue.twitlin.accounts_users.followers

import com.sorrowblue.twitlin.accounts_users.UserIds
import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import com.sorrowblue.twitlin.objects.PagingTwitterUser

const val FOLLOWERS = "${Urls._1_1}/followers"

internal class FollowersApiImp(private val client: Client) : FollowersApi {

    override suspend fun ids(
        userId: Long,
        cursor: Long?,
        stringifyIds: Boolean?,
        count: Int?
    ): Response<UserIds> = client.get(
        "$FOLLOWERS/ids.json",
        "user_id" to userId,
        "cursor" to cursor,
        "stringifyIds" to stringifyIds,
        "count" to count
    )

    override suspend fun ids(
        screenName: String,
        cursor: Long?,
        stringifyIds: Boolean?,
        count: Int?
    ): Response<UserIds> = client.get(
        "$FOLLOWERS/ids.json",
        "screen_name" to screenName,
        "cursor" to cursor,
        "stringifyIds" to stringifyIds,
        "count" to count
    )

    override suspend fun list(
        userId: Long,
        cursor: Long?,
        count: Int?,
        skipStatus: Boolean?,
        includeUserEntities: Boolean?
    ): Response<PagingTwitterUser> = client.get(
        "$FOLLOWERS/list.json",
        "user_id" to userId,
        "cursor" to cursor,
        "count" to count,
        "skip_status" to skipStatus,
        "include_user_entities" to includeUserEntities
    )

    override suspend fun list(
        screenName: String,
        cursor: Long?,
        count: Int?,
        skipStatus: Boolean?,
        includeUserEntities: Boolean?
    ): Response<PagingTwitterUser> = client.get(
        "$FOLLOWERS/list.json",
        "screen_name" to screenName,
        "cursor" to cursor,
        "count" to count,
        "skip_status" to skipStatus,
        "include_user_entities" to includeUserEntities
    )

}
