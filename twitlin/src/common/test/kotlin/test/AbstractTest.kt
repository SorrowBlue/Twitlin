/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import kotlinx.datetime.TimeZone
import kotlin.test.BeforeTest

interface AbstractTest {

    @BeforeTest
    fun initializeTwitlin() {
        val prop = Properties("local.properties")
        Twitlin.initialize(prop.getProperty("API_KEY", ""), prop.getProperty("API_SECRET", "")) {
            accessToken = AccessToken(
                prop.getProperty("ACCESS_TOKEN", ""),
                prop.getProperty("ACCESS_TOKEN_SECRET", ""),
                "",
                ""
            )
            bearerToken = BearerToken("Bearer", prop.getProperty("BEARER_TOKEN", ""))
            timeZone = TimeZone.UTC
            antilog = TestAntilog()
        }
    }

    fun runBlocking(block: suspend () -> Unit) = TestUtils.runBlocking(block)
}
