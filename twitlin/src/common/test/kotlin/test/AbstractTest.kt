package test

import com.sorrowblue.twitlin.BuildKonfig
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.client.ConsumerKeys
import com.sorrowblue.twitlin.client.Oauth1aClient
import com.sorrowblue.twitlin.client.Oauth2Client
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.v2.oauth2.OAuth2Token
import kotlin.test.BeforeTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

interface AbstractTest {

    val token
        get() = OAuth2Token(
            tokenType = "bearer",
            expiresIn = 7200,
            accessToken = "UTRtSEpSZGxObmgxZHpUc1FONXNmRWEwNkE5NzRFOTVrMW1wM3FpRnRGVUJEOjE2MzEzODEzMDk3OTg6MToxOmF0OjE",
            scope = emptyList(),
            refreshToken = "OE91MkRRYnlnRXlTQUdGbkI3cGRVZDAxb3ZHMDZwNkNmd0kyS3FMTWNJRWxlOjE2MzEzODEzMDk3OTg6MTowOnJ0OjE"
        )

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
