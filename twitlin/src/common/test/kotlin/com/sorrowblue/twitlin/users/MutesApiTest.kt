package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.users.mutes.MutesApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import test.resultLog

class MutesApiTest : AbstractTest {

    private val mutesApi = Twitlin.getApi<MutesApi>(oauth1aClient)

    @Test
    fun createTest() = runBlocking {
        mutesApi.create("shinya_yuunari").resultLog().let { assertNotNull(it) }
    }
}
