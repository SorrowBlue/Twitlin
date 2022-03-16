package com.sorrowblue.twitlin.api.tweets.favorites.impl

import com.sorrowblue.twitlin.api.objects.Tweet
import com.sorrowblue.twitlin.api.tweets.favorites.FavoritesApi
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.TweetId
import com.sorrowblue.twitlin.core.objects.UserId
import com.sorrowblue.twitlin.core.util.API_FAVORITES
import io.ktor.client.request.parameter
import kotlinx.serialization.builtins.ListSerializer

internal class FavoritesApiImpl(private val client: TwitterClient) : FavoritesApi {

    override suspend fun list(
        userId: UserId,
        count: Int,
        includeEntities: Boolean,
        sinceId: TweetId?,
        maxId: TweetId?
    ): Response<List<Tweet>> = listImpl(userId, null, count, includeEntities, sinceId, maxId)

    override suspend fun list(
        screenName: String,
        count: Int,
        includeEntities: Boolean,
        sinceId: TweetId?,
        maxId: TweetId?
    ): Response<List<Tweet>> = listImpl(null, screenName, count, includeEntities, sinceId, maxId)

    private suspend fun listImpl(
        userId: UserId?,
        screenName: String?,
        count: Int,
        includeEntities: Boolean,
        sinceId: TweetId?,
        maxId: TweetId?
    ): Response<List<Tweet>> {
        return client.get("$API_FAVORITES/list.json", Response.serializer(ListSerializer(Tweet.serializer()))) {
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
            parameter("count", count)
            parameter("sinceId", sinceId?.id)
            parameter("maxId", maxId?.id)
            parameter("include_entities", includeEntities)
        }
    }

    override suspend fun create(id: TweetId, includeEntities: Boolean): Response<Tweet> {
        return client.post("$API_FAVORITES/create.json", Response.serializer(Tweet.serializer())) {
            parameter("id", id.id)
            parameter("include_entities", includeEntities)
        }
    }

    override suspend fun destroy(id: TweetId, includeEntities: Boolean): Response<Tweet> {
        return client.post("$API_FAVORITES/destroy.json", Response.serializer(Tweet.serializer())) {
            parameter("id", id.id)
            parameter("include_entities", includeEntities)
        }
    }
}
