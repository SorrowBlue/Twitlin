package com.sorrowblue.twitlin.api.v2.tweets.counts

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.testResult
import kotlin.test.Test
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import test.AbstractTest

@ExperimentalCoroutinesApi
class TweetsCountsApiTest : AbstractTest {

    private val tweetsCountsApi = TwitlinApiV2.getApi<TweetsCountsApi>(oauth1aClient)

    @OptIn(ExperimentalTime::class)
    @Test
    fun testRecent() = runTest {
        tweetsCountsApi.recent(
            "lakers",
            granularity = "day",
            endTime = Clock.System.now().minus(1.minutes).toLocalDateTime(TimeZone.UTC)
        ).testResult()
    }
}
