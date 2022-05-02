package com.sorrowblue.twitlin.api.v2.tweets.counts

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.oauth2.ProjectConfig
import com.sorrowblue.twitlin.api.v2.test.shouldSuccess
import io.kotest.core.spec.style.FunSpec
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class TweetsCountsApiTest : FunSpec({

    val tweetsCountsApi = TwitlinApiV2.getApi<TweetsCountsApi>(ProjectConfig.oAuth2Client)

    @OptIn(ExperimentalTime::class)
    test("tweetsCountsApi.recent") {
        tweetsCountsApi.recent(
            "lakers",
            granularity = "day",
            endTime = Clock.System.now().minus(1.minutes).toLocalDateTime(TimeZone.UTC)
        ).shouldSuccess()
    }
})
