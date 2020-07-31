package com.sorrowblue.twitlin

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.context.startKoin


private const val ACCESS_TOKEN = "938122027231150081-nWR2QO576Hr6y1lkzgzfvzR0MymQfcw"
private const val ACCESS_TOKEN_SECRET = "938122027231150081-nWR2QO576Hr6y1lkzgzfvzR0MymQfcw"

private val accessToken = AccessToken(
	ACCESS_TOKEN,
	ACCESS_TOKEN_SECRET
)

class AuthTest {

	private val context = ApplicationProvider.getApplicationContext<Context>()

	init {

		startKoin {
			twitlinModule(
				"ctNGOKkamPkXfFIcf4iQF37b7",
				"BlW8VyYa83nHaP84dfkkGoHuEDonBFKwaPdH6HMNJBPD3pRl1T"
			)
		}
		AndroidTwitlin.initialize(
			"938122027231150081-nWR2QO576Hr6y1lkzgzfvzR0MymQfcw",
			"938122027231150081-nWR2QO576Hr6y1lkzgzfvzR0MymQfcw"
		)
	}

	@Test
	fun authenticateTest() {
		runBlocking {
			Twitlin.api.authentication.authenticate("snsmate://response/request_token")
		}.onError {
			println("ERROR: " + it.joinToString(", ") { "${it.code} -> ${it.message}" })
		}.onSuccess {
			println("SUCCESS: $it")
		}

	}
}
