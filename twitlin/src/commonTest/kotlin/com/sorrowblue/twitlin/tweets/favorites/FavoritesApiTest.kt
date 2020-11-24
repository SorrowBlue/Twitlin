package com.sorrowblue.twitlin.tweets.favorites

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull

class FavoritesApiTest : AbstractTest {

    @Test
    fun list() = runTest {
        assertNotNull(TwitterAPI.favorites.list("STAR_ZERO", includeEntities = false).testResult())
    }
}
