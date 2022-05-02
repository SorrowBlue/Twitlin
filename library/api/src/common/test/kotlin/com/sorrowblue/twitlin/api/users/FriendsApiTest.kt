package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.api.users.friends.FriendsApi
import io.kotest.core.spec.style.FunSpec

class FriendsApiTest : FunSpec({

    val friendsApi = TwitlinApi.getApi<FriendsApi>(oauth1aClient)

    @Suppress("DEPRECATION")
    test("friendsApi.list") {
        friendsApi.list(screenName = "new_runnable", count = 50)
            .shouldSuccess()
    }
})
