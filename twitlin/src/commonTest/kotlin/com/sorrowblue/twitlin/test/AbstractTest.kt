/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.test

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import kotlin.test.BeforeTest

interface AbstractTest {
    @BeforeTest
    fun initializeTwitlin() {
        Napier.base(TestAntilog())
        Twitlin.initialize(
            TestKey.API_KEY,
            TestKey.API_SECRET,
            AccessToken(TestKey.ACCESS_TOKEN, TestKey.ACCESS_TOKEN_SECRET, "", "")
        )
        Twitlin.client.bearerToken = BearerToken(
            "Bearer",
            "AAAAAAAAAAAAAAAAAAAAAP9CEgEAAAAAzu06w6WP7gs926FFpIpdvo92MbI%3DcYXjeFyh2YELwQm9iqN0Fy6ojxDG65v8ztOCgTLAmO6QcI16WJ"
        )
        Twitlin.v2Client.bearerToken = BearerToken(
            "Bearer",
            "AAAAAAAAAAAAAAAAAAAAAP9CEgEAAAAAzu06w6WP7gs926FFpIpdvo92MbI%3DcYXjeFyh2YELwQm9iqN0Fy6ojxDG65v8ztOCgTLAmO6QcI16WJ"
        )
    }
}
