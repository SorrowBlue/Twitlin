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

class FriendsApiTest : AbstractTest {

    @Test
    fun listTest() = runTest {
        TwitterAPI.friends.list(screenName = "new_runnable", count = 50)
            .testResult().let { assertNotNull(it) }
    }
}
