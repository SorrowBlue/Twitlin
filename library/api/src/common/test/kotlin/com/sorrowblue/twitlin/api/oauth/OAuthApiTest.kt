package com.sorrowblue.twitlin.api.oauth

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import io.kotest.core.spec.style.FunSpec
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class OAuthApiTest : FunSpec({

    val oauthApi = TwitlinApi.getApi<OAuthApi>(oauth1aClient)

    test("oauthApi.accessToken").config(enabled = true) {
        oauthApi.accessToken("8evbTQAAAAABEkL_AAABgBR3Xm4", "yHqjMADQnDvYPsBPZYrHtNVQD3kMbkEL")
            .shouldSuccess()
    }

    test("oauthApi.requestToken").config(enabled = false) {
        oauthApi.requestToken("https://maitter.sorrowblue.com")
            .shouldSuccess()
    }

    test("oauthApi.authenticate").config(enabled = false) {
        oauthApi.requestToken("https://maitter.sorrowblue.com")
            .onSuccess { println(oauthApi.authenticate(it.oauthToken, forceLogin = true, "twitlin_kmp")) }
            .shouldSuccess()
    }

    test("oauthApi.authorize").config(enabled = false) {
        oauthApi.requestToken("https://maitter.sorrowblue.com")
            .onSuccess { println(oauthApi.authorize(it.oauthToken)) }
            .shouldSuccess()
    }

    test("oauthApi.requestToken oob").config(enabled = false) {
        oauthApi.requestToken("oob")
            .onSuccess { oauthApi.authorize(it.oauthToken) }
            .shouldSuccess()
    }

    test("oauthApi.invalidateToken").config(enabled = false) {
        oauthApi.invalidateToken()
            .shouldSuccess()
    }
})
