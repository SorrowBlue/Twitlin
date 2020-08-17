package com.sorrowblue.twitlin.accounts_users.account

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.test.Test.runTest
import com.sorrowblue.twitlin.test.initializeTest
import kotlin.test.Test

class AccountApiTest {

	init {
		initializeTest()
	}

	@Test
	fun verifyCredentialsTest() = runTest {
		Twitlin.Api.account.verifyCredentials()
			.onError {
				Napier.e(
					it.joinToString(", ") { error -> "${error.code} -> ${error.message}" },
					tag = "verifyCredentialsTest"
				)
			}.onSuccess {
				Napier.d(it.toString(), tag = "verifyCredentialsTest")
			}
	}
}
