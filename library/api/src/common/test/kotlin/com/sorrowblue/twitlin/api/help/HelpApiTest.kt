package com.sorrowblue.twitlin.api.help

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import io.kotest.core.spec.style.FunSpec

class HelpApiTest : FunSpec({

    val helpApi = TwitlinApi.getApi<HelpApi>(oauth1aClient)

    test("helpApi.languages") {
        helpApi.languages()
            .shouldSuccess()
    }
})
