/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.favorites

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test
import kotlin.test.assertNotNull

class FavoritesApiTest : AbstractTest {

    @Test
    fun list() = runBlocking {
        assertNotNull(TwitterAPI.favorites.list("STAR_ZERO", includeEntities = false).resultLog())
    }
}
