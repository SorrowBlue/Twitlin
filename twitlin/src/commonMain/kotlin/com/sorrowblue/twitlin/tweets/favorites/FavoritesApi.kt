/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.favorites

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.Tweet

public interface FavoritesApi {

    public suspend fun list(
        userId: Long,
        count: Int = 20,
        sinceId: Long? = null,
        maxId: Long? = null,
        includeEntities: Boolean = true
    ): Response<List<Tweet>>

    public suspend fun list(
        screenName: String,
        count: Int = 20,
        sinceId: Long? = null,
        maxId: Long? = null,
        includeEntities: Boolean = true
    ): Response<List<Tweet>>
}
