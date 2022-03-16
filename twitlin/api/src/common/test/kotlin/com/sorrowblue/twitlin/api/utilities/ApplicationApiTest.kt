package com.sorrowblue.twitlin.api.utilities

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.utilities.application.ApplicationApi
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class ApplicationApiTest : AbstractTest {

    private val applicationApi = TwitlinApi.getApi<ApplicationApi>(oauth2Client)

    @Test
    fun lateLimit() = runTest { applicationApi.rateLimitStatus().resultLog() }
}
