/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuthApiTest : AbstractTest {

    //    @Ignore
    @Test
    fun accessTokenTest() = runBlocking {
        val accessToken = TwitterAPI.oauthApi.accessToken(
            "GoioYgAAAAABEkL_AAABdseGi2g",
            "h0CwkPakH9P0TQLBQnrZJkrLiQI72p0d"
        ).resultLog()
        assertNotNull(accessToken, "accessToken is null")
    }

    @Test
    fun authenticateTest() = runBlocking {
        val url =
            TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com").resultLog()?.let {
                TwitterAPI.oauthApi.authenticate(it.oauthToken)
            }
        Napier.i("authenticate url: $url")
        assertNotNull(url, "authenticate url is null")
    }

    @Test
    fun authorizeTest() = runBlocking {
        val url =
            TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com").resultLog()?.let {
                TwitterAPI.oauthApi.authorize(it.oauthToken)
            }
        Napier.i("authorize url: $url")
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
