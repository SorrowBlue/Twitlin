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
class OAuth2ApiTest : AbstractTest {

    private val oauth2Api = CoreApiGetter.oAuth2Api(oauth2Client)

    @Ignore
    @Test
    fun tokenTest() = runTest {
        val accessToken = oauth2Api.token().resultLog()
        assertNotNull(accessToken, "token is null")
    }

    @Ignore
    @Test
    fun invalidateToken() = runTest {
        assertNotNull(oauth2Api.invalidateToken().resultLog(), "invalidateToken is null")
    }
}
