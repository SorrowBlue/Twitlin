package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.users.friends.FriendsApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@OptIn(ExperimentalCoroutinesApi::class)
class FriendsApiTest : AbstractTest {

    private val friendsApi = TwitlinApi.getApi<FriendsApi>(oauth1aClient)

    @Test
    fun listTest() = runTest {
        @Suppress("DEPRECATION")
        friendsApi.list(screenName = "new_runnable", count = 50).resultLog().let { assertNotNull(it) }
    }
}
