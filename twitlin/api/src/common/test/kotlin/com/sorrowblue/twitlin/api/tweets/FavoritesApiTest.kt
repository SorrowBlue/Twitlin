package com.sorrowblue.twitlin.api.tweets

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.tweets.favorites.FavoritesApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class FavoritesApiTest : AbstractTest {
    private val favoritesApi = TwitlinApi.getApi<FavoritesApi>(oauth1aClient)

    @Test
    fun list() = runTest {
        @Suppress("DEPRECATION")
        assertNotNull(favoritesApi.list("STAR_ZERO", includeEntities = false).resultLog())
    }
}
