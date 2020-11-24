package com.sorrowblue.twitlin.tweets.favorites

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls._1_1
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.objects.TwitterTweet

private val FAVORITES = "$_1_1/favorites"

internal class FavoritesApiImp(private val client: Client) : FavoritesApi {

    override suspend fun list(
        userId: Long,
        count: Int,
        sinceId: Long?,
        maxId: Long?,
        includeEntities: Boolean
    ): Response<List<TwitterTweet>> = list(userId, null, count, sinceId, maxId, includeEntities)

    override suspend fun list(
        screenName: String,
        count: Int,
        sinceId: Long?,
        maxId: Long?,
        includeEntities: Boolean
    ): Response<List<TwitterTweet>> = list(null, screenName, count, sinceId, maxId, includeEntities)

    private suspend fun list(
        userId: Long?,
        screenName: String?,
        count: Int,
        sinceId: Long?,
        maxId: Long?,
        includeEntities: Boolean
    ): Response<List<TwitterTweet>> = client.get(
        "$FAVORITES/list.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "count" to count,
        "sinceId" to sinceId,
        "maxId" to maxId,
        "include_entities" to includeEntities
    )
}
