package com.sorrowblue.twitlin

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.basics.oauth.AccessToken
import com.sorrowblue.twitlin.basics.oauth2.BearerToken
import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.v2.Client as V2Client

object Twitlin {

    internal lateinit var client: Client private set
    internal lateinit var v2Client: V2Client private set

    var accessToken: AccessToken?
        get() = client.accessToken
        set(value) {
            client.accessToken = value
            v2Client.accessToken = value
        }

    val isAuthorizationRequired get() = client.accessToken == null

    internal fun initialize(
        apiKey: String,
        apiSecret: String,
        accessToken: AccessToken? = null,
        bearerToken: BearerToken? = null
    ) {
        Napier.i("Twitlin has been initialized.", tag = "Twitlin")
        client = Client(apiKey, apiSecret, accessToken, bearerToken)
        v2Client = V2Client(apiKey, apiSecret, accessToken, bearerToken)
    }
}

expect fun initializeTwitlin(
    apiKey: String,
    apiSecret: String,
    accessToken: AccessToken? = null,
    bearerToken: BearerToken? = null,
    isEnabledDebug: Boolean = false
)
