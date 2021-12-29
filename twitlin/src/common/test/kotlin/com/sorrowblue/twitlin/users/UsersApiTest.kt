package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class UsersApiTest : AbstractTest {

    private val usersApi = Twitlin.getApi<UsersApi>(oauth1aClient)

    @Test
    fun showTest() = runTest { usersApi.show(screenName = "Twitter").resultLog().let { assertNotNull(it) } }
}
