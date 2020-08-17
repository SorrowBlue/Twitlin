package com.sorrowblue.twitlin.test

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.basics.oauth.AccessToken

const val API_KEY = "ctNGOKkamPkXfFIcf4iQF37b7"
const val API_SECRET = "BlW8VyYa83nHaP84dfkkGoHuEDonBFKwaPdH6HMNJBPD3pRl1T"

fun initializeTest() {
	Napier.base(TestAntilog())
	Twitlin.initialize(TestKey.API_KEY, TestKey.API_SECRET, AccessToken(TestKey.ACCESS_TOKEN, TestKey.ACCESS_TOKEN_SECRET))
}

expect object TestKey {

	val API_KEY: String
	val API_SECRET: String
	val ACCESS_TOKEN: String
	val ACCESS_TOKEN_SECRET: String
}
