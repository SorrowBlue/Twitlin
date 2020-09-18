package com.sorrowblue.twitlin.basic.oauth2

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuth2ApiTest : AbstractTest {

	@Test
	fun tokenTest() = runTest {
		val token = TwitterAPI.oauth2.token().onSuccess {
			Napier.d("bearerToken = $it")
		}.onError {
			Napier.e(it.joinToString(",") { "${it.code}:${it.message}" })
		}.getOrNull()
		assertNotNull(token)
	}

	@Test
	fun invalidateTokenTest() = runTest {
		val b = TwitterAPI.oauth2.token().getOrNull() ?: return@runTest
		Napier.d("bearerToken = $b")
		TwitterAPI.oauth2.invalidateToken(b)
			.onSuccess {
				Napier.d("invalidateToken = $it")
			}.onError {
				Napier.e("invalidateToken = " + it.joinToString(",") { "${it.code}:${it.message}" })
			}
	}
}
