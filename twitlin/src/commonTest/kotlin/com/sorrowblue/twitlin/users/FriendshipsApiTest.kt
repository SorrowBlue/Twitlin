/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test

class FriendshipsApiTest : AbstractTest {

    @Test
    fun showTest() = runTest {
        TwitterAPI.friendships.show(
            sourceScreenName = "sorrowblue_sb",
            targetScreenName = "twitter"
        )
            .testResult()
    }

    @Test
    fun incomingTest() = runTest {
        TwitterAPI.friendships.incoming().testResult()
    }

    @Test
    fun lookupUserIdsTest() = runTest {
        TwitterAPI.friendships.lookup(userId = listOf("1528352858", "2905085521"))
            .testResult()
    }

    @Test
    fun lookupScreenNamesTest() = runTest {
        TwitterAPI.friendships.lookup(
            screenName = listOf(
                "andypiper",
                "binary_aaron",
                "twitterdev"
            )
        ).testResult()
    }

    @Test
    fun noRetweetsIdsTest() = runTest {
        TwitterAPI.friendships.noRetweetsIds().testResult()
    }

    @Test
    fun outgoingStrTest() = runTest {
        TwitterAPI.friendships.outgoing("-1").testResult()
    }
}
