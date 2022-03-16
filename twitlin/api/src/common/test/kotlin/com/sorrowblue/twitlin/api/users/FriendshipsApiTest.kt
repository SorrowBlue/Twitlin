package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.users.friendships.FriendshipsApi
import com.sorrowblue.twitlin.core.objects.UserId
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@OptIn(ExperimentalCoroutinesApi::class)
class FriendshipsApiTest : AbstractTest {

    private val friendshipsApi = TwitlinApi.getApi<FriendshipsApi>(oauth1aClient)

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
