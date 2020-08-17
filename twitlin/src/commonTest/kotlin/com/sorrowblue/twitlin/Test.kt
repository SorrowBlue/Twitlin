package com.sorrowblue.twitlin

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import com.sorrowblue.twitlin.basics.oauth.OAuthToken
import com.sorrowblue.twitlin.objects.TwitterUser
import com.sorrowblue.twitlin.test.*
import com.sorrowblue.twitlin.test.Test.runTest
import kotlin.test.Test

class Test {

	init {
		Napier.base(TestAntilog())
		Twitlin.initialize(TestKey.API_KEY, TestKey.API_SECRET, null)
	}

	@Test
	fun testTest() = runTest {
		Twitlin.client.post2<TwitterUser>(
			"https://api.twitter.com/1.1/account/update_profile.json",
			"url" to "https://sorrowblue.com/apex"
		).onSuccess {
			Napier.d("onSuccess = $it")
		}.onError {
			Napier.e("onError = $it")
		}
	}

	@Test
	fun testTest2() = runTest {
		Twitlin.client.post2<String>(
			"https://api.twitter.com/oauth/request_token",
			"oauth_callback" to "https://snsmate.sorrowblue.com"
		).onSuccess {
			Napier.d("onSuccess = ${OAuthToken.fromString(it)}")
		}.onError {
			Napier.e("onError = $it")
		}
	}

	@Test
	fun testTest3() = runTest {
		Twitlin.client.post2<String>(
			"https://api.twitter.com/oauth/access_token",
			"oauth_verifier" to "d9UxUHEhXHa2VQT09SzCQtogCcwUEwLu",
			overrideOAuthToken = "ubTkrAAAAAABEkL_AAABc_lAnZE"
		).onSuccess {
			Napier.d("onSuccess = ${AccessToken.fromString(it)}")
		}.onError {
			Napier.e("onError = $it")
		}
	}
}
