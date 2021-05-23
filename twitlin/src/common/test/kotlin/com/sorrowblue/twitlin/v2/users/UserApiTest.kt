/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.testResult
import test.AbstractTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.resultLog

class UserApiTest : AbstractTest {

    @Test
    fun usersIdTest() = runBlocking {
        TwitterV2API.usersApi.users(
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

    @Test
    fun testBlocking() = runBlocking {
        val userId = TwitterV2API.usersApi.byUsername("sorrowblue_sb").dataOrNull()?.data?.id!!
        TwitterAPI.blocksApi.ids().resultLog()
        TwitterV2API.usersApi.blocking(userId, UsersApi.Expansion.all(), TweetField.all(), UserField.all())
            .testResult()
    }
}
