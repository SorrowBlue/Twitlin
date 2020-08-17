package com.sorrowblue.twitlin.tweets.status

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.test.Test.runTest
import com.sorrowblue.twitlin.test.initializeTest
import kotlin.test.Test

class StatusApiTest {

	init {
		initializeTest()
	}

	@Test
	fun lookupTest() = runTest {
		Twitlin.Api.statuses.lookup(listOf(1284807458699894785))
			.onSuccess {
				Napier.d("tweet = $it")
			}.onError {
				Napier.e("error = $it")
			}
	}
}
