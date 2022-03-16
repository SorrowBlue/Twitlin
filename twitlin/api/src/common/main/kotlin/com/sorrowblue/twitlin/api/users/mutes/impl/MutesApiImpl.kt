package com.sorrowblue.twitlin.api.users.mutes.impl

import com.sorrowblue.twitlin.api.objects.PagingIds
import com.sorrowblue.twitlin.api.objects.User
import com.sorrowblue.twitlin.api.users.PagingUser
import com.sorrowblue.twitlin.api.users.mutes.MutesApi
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.UserId
import com.sorrowblue.twitlin.core.util.API_MUTES
import io.ktor.client.request.parameter
import io.ktor.http.Parameters

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class MutesApiImpl(private val client: TwitterClient) : MutesApi {

    override suspend fun ids(cursor: String): Response<PagingIds<UserId>> {
        return client.get("$API_MUTES/users/ids.json", Response.serializer(PagingIds.serializer(UserId.serializer()))) {
            parameter("cursor", cursor)
        }
    }

    override suspend fun list(cursor: String, includeEntities: Boolean, skipStatus: Boolean): Response<PagingUser> {
        return client.get("$API_MUTES/users/list.json", Response.serializer(PagingUser.serializer())) {
            parameter("cursor", cursor)
            parameter("include_entities", includeEntities)
            parameter("skip_status", skipStatus)
        }
    }

    override suspend fun create(userId: UserId): Response<User> = createImpl(userId, null)

    override suspend fun create(screenName: String): Response<User> = createImpl(null, screenName)

    private suspend fun createImpl(userId: UserId?, screenName: String?): Response<User> {
        return client.submitForm(
            "$API_MUTES/users/create.json",
            Response.serializer(User.serializer()),
            Parameters.build {
                userId?.let { append("user_id", it.id) }
                screenName?.let { append("screen_name", it) }
            }
        )
    }

    override suspend fun destroy(userId: UserId): Response<User> = destroyImpl(userId, null)

    override suspend fun destroy(screenName: String): Response<User> = destroyImpl(null, screenName)

    private suspend fun destroyImpl(userId: UserId?, screenName: String?): Response<User> {
        return client.submitForm(
            "$API_MUTES/users/destroy.json",
            Response.serializer(User.serializer()),
            Parameters.build {
                userId?.let { append("user_id", it.id) }
                screenName?.let { append("screen_name", it) }
            }
        )
    }
}
