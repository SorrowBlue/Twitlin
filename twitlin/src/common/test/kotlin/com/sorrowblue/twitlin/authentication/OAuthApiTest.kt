/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.TwitterAPI
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import test.resultLog

class OAuthApiTest : AbstractTest {

    @Ignore
    @Test
    fun accessTokenTest() = runBlocking {
        val accessToken = TwitterAPI.oauthApi.accessToken(
            "nGBqmgAAAAABEkL_AAABd-0vegc",
            "2052728"
        ).resultLog()
        assertNotNull(accessToken, "accessToken is null")
    }

    @Test
    fun authenticateTest() = runBlocking {
        val url =
            TwitterAPI.oauthApi.requestToken("https://maitter.sorrowblue.com").resultLog()?.let {
                TwitterAPI.oauthApi.authenticate(it.oauthToken)
            }
        assertNotNull(url, "authenticate url is null")
    }

    @Test
    fun authorizeTest() = runBlocking {
        val url =
            TwitterAPI.oauthApi.requestToken("https://maitter.sorrowblue.com").resultLog()?.let {
                TwitterAPI.oauthApi.authorize(it.oauthToken)
            }
        assertNotNull(url, "authorize url is null")
    }

    @Test
    fun testAuthorize_oob() = runBlocking {
        val url =
            TwitterAPI.oauthApi.requestToken("oob").resultLog()?.let {
                TwitterAPI.oauthApi.authorize(it.oauthToken)
            }
        assertNotNull(url, "authorize url is null")
    }

    @Test
    fun requestTokenTest() = runBlocking {
        val requestToken = TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com")
            .resultLog()
        assertNotNull(requestToken, "requestToken is null")
    }

    @Ignore
    @Test
    fun invalidateToken() = runBlocking {
        assertNotNull(TwitterAPI.oauthApi.invalidateToken().resultLog(), "invalidateToken is null")
    }
}
