package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField
import kotlin.test.Test
import kotlin.test.assertNotNull

@TwitterAPIV2
class UserApiTest : AbstractTest {

    @Test
    fun usersIdTest() = runTest {
        TwitterAPI.V2.usersApi.users(
//			"2244994945",
            "938122027231150081",
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = UsersApi.Expansion.all()
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun usersIdsTest() = runTest {
        TwitterAPI.V2.usersApi.users(
            listOf("2244994945", "6253282"),
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = UsersApi.Expansion.all()
        ).testResult().also {
            assertNotNull(it, "")
        }
    }

    @Test
    fun byUsernameTest() = runTest {
        TwitterAPI.V2.usersApi.byUsername(
            "TwitterDev",
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = UsersApi.Expansion.all()
        ).testResult().also {
            assertNotNull(it, "")
        }
    }
}
