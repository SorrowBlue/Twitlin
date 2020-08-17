package com.sorrowblue.twitlin.v2.users

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.Test.runTest
import com.sorrowblue.twitlin.test.initializeTest
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField
import com.sorrowblue.twitlin.v2.tweets.toParameter
import kotlin.test.Test
import kotlin.test.assertNotNull

class UserApiTest {

	init {
		initializeTest()
	}

	@Test
	fun usersIdTest() = runTest {
		Twitlin.v2.usersApi.users(
			"2244994945",
			tweetFields = TweetField.all(),
			userFields = UserField.all(),
			expansions = UsersApi.Expansion.all()
		).onSuccess {
			Napier.d("onSuccess = $it")
		}.onFailure {
			Napier.e("onFailure = $it")
		}.dataOrNull().also {
			assertNotNull(it, "")
		}
	}

	@Test
	fun usersIdsTest() = runTest {
		Twitlin.v2.usersApi.users(
			listOf("2244994945", "6253282"),
			tweetFields = TweetField.all(),
			userFields = UserField.all(),
			expansions = UsersApi.Expansion.all()
		).onSuccess {
			Napier.d("onSuccess = $it")
		}.onFailure {
			Napier.e("onFailure = $it")
		}.dataOrNull().also {
			assertNotNull(it, "")
		}
	}
}
