/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.TwitterUser
import com.sorrowblue.twitlin.users.ProfileBanner
import com.sorrowblue.twitlin.users.UsersApi

private const val USERS = "${Urls.V1}/users"

internal class UsersApiImpl(private val client: UserClient) : UsersApi {

    override suspend fun profileBanner(
        userId: String?,
        screenName: String?
    ): Response<ProfileBanner> =
        client.get("$USERS/profile_banner.json", "user_id" to userId, "screen_name" to screenName)

    override suspend fun reportSpam(
        userId: String?,
        screenName: String?,
        performBlock: Boolean
    ): Response<TwitterUser> = client.get(
        "$USERS/report_spam.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "perform_block" to performBlock
    )

    override suspend fun lookup(
        screenName: List<String>?,
        userId: List<String>?,
        includeEntities: Boolean?,
        tweetMode: Boolean?
    ): Response<List<TwitterUser>> = client.get(
        "$USERS/lookup.json",
        "screen_name" to screenName?.joinToString(","),
        "user_id" to userId?.joinToString(","),
        "include_entities" to includeEntities,
        "tweet_mode" to tweetMode
    )

    override suspend fun search(
        q: String,
        page: Int?,
        count: Int?,
        includeEntities: Boolean?
    ): Response<List<TwitterUser>> = client.get(
        "$USERS/search.json",
        "q" to q,
        "page" to count,
        "count" to includeEntities,
        "include_entities" to includeEntities
    )

    override suspend fun show(
        userId: String?,
        screenName: String?,
        includeEntities: Boolean?
    ): Response<TwitterUser> = client.get(
        "$USERS/show.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "include_entities" to includeEntities
    )


}
