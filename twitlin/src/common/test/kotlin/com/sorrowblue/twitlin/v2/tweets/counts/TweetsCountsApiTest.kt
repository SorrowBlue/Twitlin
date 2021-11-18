package com.sorrowblue.twitlin.v2.tweets.counts

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.v2.testResult
import kotlin.test.Test
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import test.AbstractTest

class TweetsCountsApiTest : AbstractTest {

    private val tweetsCountsApi = Twitlin.getApi<TweetsCountsApi>(oauth1aClient)

    @OptIn(ExperimentalTime::class)
    @Test
    fun testRecent() = runBlocking {
        tweetsCountsApi.recent(
            "lakers",
            granularity = "day",
            endTime = Clock.System.now().minus(1.minutes).toLocalDateTime(TimeZone.UTC)
        ).testResult()
    }
}
