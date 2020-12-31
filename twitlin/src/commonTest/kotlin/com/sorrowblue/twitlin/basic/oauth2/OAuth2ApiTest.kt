/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.basic.oauth2

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test

class OAuth2ApiTest : AbstractTest {

    @Test
    fun tokenTest() = runTest {
        TwitterAPI.oauth2.token().testResult()
    }

    @Test
    fun invalidateTokenTest() = runTest {
        val b = TwitterAPI.oauth2.token().getOrNull() ?: return@runTest
        Napier.d("bearerToken = $b")
        TwitterAPI.oauth2.invalidateToken().testResult()
    }
}
