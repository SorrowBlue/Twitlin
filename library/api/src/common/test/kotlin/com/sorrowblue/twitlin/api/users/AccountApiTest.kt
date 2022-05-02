package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.api.users.account.AccountApi
import io.kotest.core.spec.style.FunSpec

class AccountApiTest : FunSpec({

    val accountApi = TwitlinApi.getApi<AccountApi>(oauth1aClient)

    test("accountApi.verifyCredentials") {
        accountApi.verifyCredentials(includeEntities = true, includeEmail = true, skipStatus = false)
            .shouldSuccess()
    }

    test("accountApi.settings") {
        accountApi.settings()
            .shouldSuccess()
    }

    test("accountApi.updateProfile") {
        accountApi.updateProfile(profileLinkColor = "FF0000")
            .shouldSuccess()
    }
})
