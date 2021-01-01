/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.basic.oauth

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuthApiTest : AbstractTest {

    @Test
    fun accessTokenTest() = runTest {
        TwitterAPI.oauthApi.accessToken(
            "etQh_QAAAAABEkL_AAABdFc_n68",
            "yyDhGg66AwElV1qUNodd4EaU7LgJ54KK"
        ).testResult()
    }

    @Test
    fun authenticateTest() = runTest {
        val url =
            TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com").getOrNull()?.let {
                TwitterAPI.oauthApi.authenticate(it.oauthToken)
            }
        assertNotNull(url, "authenticate url is null")
    }

    @Test
    fun authorize() = runTest {
        val url =
            TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com").getOrNull()?.let {
                TwitterAPI.oauthApi.authorize(it.oauthToken)
            }
        Napier.d("authorize = $url")
        assertNotNull(url, "authorize url is null")
    }


    @Test
    fun requestToken() = runTest {
        val token = TwitterAPI.oauthApi.requestToken("https://snsmate.sorrowblue.com")
            .testResult()
        assertNotNull(token, "oauthToken is null")
    }

    @Ignore
    @Test
    fun invalidateToken() {
        TODO()
    }
}
