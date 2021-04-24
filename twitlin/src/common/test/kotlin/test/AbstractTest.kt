/*
 * (c) 2020-2021 SorrowBlue.
 */

package test

import com.sorrowblue.twitlin.BuildKonfig
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.logLevel
import kotlin.test.BeforeTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.datetime.TimeZone
import mu.KLogger
import mu.KotlinLogging

internal val logger: KLogger
    get() = logLevel(KotlinLogging.logger("TEST"))

interface AbstractTest {

    val logger: KLogger
        get() = logLevel(KotlinLogging.logger("TEST"))

    @BeforeTest
    fun initializeTwitlin() {
        Twitlin.initialize(BuildKonfig.QIITA_API_KEY, BuildKonfig.QIITA_API_SECRET) {
            accessToken =
                AccessToken(BuildKonfig.QIITA_API_ACCESS_TOKEN, BuildKonfig.QIITA_API_ACCESS_TOKEN_SECRET, "", "")
            bearerToken = BearerToken("Bearer", BuildKonfig.QIITA_API_BEARER_TOKEN)
            timeZone = TimeZone.UTC
        }
    }

    fun runBlocking(block: suspend CoroutineScope.() -> Unit) = TestUtils.runBlocking(block)
}
