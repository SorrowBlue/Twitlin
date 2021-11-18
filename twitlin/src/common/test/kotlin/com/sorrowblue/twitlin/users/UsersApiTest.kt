package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import test.resultLog

class UsersApiTest : AbstractTest {

    private val usersApi = Twitlin.getApi<UsersApi>(oauth1aClient)

    @Test
    fun showTest() = runBlocking {
        usersApi.show(screenName = "Twitter").resultLog().let { assertNotNull(it) }
    }
}
