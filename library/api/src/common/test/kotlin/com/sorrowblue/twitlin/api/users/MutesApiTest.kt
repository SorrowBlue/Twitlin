package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.api.users.mutes.MutesApi
import io.kotest.core.spec.style.FunSpec

class MutesApiTest : FunSpec({

    val mutesApi = TwitlinApi.getApi<MutesApi>(oauth1aClient)

    test("mutesApi.create") {
        mutesApi.create("shinya_yuunari")
            .shouldSuccess()
    }
})
