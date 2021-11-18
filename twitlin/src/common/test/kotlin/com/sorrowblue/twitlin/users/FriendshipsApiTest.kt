package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.users.friendships.FriendshipsApi
import kotlin.test.Test
import test.AbstractTest
import test.resultLog

class FriendshipsApiTest : AbstractTest {

    private val friendshipsApi = Twitlin.getApi<FriendshipsApi>(oauth1aClient)

    @Test
    fun showTest() = runBlocking {
        friendshipsApi.show(
            sourceScreenName = "sorrowblue_sb",
            targetScreenName = "twitter"
        ).resultLog()
    }

    @Test
    fun incomingTest() = runBlocking {
        friendshipsApi.incoming().resultLog()
    }

    @Test
    fun lookupUserIdsTest() = runBlocking {
        friendshipsApi.lookup(listOf(UserId("1528352858"), UserId("2905085521"))).resultLog()
    }

    @Test
    fun lookupScreenNamesTest() = runBlocking {
        friendshipsApi.lookupByScreenName(listOf("andypiper", "binary_aaron", "twitterdev")).resultLog()
    }

    @Test
    fun noRetweetsIdsTest() = runBlocking {
        friendshipsApi.noRetweetsIds().resultLog()
    }

    @Test
    fun outgoingStrTest() = runBlocking {
        friendshipsApi.outgoing("-1").resultLog()
    }
}
