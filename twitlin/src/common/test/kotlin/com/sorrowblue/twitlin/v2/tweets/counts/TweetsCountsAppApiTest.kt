/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.counts

import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.testResult
import kotlin.test.Test
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import test.AbstractTest

class TweetsCountsAppApiTest : AbstractTest {

    @OptIn(ExperimentalTime::class)
    @Test
    fun testRecent() = runBlocking {
        TwitterV2API.tweetsCountsApi.recent(
            "lakers",
            granularity = "day",
            endTime = Clock.System.now().minus(Duration.minutes(1)).toLocalDateTime(TimeZone.UTC)
        ).testResult()
    }
}
