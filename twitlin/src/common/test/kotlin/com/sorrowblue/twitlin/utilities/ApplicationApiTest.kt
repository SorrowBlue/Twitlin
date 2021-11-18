package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.utilities.application.ApplicationApi
import kotlin.test.Test
import test.AbstractTest
import test.resultLog

class ApplicationApiTest : AbstractTest {

    private val applicationApi = Twitlin.getApi<ApplicationApi>(oauth2Client)

    @Test
    fun lateLimit() = runBlocking { applicationApi.rateLimitStatus().resultLog() }
}
