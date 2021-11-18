package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.Twitlin
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import test.resultLog

class OAuthApiTest : AbstractTest {

    private val oauthApi = Twitlin.getApi<OAuthApi>(oauth1aClient)

    @Test
    fun accessTokenTest() = runBlocking {
        val accessToken = oauthApi.accessToken(
            "t4O14gAAAAABEkL_AAABfHZeEBw",
            "6C6qSXhVaifi2VaHHUP2XG7N8azn4vAj"
        ).resultLog()
        assertNotNull(accessToken, "accessToken is null")
    }

    @Test
    fun authenticateTest() = runBlocking {
        val url =
            oauthApi.requestToken("https://maitter.sorrowblue.com").resultLog()?.let {
                oauthApi.authenticate(it.oauthToken, forceLogin = true)
            }?.let { println("authenticateUrl: $it") }
        assertNotNull(url, "authenticate url is null")
    }

    @Ignore
    @Test
    fun authorizeTest() = runBlocking {
        val url =
            oauthApi.requestToken("https://maitter.sorrowblue.com").resultLog()?.let {
                oauthApi.authorize(it.oauthToken)
            }
        assertNotNull(url, "authorize url is null")
    }

    @Ignore
    @Test
    fun testAuthorize_oob() = runBlocking {
        val url =
            oauthApi.requestToken("oob").resultLog()?.let {
                oauthApi.authorize(it.oauthToken)
            }
        assertNotNull(url, "authorize url is null")
    }

    @Test
    fun requestTokenTest() = runBlocking {
        val requestToken = oauthApi.requestToken("https://maitter.sorrowblue.com")
            .resultLog()
        assertNotNull(requestToken, "requestToken is null")
    }

    @Ignore
    @Test
    fun invalidateToken() = runBlocking {
        assertNotNull(oauthApi.invalidateToken().resultLog(), "invalidateToken is null")
    }
}
