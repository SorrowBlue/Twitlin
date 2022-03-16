package com.sorrowblue.twitlin.api.users.blocks.impl

import com.sorrowblue.twitlin.api.objects.PagingIds
import com.sorrowblue.twitlin.api.objects.User
import com.sorrowblue.twitlin.api.users.PagingUser
import com.sorrowblue.twitlin.api.users.blocks.BlocksApi
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.UserId
import com.sorrowblue.twitlin.core.util.API_BLOCKS
import io.ktor.client.request.parameter
import io.ktor.http.Parameters

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class BlocksApiImpl(private val client: TwitterClient) : BlocksApi {

    override suspend fun ids(cursor: String): Response<PagingIds<UserId>> {
        return client.get("$API_BLOCKS/ids.json", Response.serializer(PagingIds.serializer(UserId.serializer()))) {
            parameter("stringify_ids", true)
            parameter("cursor", cursor)
        }
    }

    override suspend fun list(cursor: String, includeEntities: Boolean, skipStatus: Boolean): Response<PagingUser> {
        return client.get("$API_BLOCKS/list.json", Response.serializer(PagingUser.serializer())) {
            parameter("cursor", cursor)
            parameter("include_entities", includeEntities)
            parameter("skip_status", skipStatus)
        }
    }

    override suspend fun create(userId: UserId, includeEntities: Boolean, skipStatus: Boolean): Response<User> =
        createImpl(userId, null, includeEntities, skipStatus)

    override suspend fun create(screenName: String, includeEntities: Boolean, skipStatus: Boolean): Response<User> =
        createImpl(null, screenName, includeEntities, skipStatus)

    private suspend fun createImpl(
        userId: UserId?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        return client.submitForm(
            "$API_BLOCKS/create.json",
            Response.serializer(User.serializer()),
            Parameters.build {
                userId?.let { append("user_id", it.id) }
                screenName?.let { append("screen_name", it) }
                append("include_entities", includeEntities.toString())
                append("skip_status", skipStatus.toString())
            }
        )
    }

    override suspend fun destroy(userId: UserId, includeEntities: Boolean, skipStatus: Boolean): Response<User> =
        destroyImpl(userId, null, includeEntities, skipStatus)

    override suspend fun destroy(screenName: String, includeEntities: Boolean, skipStatus: Boolean): Response<User> =
        destroyImpl(null, screenName, includeEntities, skipStatus)

    private suspend fun destroyImpl(
        userId: UserId?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        return client.submitForm(
            "$API_BLOCKS/destroy.json",
            Response.serializer(User.serializer()),
            Parameters.build {
                userId?.let { append("user_id", it.id) }
                screenName?.let { append("screen_name", it) }
                append("include_entities", includeEntities.toString())
                append("skip_status", skipStatus.toString())
            }
        )
    }
}
