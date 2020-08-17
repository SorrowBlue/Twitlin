package com.sorrowblue.twitlin

import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.basics.oauth.AccessToken

fun initializeTwitlin(apiKey: String, apiSecret: String, accessToken: AccessToken? = null) {
	Napier.base(DebugAntilog())
	Twitlin.initialize(apiKey, apiSecret, accessToken)
}
