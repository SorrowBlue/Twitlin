package com.sorrowblue.twitlin

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.test.Test.runTest
import com.sorrowblue.twitlin.test.initializeTest
import com.sorrowblue.twitlin.v2.objects.Tweet
import kotlin.test.Test

class ApiV2Test {

	init {
		initializeTest()
	}

	@Test
	fun testFoo() = runTest {
		Twitlin.v2Client.get<List<Tweet>>(
			"https://api.twitter.com/2/tweets",
			"ids" to "1281584650985463809,1276457123744899073,1276456989640454148"
		)
			.onSuccess {
				Napier.d("onSuccess = $it")
			}.onFailure {
				Napier.d("onFailure = $it")
			}
	}

	@Test
	fun napierTest() = runTest {
		Napier.d("run test", tag = "TEST")
	}
}

