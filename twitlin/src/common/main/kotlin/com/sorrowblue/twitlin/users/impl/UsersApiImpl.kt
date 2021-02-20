/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.users.ProfileBanner
import com.sorrowblue.twitlin.users.UsersApi
import kotlinx.serialization.builtins.ListSerializer

private const val USERS = "${Urls.V1}/users"

internal class UsersApiImpl(private val client: UserClient) : UsersApi {
    override suspend fun profileBanner(
        userId: String?,
        screenName: String?
    ): Response<ProfileBanner> {
        return client.get(
            "$USERS/profile_banner.json",
            Response.serializer(ProfileBanner.serializer()),
            "user_id" to userId,
            "screen_name" to screenName
        )
    }

    override suspend fun reportSpam(
        userId: String?,
        screenName: String?,
        performBlock: Boolean
    ): Response<User> {
        return client.get(
            "$USERS/report_spam.json",
            Response.serializer(User.serializer()),
            "user_id" to userId,
            "screen_name" to screenName,
            "perform_block" to performBlock
        )
    }

    override suspend fun lookup(
        screenName: List<String>?,
        userId: List<String>?,
        includeEntities: Boolean?,
        tweetMode: Boolean?
    ): Response<List<User>> {
        return client.get(
            "$USERS/lookup.json",
            Response.serializer(ListSerializer(User.serializer())),
            "screen_name" to screenName?.joinToString(","),
            "user_id" to userId?.joinToString(","),
            "include_entities" to includeEntities,
            "tweet_mode" to tweetMode
        )
    }

    override suspend fun search(
        q: String,
        page: Int?,
        count: Int?,
        includeEntities: Boolean?
    ): Response<List<User>> {
        return client.get(
            "$USERS/search.json",
            Response.serializer(ListSerializer(User.serializer())),
            "q" to q,
            "page" to count,
            "count" to includeEntities,
            "include_entities" to includeEntities
        )
    }

    override suspend fun show(
        userId: String?,
        screenName: String?,
        includeEntities: Boolean
    ): Response<User> {
        return client.get(
            "$USERS/show.json",
            Response.serializer(User.serializer()),
            "user_id" to userId,
            "screen_name" to screenName,
            "include_entities" to includeEntities
        )
    }
}
