package test

import com.sorrowblue.twitlin.api.BuildKonfig
import com.sorrowblue.twitlin.core.authentication.AccessToken
import com.sorrowblue.twitlin.core.authentication.BearerToken
import com.sorrowblue.twitlin.core.client.ConsumerKeys
import com.sorrowblue.twitlin.core.client.Oauth1aClient
import com.sorrowblue.twitlin.core.client.Oauth2Client
import com.sorrowblue.twitlin.core.objects.UserId
import kotlin.test.BeforeTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

interface AbstractTest {

    val consumerKeys get() = ConsumerKeys(BuildKonfig.TWITTER_API_KEY, BuildKonfig.TWITTER_API_SECRET)
    val accessToken
        get() = AccessToken(
            BuildKonfig.TWITTER_API_ACCESS_TOKEN,
            BuildKonfig.TWITTER_API_ACCESS_TOKEN_SECRET,
            UserId(""),
            ""
        )

    val oauth1aClient get() = Oauth1aClient(consumerKeys, accessToken)

    val oauth2Client get() = Oauth2Client(consumerKeys, BearerToken("bearer", BuildKonfig.TWITTER_API_BEARER_TOKEN))

    @BeforeTest
    fun initializeTwitlin() {
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Deprecated("a", ReplaceWith("runTest { block(this) }", "kotlinx.coroutines.test.runTest"))
    fun runBlocking(block: suspend CoroutineScope.() -> Unit) = runTest { block(this) }
}
