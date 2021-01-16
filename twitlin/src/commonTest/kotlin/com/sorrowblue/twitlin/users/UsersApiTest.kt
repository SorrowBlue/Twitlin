/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test
import kotlin.test.assertNotNull

class UsersApiTest : AbstractTest {

    @Test
    fun showTest() = runBlocking {
        TwitterAPI.usersApi.show(screenName = "Twitter").resultLog().let { assertNotNull(it) }
    }
}
