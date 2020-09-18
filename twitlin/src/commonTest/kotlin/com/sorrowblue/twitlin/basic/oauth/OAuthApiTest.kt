package com.sorrowblue.twitlin.basic.oauth

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.basics.oauth.Authenticate
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuthApiTest : AbstractTest{

	@Test
	fun accessTokenTest() = runTest {
		val accessToken = TwitterAPI.oauth.accessToken(
			Authenticate("etQh_QAAAAABEkL_AAABdFc_n68", "yyDhGg66AwElV1qUNodd4EaU7LgJ54KK")
		).onSuccess {
			Napier.d("accessToken = $it")
		}.onError {
			Napier.e(it.joinToString(", ") { "${it.code}:${it.message}" })
		}.getOrNull()
		assertNotNull(accessToken, "accessToken is null")
	}

	@Test
	fun authenticateTest() = runTest {
		val url =
			TwitterAPI.oauth.requestToken("https://snsmate.sorrowblue.com").getOrNull()?.let {
				TwitterAPI.oauth.authenticate(it)
			}
		Napier.d("authenticate = $url")
		assertNotNull(url, "authenticate url is null")
	}

	@Test
	fun authorize() = runTest {
		val url =
			TwitterAPI.oauth.requestToken("https://snsmate.sorrowblue.com").getOrNull()?.let {
				TwitterAPI.oauth.authorize(it)
			}
		Napier.d("authorize = $url")
		assertNotNull(url, "authorize url is null")
	}


	@Test
	fun requestToken() = runTest {
		val token = TwitterAPI.oauth.requestToken("https://snsmate.sorrowblue.com")
			.onSuccess {
				Napier.d("oAuthToken = $it")
			}.onError {
				Napier.e(it.joinToString(", ") { "${it.code}:${it.message}" })
			}.getOrNull()
		assertNotNull(token, "oauthToken is null")
	}

	@Ignore
	@Test
	fun invalidateToken() {
		TODO()
	}
}
