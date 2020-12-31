/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.accounts_users.users

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test

class UsersApiTest : AbstractTest {

    @Test
    fun lookupTest() = runTest {
        TwitterAPI.users.lookup(
            listOf("gigazine"),
            includeEntities = true,
            tweetMode = true
        ).testResult()
    }
}
