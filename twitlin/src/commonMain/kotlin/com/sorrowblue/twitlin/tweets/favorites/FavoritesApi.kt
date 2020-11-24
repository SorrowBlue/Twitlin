package com.sorrowblue.twitlin.tweets.favorites

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.TwitterTweet

interface FavoritesApi {
    suspend fun list(
        userId: Long,
        count: Int = 20,
        sinceId: Long? = null,
        maxId: Long? = null,
        includeEntities: Boolean = true
    ): Response<List<TwitterTweet>>

    suspend fun list(
        screenName: String,
        count: Int = 20,
        sinceId: Long? = null,
        maxId: Long? = null,
        includeEntities: Boolean = true
    ): Response<List<TwitterTweet>>
}
