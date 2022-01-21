package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.utilities.help.HelpApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@OptIn(ExperimentalCoroutinesApi::class)
class HelpApiTest : AbstractTest {

    private val helpApi = Twitlin.getApi<HelpApi>(oauth1aClient)

    @Test
    fun languages() = runTest { assertNotNull(helpApi.languages().resultLog()) }
}
