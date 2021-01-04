/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.favorites

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.Tweet

private val FAVORITES = "${Urls.V1}/favorites"

internal class FavoritesApiImp(private val client: UserClient) : FavoritesApi {

    override suspend fun list(
        userId: Long,
        count: Int,
        sinceId: Long?,
        maxId: Long?,
        includeEntities: Boolean
    ): Response<List<Tweet>> = list(userId, null, count, sinceId, maxId, includeEntities)

    override suspend fun list(
        screenName: String,
        count: Int,
        sinceId: Long?,
        maxId: Long?,
        includeEntities: Boolean
    ): Response<List<Tweet>> = list(null, screenName, count, sinceId, maxId, includeEntities)

    private suspend fun list(
        userId: Long?,
        screenName: String?,
        count: Int,
        sinceId: Long?,
        maxId: Long?,
        includeEntities: Boolean
    ): Response<List<Tweet>> = client.get(
        "$FAVORITES/list.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "count" to count,
        "sinceId" to sinceId,
        "maxId" to maxId,
        "include_entities" to includeEntities
    )
}
