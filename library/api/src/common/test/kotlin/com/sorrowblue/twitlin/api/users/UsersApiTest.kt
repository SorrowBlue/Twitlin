package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import io.kotest.core.spec.style.FunSpec

class UsersApiTest : FunSpec({

    val usersApi = TwitlinApi.getApi<UsersApi>(oauth1aClient)

    test("") {
        usersApi.show(screenName = "Twitter")
            .shouldSuccess()
    }
})
