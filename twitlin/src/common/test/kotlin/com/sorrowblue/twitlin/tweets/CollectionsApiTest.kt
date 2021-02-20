/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test
import kotlin.test.assertNotNull

class CollectionsApiTest : AbstractTest {

    @Test
    fun entriesTest() = runBlocking {
        TwitterAPI.collectionsApi.entries("custom-539487832448843776")
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun listTest() = runBlocking {
        TwitterAPI.collectionsApi.list(screenName = "twittermusic")
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun createTest() = runBlocking {
        TwitterAPI.collectionsApi.create("twitlintest", "twitlintest description")
            .resultLog().let { assertNotNull(it) }
    }
}
