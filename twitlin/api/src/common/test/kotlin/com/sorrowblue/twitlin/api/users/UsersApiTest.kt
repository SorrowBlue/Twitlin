package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class UsersApiTest : AbstractTest {

    private val usersApi = TwitlinApi.getApi<UsersApi>(oauth1aClient)

    @Test
    fun showTest() = runTest { usersApi.show(screenName = "Twitter").resultLog().let { assertNotNull(it) } }
}
