/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.authentication

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuth2ApiTest : AbstractTest {

    @Test
    fun tokenTest() = runTest {
        val accessToken = TwitterAPI.oauth2Api.token().testResult()
        assertNotNull(accessToken, "token is null")

    }

    @Ignore
    @Test
    fun invalidateToken() = runTest {
        assertNotNull(TwitterAPI.oauth2Api.invalidateToken().testResult(), "invalidateToken is null")
    }
}
