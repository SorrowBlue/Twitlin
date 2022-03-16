package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.users.mutes.MutesApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class MutesApiTest : AbstractTest {

    private val mutesApi = TwitlinApi.getApi<MutesApi>(oauth1aClient)

    @Test
    fun createTest() = runTest { mutesApi.create("shinya_yuunari").resultLog().let { assertNotNull(it) } }
}
