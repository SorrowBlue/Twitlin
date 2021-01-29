/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.tweets.FavoritesApi
import kotlinx.serialization.builtins.ListSerializer

private const val FAVORITES = "${Urls.V1}/favorites"

internal class FavoritesApiImp(private val client: UserClient) : FavoritesApi {
    override suspend fun create(id: String, includeEntities: Boolean): Response<Tweet> {
        return client.post(
            "$FAVORITES/create.json",
            Response.serializer(Tweet.serializer()),
            "id" to id,
            "include_entities" to includeEntities
        )
    }

    override suspend fun destroy(id: String, includeEntities: Boolean): Response<Tweet> {
        return client.post(
            "$FAVORITES/destroy.json",
            Response.serializer(Tweet.serializer()),
            "id" to id,
            "include_entities" to includeEntities
        )
    }

    override suspend fun list(
        userId: String?,
        screenName: String?,
        count: Int,
        sinceId: String?,
        maxId: String?,
        includeEntities: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$FAVORITES/list.json",
            Response.serializer(ListSerializer(Tweet.serializer())),
            "user_id" to userId,
            "screen_name" to screenName,
            "count" to count,
            "sinceId" to sinceId,
            "maxId" to maxId,
            "include_entities" to includeEntities
        )
    }
}
