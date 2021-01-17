/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.users.MutesApi
import com.sorrowblue.twitlin.users.PagingUser

private const val MUTES = "${Urls.V1}/mutes/users/"

internal class MutesApiImpl(private val client: UserClient) : MutesApi {
    override suspend fun ids(cursor: String): Response<PagingIds> {
        return client.get(
            "$MUTES/ids.json",
            Response.serializer(PagingIds.serializer()),
            "cursor" to cursor
        )
    }

    override suspend fun list(
        cursor: String,
        includeEntities: Boolean?,
        skipStatus: Boolean?
    ): Response<PagingUser> {
        return client.get(
            "$MUTES/list.json",
            Response.serializer(PagingUser.serializer()),
            "cursor" to cursor,
            "include_entities" to includeEntities,
            "skip_status" to skipStatus
        )
    }

    override suspend fun create(screenName: String?, userId: String?): Response<User> {
        return client.post(
            "$MUTES/create.json",
            Response.serializer(User.serializer()),
            "screen_name" to screenName,
            "user_id" to userId,
        )
    }

    override suspend fun destroy(screenName: String?, userId: String?): Response<User> {
        return client.post(
            "$MUTES/destroy.json",
            Response.serializer(User.serializer()),
            "screen_name" to screenName,
            "user_id" to userId,
        )
    }
}
