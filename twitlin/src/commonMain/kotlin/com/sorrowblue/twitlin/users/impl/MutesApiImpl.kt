/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.TwitterUser
import com.sorrowblue.twitlin.users.MutesApi
import com.sorrowblue.twitlin.users.PagingIds
import com.sorrowblue.twitlin.users.PagingUser

private const val MUTES = "${Urls.V1}/mutes/users/"

internal class MutesApiImpl(private val client: UserClient) : MutesApi {

    override suspend fun ids(cursor: String): Response<PagingIds> =
        client.get("$MUTES/ids.json", "cursor" to cursor)

    override suspend fun list(
        cursor: String,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<PagingUser> = client.get(
        "$MUTES/list.json", "cursor" to cursor,
        "include_entities" to includeEntities,
        "skip_status" to skipStatus
    )

    override suspend fun create(screenName: String?, userId: String?): Response<TwitterUser> =
        client.post(
            "$MUTES/create.json",
            "screen_name" to screenName,
            "user_id" to userId,
        )

    override suspend fun destroy(screenName: String?, userId: String?): Response<TwitterUser> =
        client.post(
            "$MUTES/destroy.json",
            "screen_name" to screenName,
            "user_id" to userId,
        )
}
