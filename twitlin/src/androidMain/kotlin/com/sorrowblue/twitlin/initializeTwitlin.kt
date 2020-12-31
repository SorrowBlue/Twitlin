/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin

import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken

/**
 * TODO
 *
 * @param apiKey
 * @param apiSecret
 * @param accessToken
 * @param bearerToken
 * @param isEnabledDebug
 */
public actual fun initializeTwitlin(
    apiKey: String,
    apiSecret: String,
    accessToken: AccessToken?,
    bearerToken: BearerToken?,
    isEnabledDebug: Boolean
) {
    if (isEnabledDebug) Napier.base(DebugAntilog("Twitlin"))
    Twitlin.initialize(apiKey, apiSecret, accessToken, bearerToken)
}
