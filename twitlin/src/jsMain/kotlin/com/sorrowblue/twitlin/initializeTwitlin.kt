package com.sorrowblue.twitlin

import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import com.sorrowblue.twitlin.basics.oauth2.BearerToken

actual fun initializeTwitlin(
	apiKey: String,
	apiSecret: String,
	accessToken: AccessToken?,
	bearerToken: BearerToken?,
	isEnabledDebug: Boolean
) {
	if (isEnabledDebug) Napier.base(DebugAntilog())
	Twitlin.initialize(apiKey, apiSecret, accessToken, bearerToken)
}
