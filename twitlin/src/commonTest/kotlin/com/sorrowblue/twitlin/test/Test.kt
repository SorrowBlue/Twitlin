package com.sorrowblue.twitlin.test

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.basics.oauth.AccessToken

expect object Test {
	fun runTest(block: suspend () -> Unit)
}

fun initializeTest() {
	Napier.base(TestAntilog())
	Twitlin.initialize(TestKey.API_KEY, TestKey.API_SECRET, AccessToken(TestKey.ACCESS_TOKEN, TestKey.ACCESS_TOKEN_SECRET))
}

