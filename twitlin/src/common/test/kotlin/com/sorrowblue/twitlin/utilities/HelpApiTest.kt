package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.utilities.help.HelpApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import test.resultLog

class HelpApiTest : AbstractTest {

    private val helpApi = Twitlin.getApi<HelpApi>(oauth2Client)

    @Test
    fun configuration() = runBlocking { assertNotNull(helpApi.configuration().resultLog()) }

    @Test
    fun languages() = runBlocking { assertNotNull(helpApi.languages().resultLog()) }

}
