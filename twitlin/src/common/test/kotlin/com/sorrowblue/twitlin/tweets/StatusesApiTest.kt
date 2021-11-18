package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.flow.collect
import test.AbstractTest
import test.resultLog

class StatusesApiTest : AbstractTest {

    private val statusesApi = Twitlin.getApi<StatusesApi>(oauth1aClient)

    @Test
    fun lookupTest() = runBlocking {
        assertNotNull(statusesApi.lookup(listOf(TweetId("1340446874583457792"))).resultLog())
    }

    @Test
    fun timelineTest() = runBlocking {
        assertNotNull(
            statusesApi.homeTimeline(10, trimUser = true, includeEntities = false).resultLog()?.map { it.id }
                .also { println("APPAPP: $it") }
        )
    }

    @Test
    fun updateTest() = runBlocking {
        statusesApi.update("Tweet test from Twitlin. code=[${Random.Default.nextDouble()}]").resultLog()
    }

    @Test
    fun filterTest() = runBlocking {
        statusesApi.filter(track = listOf("twitter")).collect {
            println(it)
        }
    }
}
