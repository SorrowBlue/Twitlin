package com.sorrowblue.twitlin.account

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.settings.Settings
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

private const val ACCESS_TOKEN = "938122027231150081-edSNSs0q0D9ahF9VW3zAUushpIbhrxz"
private const val ACCESS_TOKEN_SECRET = "V5e6HQ7zfltRkghgR1B0jBeq4bHHmq0VDfNo5ZT32Otll"

class AccountApiTest {

	init {
		Napier.base(TestAntilog())
		Twitlin.initialize(
			"ctNGOKkamPkXfFIcf4iQF37b7",
			"BlW8VyYa83nHaP84dfkkGoHuEDonBFKwaPdH6HMNJBPD3pRl1T",
			Settings(),
			ACCESS_TOKEN,
			ACCESS_TOKEN_SECRET
		)
	}

	@Test
	fun verifyCredentialsTest() {
		runBlocking {
			Twitlin.Api.account.verifyCredentials()
		}.onError {
			Napier.e(
				it.joinToString(", ") { "${it.code} -> ${it.message}" },
				tag = "verifyCredentialsTest"
			)
		}.onSuccess {
			Napier.d(it.toString(), tag = "verifyCredentialsTest")
		}
	}
}

class TestAntilog : Antilog() {
	override fun performLog(
		priority: Napier.Level,
		tag: String?,
		throwable: Throwable?,
		message: String?
	) {
		println("${priority.name}: [$tag]: $message")

	}
}
