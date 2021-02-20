/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test
import kotlin.test.assertNotNull

class FriendsApiTest : AbstractTest {

    @Test
    fun listTest() = runBlocking {
        TwitterAPI.friendsApi.list(screenName = "new_runnable", count = 50)
            .resultLog().let { assertNotNull(it) }
    }
}
