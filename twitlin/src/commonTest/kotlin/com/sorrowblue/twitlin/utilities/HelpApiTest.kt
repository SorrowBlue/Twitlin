package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull

class HelpApiTest : AbstractTest {

    @Test
    fun configuration() = runTest { assertNotNull(TwitterAPI.help.configuration().testResult()) }

    @Test
    fun languages() = runTest { assertNotNull(TwitterAPI.help.languages().testResult()) }

    @Test
    fun lateLimit() = runTest { TwitterAPI.application.rateLimitStatus().testResult() }
}
