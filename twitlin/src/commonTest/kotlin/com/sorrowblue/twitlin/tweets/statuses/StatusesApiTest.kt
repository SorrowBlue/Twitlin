/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.TwitterAPI
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.flow.collect
import test.AbstractTest
import test.resultLog

class StatusesApiTest : AbstractTest {

    @Test
    fun lookupTest() = runBlocking {
        assertNotNull(TwitterAPI.statuses.lookup(listOf("1340446874583457792")).resultLog())
    }

    @Test
    fun timelineTest() = runBlocking {
        assertNotNull(TwitterAPI.statuses.homeTimeline(count = 100).resultLog()?.map { it.idStr }.also {
            println("APPAPP: $it")
        })
    }

    @Test
    fun updateTest() = runBlocking {
        TwitterAPI.statuses.update("Tweet test from Twitlin.", "")
    }

    @Test
    fun filterTest() = runBlocking {
        var count = 0
        TwitterAPI.statuses.filter(track = listOf("#シンデレラHNYday1")).collect {
            println("${count++}")
        }
    }
}

