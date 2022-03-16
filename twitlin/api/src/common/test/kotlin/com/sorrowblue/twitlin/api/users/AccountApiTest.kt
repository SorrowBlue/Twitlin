package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.users.account.AccountApi
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class AccountApiTest : AbstractTest {

    private val accountApi = TwitlinApi.getApi<AccountApi>(oauth1aClient)

    @Test
    fun verifyCredentialsTest() = runTest {
        accountApi.verifyCredentials(
            includeEntities = true,
            includeEmail = true,
            skipStatus = false
        ).resultLog()
    }

    @Test
    fun settingTest() = runTest { accountApi.settings().resultLog() }

    @Test
    fun updateProfileLinkColorTest() = runTest {
        accountApi.updateProfile(
            profileLinkColor = "FF0000"
        ).resultLog()
    }
}
