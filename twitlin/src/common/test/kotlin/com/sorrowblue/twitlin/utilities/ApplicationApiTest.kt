package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.utilities.application.ApplicationApi
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class ApplicationApiTest : AbstractTest {

    private val applicationApi = Twitlin.getApi<ApplicationApi>(oauth2Client)

    @Test
    fun lateLimit() = runTest { applicationApi.rateLimitStatus().resultLog() }
}
