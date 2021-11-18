package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.tweets.favorites.FavoritesApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import test.resultLog

class FavoritesApiTest : AbstractTest {
    private val favoritesApi = Twitlin.getApi<FavoritesApi>(oauth1aClient)

    @Test
    fun list() = runBlocking {
        @Suppress("DEPRECATION")
        assertNotNull(favoritesApi.list("STAR_ZERO", includeEntities = false).resultLog())
    }
}
