package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test
import kotlin.test.assertNotNull

class HelpApiTest : AbstractTest {

    @Test
    fun configuration() =
        runBlocking { assertNotNull(TwitterAPI.helpApi.configuration().resultLog()) }

    @Test
    fun languages() = runBlocking { assertNotNull(TwitterAPI.helpApi.languages().resultLog()) }

    @Test
    fun lateLimit() = runBlocking { TwitterAPI.applicationApi.rateLimitStatus().resultLog() }
}
