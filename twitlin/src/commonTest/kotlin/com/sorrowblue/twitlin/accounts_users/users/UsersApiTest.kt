/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.accounts_users.users

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test

class UsersApiTest : AbstractTest {

    @Test
    fun lookupTest() = runBlocking {
        TwitterAPI.usersApi.lookup(
            listOf("gigazine"),
            includeEntities = true,
            tweetMode = true
        ).resultLog()
    }
}
