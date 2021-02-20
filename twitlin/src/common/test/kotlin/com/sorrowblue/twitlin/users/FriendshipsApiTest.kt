/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test

class FriendshipsApiTest : AbstractTest {

    @Test
    fun showTest() = runBlocking {
        TwitterAPI.friendshipsApi.show(
            sourceScreenName = "sorrowblue_sb",
            targetScreenName = "twitter"
        )
            .resultLog()
    }

    @Test
    fun incomingTest() = runBlocking {
        TwitterAPI.friendshipsApi.incoming().resultLog()
    }

    @Test
    fun lookupUserIdsTest() = runBlocking {
        TwitterAPI.friendshipsApi.lookup(userId = listOf("1528352858", "2905085521"))
            .resultLog()
    }

    @Test
    fun lookupScreenNamesTest() = runBlocking {
        TwitterAPI.friendshipsApi.lookup(
            screenName = listOf(
                "andypiper",
                "binary_aaron",
                "twitterdev"
            )
        ).resultLog()
    }

    @Test
    fun noRetweetsIdsTest() = runBlocking {
        TwitterAPI.friendshipsApi.noRetweetsIds().resultLog()
    }

    @Test
    fun outgoingStrTest() = runBlocking {
        TwitterAPI.friendshipsApi.outgoing("-1").resultLog()
    }
}
