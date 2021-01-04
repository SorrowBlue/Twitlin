/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull

class CollectionsApiTest : AbstractTest {

    @Test
    fun entriesTest() = runTest {
        TwitterAPI.collectionsApi.entries("custom-539487832448843776")
            .testResult().let { assertNotNull(it) }
    }

    @Test
    fun listTest() = runTest {
        TwitterAPI.collectionsApi.list(screenName = "twittermusic")
            .testResult().let { assertNotNull(it) }
    }
}
