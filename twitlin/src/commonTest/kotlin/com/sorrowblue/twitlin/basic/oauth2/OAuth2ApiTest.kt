package com.sorrowblue.twitlin.basic.oauth2

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.*
import com.sorrowblue.twitlin.test.Test.runTest
import com.sorrowblue.twitlin.test.initializeTest
import kotlin.test.Test
import kotlin.test.assertNotNull

class OAuth2ApiTest {

	init {
		initializeTest()
	}

	@Test
	fun tokenTest() = runTest {
		val token = Twitlin.Api.oauth2.token().onSuccess {
			Napier.d("bearerToken = $it")
		}.onError {
			Napier.e(it.joinToString(",") { "${it.code}:${it.message}" })
		}.getOrNull()
		assertNotNull(token)
	}

	@Test
	fun invalidateTokenTest() = runTest {
		val b = Twitlin.Api.oauth2.token().getOrNull() ?: return@runTest
		Napier.d("bearerToken = $b")
		Twitlin.Api.oauth2.invalidateToken(b)
			.onSuccess {
				Napier.d("invalidateToken = $it")
			}.onError {
				Napier.e("invalidateToken = " + it.joinToString(",") { "${it.code}:${it.message}" })
			}
	}
}
