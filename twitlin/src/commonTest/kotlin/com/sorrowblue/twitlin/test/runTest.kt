package com.sorrowblue.twitlin.test

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.basics.oauth.AccessToken

const val API_KEY = "ctNGOKkamPkXfFIcf4iQF37b7"
const val API_SECRET = "BlW8VyYa83nHaP84dfkkGoHuEDonBFKwaPdH6HMNJBPD3pRl1T"
const val ACCESS_TOKEN = "938122027231150081-edSNSs0q0D9ahF9VW3zAUushpIbhrxz"
const val ACCESS_TOKEN_SECRET = "V5e6HQ7zfltRkghgR1B0jBeq4bHHmq0VDfNo5ZT32Otll"

fun initializeTest() {
	Napier.base(TestAntilog())
	Twitlin.initialize(API_KEY, API_SECRET, AccessToken(ACCESS_TOKEN, ACCESS_TOKEN_SECRET))
}
