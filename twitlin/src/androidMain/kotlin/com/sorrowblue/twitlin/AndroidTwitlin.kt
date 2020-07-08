package com.sorrowblue.twitlin

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier

object AndroidTwitlin {
	fun initialize(oAuthToken: String? = null, oAuthTokenSecret: String? = null, antilog: Antilog) {
		Twitlin.initialize(oAuthToken, oAuthTokenSecret)
		Napier.base(antilog)
	}

	fun initialize(oAuthToken: String? = null, oAuthTokenSecret: String? = null) {
		Twitlin.initialize(oAuthToken, oAuthTokenSecret)
		Napier.base(DebugAntilog())
	}
}
