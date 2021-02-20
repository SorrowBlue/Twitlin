/*
 * (c) 2020-2021 SorrowBlue.
 */



package com.sorrowblue.twitlin.accounts_users.account

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test

class AccountApiTest : AbstractTest {

    @Test
    fun verifyCredentialsTest() = runBlocking {
        TwitterAPI.accountApi.verifyCredentials(
            includeEntities = true,
            includeEmail = true,
            skipStatus = false
        ).resultLog()
    }

    @Test
    fun settingTest() = runBlocking {
        TwitterAPI.accountApi.settings().resultLog()
    }

    @Test
    fun updateProfileLinkColorTest() = runBlocking {
        TwitterAPI.accountApi.updateProfile(
            profileLinkColor = "FF0000"
        ).resultLog()
    }
}
