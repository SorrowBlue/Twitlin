/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import com.sorrowblue.twitlin.BuildKonfig
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.datetime.TimeZone
import kotlin.test.BeforeTest

interface AbstractTest {

    @BeforeTest
    fun initializeTwitlin() {
        Twitlin.initialize(BuildKonfig.API_KEY, BuildKonfig.API_SECRET) {
            accessToken =
                AccessToken(BuildKonfig.ACCESS_TOKEN, BuildKonfig.ACCESS_TOKEN_SECRET, "", "")
            bearerToken = BearerToken("Bearer", BuildKonfig.BEARER_TOKEN)
            timeZone = TimeZone.UTC
            antilog = TestAntilog()
        }
    }

    fun runBlocking(block: suspend CoroutineScope.() -> Unit) = TestUtils.runBlocking(block)
}
