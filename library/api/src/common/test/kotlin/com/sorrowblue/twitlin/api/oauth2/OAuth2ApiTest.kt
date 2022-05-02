package com.sorrowblue.twitlin.api.oauth2

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth2Client
import com.sorrowblue.twitlin.api.test.shouldSuccess
import io.kotest.core.spec.style.FunSpec
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class OAuth2ApiTest : FunSpec({

    val oauth2Api = TwitlinApi.getApi<OAuth2Api>(oauth2Client)

    test("oauth2Api.token").config(enabled = false) {
        oauth2Api.token()
            .shouldSuccess()
    }

    test("oauth2Api.invalidateToken").config(enabled = false) {
        oauth2Api.invalidateToken()
            .shouldSuccess()
    }
})
