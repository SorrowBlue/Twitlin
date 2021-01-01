/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.accounts_users.account

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test

class AccountApiTest : AbstractTest {

    @Test
    fun verifyCredentialsTest() = runTest {
        TwitterAPI.account.verifyCredentials(
            includeEntities = true,
            includeEmail = true,
            skipStatus = false
        ).testResult()
    }

    @Test
    fun settingTest() = runTest {
        TwitterAPI.account.settings().testResult()
    }

    @Test
    fun updateProfileLinkColorTest() = runTest {
        TwitterAPI.account.updateProfile(
            profileLinkColor = "FF0000"
        ).testResult()
    }
}