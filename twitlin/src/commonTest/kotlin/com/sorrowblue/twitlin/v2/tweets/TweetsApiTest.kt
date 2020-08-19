package com.sorrowblue.twitlin.v2.tweets

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.test.Test.runTest
import com.sorrowblue.twitlin.test.initializeTest
import kotlin.test.Test
import kotlin.test.assertNotNull
class TweetsApiTest {

	init {
		initializeTest()
	}

	@Test

	fun tweetsIdTest() = runTest {
		val tweet = Twitlin.v2.tweetsApi.tweets("1067094924124872705")
			.onSuccess {
				Napier.d("onSuccess = $it", tag = "TweetsApiTest")
			}.onFailure {
				Napier.e("onFailure = $it", tag = "TweetsApiTest")
			}.dataOrNull()
		assertNotNull(tweet)
	}
}
