package com.sorrowblue.twitlin.api.users.followers.impl

import com.sorrowblue.twitlin.api.objects.PagingIds
import com.sorrowblue.twitlin.api.users.PagingUser
import com.sorrowblue.twitlin.api.users.followers.FollowersApi
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.UserId
import com.sorrowblue.twitlin.core.util.API_FOLLOWERS
import io.ktor.client.request.parameter

internal class FollowersApiImpl(private val client: TwitterClient) : FollowersApi {

    override suspend fun ids(userId: UserId, cursor: String, count: Int): Response<PagingIds<UserId>> =
        idsImpl(userId, null, cursor, count)

    override suspend fun ids(screenName: String, cursor: String, count: Int): Response<PagingIds<UserId>> =
        idsImpl(null, screenName, cursor, count)

    private suspend fun idsImpl(
        userId: UserId?,
        screenName: String?,
        cursor: String,
        count: Int
    ): Response<PagingIds<UserId>> {
        return client.get("$API_FOLLOWERS/ids.json", Response.serializer(PagingIds.serializer(UserId.serializer()))) {
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
            parameter("cursor", cursor)
            parameter("stringify_ids", true)
            parameter("count", count)
        }
    }

    override suspend fun list(
        userId: UserId,
        cursor: String,
        count: Int,
        skipStatus: Boolean,
        includeUserEntities: Boolean
    ): Response<PagingUser> = listImpl(userId, null, cursor, count, skipStatus, includeUserEntities)

    override suspend fun list(
        screenName: String,
        cursor: String,
        count: Int,
        skipStatus: Boolean,
        includeUserEntities: Boolean
    ): Response<PagingUser> = listImpl(null, screenName, cursor, count, skipStatus, includeUserEntities)

    private suspend fun listImpl(
        userId: UserId?,
        screenName: String?,
        cursor: String,
        count: Int,
        skipStatus: Boolean,
        includeUserEntities: Boolean
    ): Response<PagingUser> {
        return client.get("$API_FOLLOWERS/list.json", Response.serializer(PagingUser.serializer())) {
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
            parameter("cursor", cursor)
            parameter("count", count)
            parameter("skip_status", skipStatus)
            parameter("include_user_entities", includeUserEntities)
        }
    }
}
