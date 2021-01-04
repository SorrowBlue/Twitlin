/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.authentication

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuthApiTest : AbstractTest {

//    @Ignore
    @Test
    fun accessTokenTest() = runTest {
        val accessToken = TwitterAPI.oauthApi.accessToken(
            "GoioYgAAAAABEkL_AAABdseGi2g",
            "h0CwkPakH9P0TQLBQnrZJkrLiQI72p0d"
        ).testResult()
        assertNotNull(accessToken, "accessToken is null")

    }

    @Test
    fun authenticateTest() = runTest {
        val url =
            TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com").testResult()?.let {
                TwitterAPI.oauthApi.authenticate(it.oauthToken)
            }
        Napier.i("authenticate url: $url")
        assertNotNull(url, "authenticate url is null")
    }

    @Test
    fun authorizeTest() = runTest {
        val url =
            TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com").testResult()?.let {
                TwitterAPI.oauthApi.authorize(it.oauthToken)
            }
        Napier.i("authorize url: $url")
        assertNotNull(url, "authorize url is null")
    }

    @Test
    fun requestTokenTest() = runTest {
        val requestToken = TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com")
            .testResult()
        assertNotNull(requestToken, "requestToken is null")
    }

    @Ignore
    @Test
    fun invalidateToken() = runTest {
        assertNotNull(TwitterAPI.oauthApi.invalidateToken().testResult(), "invalidateToken is null")
    }
}
