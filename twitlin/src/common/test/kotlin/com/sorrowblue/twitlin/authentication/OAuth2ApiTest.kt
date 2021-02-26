/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuth2ApiTest : AbstractTest {

    @Test
    fun tokenTest() = runBlocking {
        val accessToken = TwitterAPI.oauth2Api.token().resultLog()
        assertNotNull(accessToken, "token is null")
    }

    @Ignore
    @Test
    fun invalidateToken() = runBlocking {
        assertNotNull(TwitterAPI.oauth2Api.invalidateToken().resultLog(), "invalidateToken is null")
    }
}
