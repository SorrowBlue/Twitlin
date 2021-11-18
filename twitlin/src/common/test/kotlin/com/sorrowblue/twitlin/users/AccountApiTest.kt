package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.users.account.AccountApi
import kotlin.test.Test
import test.AbstractTest
import test.resultLog

class AccountApiTest : AbstractTest {

    private val accountApi = Twitlin.getApi<AccountApi>(oauth1aClient)

    @Test
    fun verifyCredentialsTest() = runBlocking {
        accountApi.verifyCredentials(
            includeEntities = true,
            includeEmail = true,
            skipStatus = false
        ).resultLog()
    }

    @Test
    fun settingTest() = runBlocking {
        accountApi.settings().resultLog()
    }

    @Test
    fun updateProfileLinkColorTest() = runBlocking {
        accountApi.updateProfile(
            profileLinkColor = "FF0000"
        ).resultLog()
    }
}
