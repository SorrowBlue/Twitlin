/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull

class UsersApiTest : AbstractTest {

    @Test
    fun showTest() = runTest {
        TwitterAPI.usersApi.show(screenName = "Twitter").testResult().let { assertNotNull(it) }
    }
}
