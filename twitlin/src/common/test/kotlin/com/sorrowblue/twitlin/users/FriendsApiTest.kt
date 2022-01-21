package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.users.friends.FriendsApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@OptIn(ExperimentalCoroutinesApi::class)
class FriendsApiTest : AbstractTest {

    private val friendsApi = Twitlin.getApi<FriendsApi>(oauth1aClient)

    @Test
    fun listTest() = runTest {
        (@Suppress("DEPRECATION")
        friendsApi.list(screenName = "new_runnable", count = 50)
            .resultLog().let { assertNotNull(it) })
    }
}
