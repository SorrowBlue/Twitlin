/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.TwitterAPI
import kotlinx.coroutines.flow.collect
import test.AbstractTest
import test.resultLog
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull

class StatusesApiTest : AbstractTest {

    @Test
    fun lookupTest() = runBlocking {
        assertNotNull(TwitterAPI.statuses.lookup(listOf("1340446874583457792")).resultLog())
    }

    @Test
    fun timelineTest() = runBlocking {
        assertNotNull(
            TwitterAPI.statuses.homeTimeline().resultLog()?.map { it.idStr }
                .also { println("APPAPP: $it") }
        )
    }

    @Test
    fun updateTest() = runBlocking {
        TwitterAPI.statuses.update("Tweet test from Twitlin.", "")
    }

    @Ignore
    @Test
    fun filterTest() = runBlocking {
        TwitterAPI.statuses.filter(track = listOf("#シンデレラHNYday1")).collect {
            return@collect
        }
    }
}
