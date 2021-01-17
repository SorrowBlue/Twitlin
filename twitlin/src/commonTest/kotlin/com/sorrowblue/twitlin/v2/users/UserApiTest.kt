/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.testResult
import test.AbstractTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class UserApiTest : AbstractTest {

    @Test
    fun usersIdTest() = runBlocking {
        TwitterV2API.usersApi.users(
//			"2244994945",
            "938122027231150081",
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = UsersApi.Expansion.all()
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun usersIdsTest() = runBlocking {
        TwitterV2API.usersApi.users(
            listOf("2244994945", "6253282"),
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = UsersApi.Expansion.all()
        ).testResult().also {
            assertNotNull(it, "")
        }
    }

    @Test
    fun byUsernameTest() = runBlocking {
        TwitterV2API.usersApi.byUsername(
            "TwitterDev",
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = UsersApi.Expansion.all()
        ).testResult().also {
            assertNotNull(it, "")
        }
    }

    @Test
    fun tweetsTest() = runBlocking {
        TwitterV2API.usersApi.tweets("2244994945", tweetFields = listOf(TweetField.TEXT))
            .testResult()
    }
}
