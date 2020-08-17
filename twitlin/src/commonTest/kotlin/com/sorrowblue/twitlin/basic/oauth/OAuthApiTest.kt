package com.sorrowblue.twitlin.basic.oauth

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.*
import com.sorrowblue.twitlin.basics.oauth.Authenticate
import com.sorrowblue.twitlin.test.API_KEY
import com.sorrowblue.twitlin.test.API_SECRET
import com.sorrowblue.twitlin.test.Test.runTest
import com.sorrowblue.twitlin.test.TestAntilog
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuthApiTest {

	init {
		Napier.base(TestAntilog())
		Twitlin.initialize(API_KEY, API_SECRET, null)
	}

	@Test
	fun accessTokenTest() = runTest {
		val accessToken = Twitlin.Api.oauth.accessToken(
			Authenticate("8i8GHgAAAAABEkL_AAABc8wxupU", "VKiCob2juC3GTYr9MeXU5eBt5QqAHzhs")
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
			Twitlin.Api.oauth.requestToken("https://snsmate.sorrowblue.com").getOrNull()?.let {
				Twitlin.Api.oauth.authenticate(it)
			}
		Napier.d("authenticate = $url")
		assertNotNull(url, "authenticate url is null")
	}

	@Test
	fun authorize() = runTest {
		val url =
			Twitlin.Api.oauth.requestToken("https://snsmate.sorrowblue.com").getOrNull()?.let {
				Twitlin.Api.oauth.authorize(it)
			}
		Napier.d("authorize = $url")
		assertNotNull(url, "authorize url is null")
	}


	@Test
	fun requestToken() = runTest {
		val token = Twitlin.Api.oauth.requestToken("https://snsmate.sorrowblue.com")
			.onSuccess {
				Napier.d("oAuthToken = $it")
			}.onError {
				Napier.e(it.joinToString(", ") { "${it.code}:${it.message}" })
			}.getOrNull()
		assertNotNull(token, "oauthToken is null")
	}

	@Test
	fun invalidateToken() {
		TODO()
	}
}
