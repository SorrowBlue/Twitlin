/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.v2.Client

/**
 * TODO
 */
public object Twitlin {

    internal lateinit var client: TwitlinClient private set
    internal lateinit var v2Client: Client private set

    public var accessToken: AccessToken?
        get() = client.accessToken
        set(value) {
            client.accessToken = value
            v2Client.accessToken = value
        }

    public val isAuthorizationRequired: Boolean get() = client.accessToken == null

    internal fun initialize(
        apiKey: String,
        apiSecret: String,
        accessToken: AccessToken? = null,
        bearerToken: BearerToken? = null
    ) {
        Napier.i("Twitlin has been initialized.", tag = "Twitlin")
        client = TwitlinClient(apiKey, apiSecret, accessToken, bearerToken)
        v2Client = Client(apiKey, apiSecret, accessToken, bearerToken)
    }
}

/**
 * TODO
 *
 * @param apiKey
 * @param apiSecret
 * @param accessToken
 * @param bearerToken
 * @param isEnabledDebug
 */
public expect fun initializeTwitlin(
    apiKey: String,
    apiSecret: String,
    accessToken: AccessToken? = null,
    bearerToken: BearerToken? = null,
    isEnabledDebug: Boolean = false
)
