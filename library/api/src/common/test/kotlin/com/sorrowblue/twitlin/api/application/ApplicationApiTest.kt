package com.sorrowblue.twitlin.api.application

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import io.kotest.core.spec.style.FunSpec

class ApplicationApiTest : FunSpec({

    val applicationApi = TwitlinApi.getApi<ApplicationApi>(oauth1aClient)

    test("applicationApi.rateLimitStatus") {
        applicationApi.rateLimitStatus()
            .shouldSuccess()
    }
})
