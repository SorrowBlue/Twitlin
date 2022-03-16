package com.sorrowblue.twitlin.api.utilities

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.utilities.help.HelpApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@OptIn(ExperimentalCoroutinesApi::class)
class HelpApiTest : AbstractTest {

    private val helpApi = TwitlinApi.getApi<HelpApi>(oauth1aClient)

    @Test
    fun languages() = runTest { assertNotNull(helpApi.languages().resultLog()) }
}
