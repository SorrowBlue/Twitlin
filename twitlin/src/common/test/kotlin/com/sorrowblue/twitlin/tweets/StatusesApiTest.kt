package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertNotNull
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class StatusesApiTest : AbstractTest {

    private val statusesApi = Twitlin.getApi<StatusesApi>(oauth1aClient)

    @Test
    fun lookupTest() = runTest {
        assertNotNull(statusesApi.lookup(listOf(TweetId("1340446874583457792"))).resultLog())
    }

    @Test
    fun timelineTest() = runTest {
        assertNotNull(
            statusesApi.homeTimeline(10, trimUser = true, includeEntities = false).resultLog()?.map { it.id }
                .also { println("APPAPP: $it") }
        )
    }

    @Test
    fun updateTest() = runTest {
        statusesApi.update("Tweet test from Twitlin. code=[${Random.Default.nextDouble()}]").resultLog()
    }

    @Test
    fun filterTest() {
        kotlin.runCatching {
            runTest(dispatchTimeoutMs = 5000) {
                var count = 0
                statusesApi.filter(track = listOf("twitter")).collect {
                    println(it)
                    if (count++ > 20) {
                        cancel(null)
                    }
                }
            }
        }.onSuccess {
            println("success")
        }.onFailure {
            if (it is CancellationException) println("success")
            else {
                assertFails(it.message) {
                    it.printStackTrace()
                }
            }
        }
    }
}
