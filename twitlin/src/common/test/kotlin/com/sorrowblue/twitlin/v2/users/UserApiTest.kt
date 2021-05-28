/*
 * (c) 2020-2021 SorrowBlue.
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
            "938122027231150081",
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = Expansion.all()
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun usersIdsTest() = runBlocking {
        TwitterV2API.usersApi.users(
            listOf("2244994945", "6253282"),
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = Expansion.all()
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
            expansions = Expansion.all()
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
    fun testBlockingList() = runBlocking {
        TwitterV2API.usersApi.blocking("938122027231150081").testResult()
    }

    @Test
    fun testBlocking() = runBlocking {
        TwitterV2API.usersApi.blocking("938122027231150081", "783214").testResult()
    }

    @Test
    fun testUnBlocking() = runBlocking {
        TwitterV2API.usersApi.unBlocking("938122027231150081", "783214").testResult()
    }

    @Test
    fun testLikedTweets() = runBlocking {
        TwitterV2API.usersApi.likedTweets("938122027231150081").testResult()
    }

    @Test
    fun testLikes() = runBlocking {
        TwitterV2API.usersApi.likes("938122027231150081", "1394925800470814720").testResult()
    }

    @Test
    fun testUnLikes() = runBlocking {
        TwitterV2API.usersApi.unLikes("938122027231150081", "1394925800470814720").testResult()
    }

    @Test
    fun testFollowingList() = runBlocking {
        TwitterV2API.usersApi.following("938122027231150081").testResult()
    }

    @Test
    fun testFollowing() = runBlocking {
        TwitterV2API.usersApi.following("938122027231150081", "1613102274").testResult()
    }

    @Test
    fun testUnFollowing() = runBlocking {
        TwitterV2API.usersApi.unFollowing("938122027231150081", "1613102274").testResult()
    }

    @Test
    fun testFollowers() = runBlocking {
        TwitterV2API.usersApi.followers("938122027231150081").testResult()
    }
}
