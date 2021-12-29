package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.users.friendships.Friendships
import com.sorrowblue.twitlin.users.friendships.FriendshipsApi
import com.sorrowblue.twitlin.users.friendships.Relationship
import kotlin.test.Test
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

class FriendshipsApiTest : AbstractTest {

    private val friendshipsApi = Twitlin.getApi<FriendshipsApi>(oauth1aClient)

    @Test
    fun showTest() = runTest {
        friendshipsApi.show(
            sourceScreenName = "sorrowblue_sb",
            targetScreenName = "twitter"
        ).resultLog()
    }

    @Test
    fun incomingTest() = runTest { friendshipsApi.incoming().resultLog() }

    @Test
    fun lookupUserIdsTest() =
        runTest { friendshipsApi.lookup(listOf(UserId("1528352858"), UserId("2905085521"))).resultLog() }

    @Test
    fun lookupScreenNamesTest() =
        runTest { friendshipsApi.lookupByScreenName(listOf("andypiper", "binary_aaron", "twitterdev")).resultLog() }

    @Test
    fun noRetweetsIdsTest() = runTest { friendshipsApi.noRetweetsIds().resultLog() }

    @Test
    fun outgoingStrTest() = runTest { friendshipsApi.outgoing("-1").resultLog() }
}
