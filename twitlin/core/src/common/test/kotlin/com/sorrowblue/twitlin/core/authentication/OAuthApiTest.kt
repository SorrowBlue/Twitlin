package com.sorrowblue.twitlin.core.authentication

import com.sorrowblue.twitlin.core.CoreApiGetter
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class OAuthApiTest : AbstractTest {

    private val oauthApi = CoreApiGetter.oAuthApi(oauth1aClient)

    @Test
    fun accessTokenTest() = runTest {
        val accessToken = oauthApi.accessToken(
            "t4O14gAAAAABEkL_AAABfHZeEBw",
            "6C6qSXhVaifi2VaHHUP2XG7N8azn4vAj"
        ).resultLog()
        assertNotNull(accessToken, "accessToken is null")
    }

    @Test
    fun authenticateTest() = runTest {
        val url =
            oauthApi.requestToken("https://maitter.sorrowblue.com").resultLog()?.let {
                oauthApi.authenticate(it.oauthToken, forceLogin = true)
            }?.let { println("authenticateUrl: $it") }
        assertNotNull(url, "authenticate url is null")
    }

    @Ignore
    @Test
    fun authorizeTest() = runTest {
        val url =
            oauthApi.requestToken("https://maitter.sorrowblue.com").resultLog()?.let {
                oauthApi.authorize(it.oauthToken)
            }
        assertNotNull(url, "authorize url is null")
    }

    @Ignore
    @Test
    fun testAuthorize_oob() = runTest {
        val url =
            oauthApi.requestToken("oob").resultLog()?.let {
                oauthApi.authorize(it.oauthToken)
            }
        assertNotNull(url, "authorize url is null")
    }

    @Test
    fun requestTokenTest() = runTest {
        val requestToken = oauthApi.requestToken("https://maitter.sorrowblue.com")
            .resultLog()
        assertNotNull(requestToken, "requestToken is null")
    }

    @Ignore
    @Test
    fun invalidateToken() = runTest {
        assertNotNull(oauthApi.invalidateToken().resultLog(), "invalidateToken is null")
    }
}
