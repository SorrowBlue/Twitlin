/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.github.aakira.napier.Antilog
import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.client.AppClient
import com.sorrowblue.twitlin.client.UserClient
import kotlinx.datetime.TimeZone
import com.sorrowblue.twitlin.v2.client.AppClient as V2AppClient
import com.sorrowblue.twitlin.v2.client.UserClient as V2UserClient

/**
 * TODO
 */
public object Twitlin {

    public var accessToken: AccessToken?
        get() = v2UserClient.accessToken
        set(value) {
            userClient.accessToken = value
            v2UserClient.accessToken = value
        }

    public var bearerToken: BearerToken?
        get() = v2AppClient.bearerToken
        set(value) {
            appClient.bearerToken = value
            v2AppClient.bearerToken = value
        }

    public const val TAG: String = "Twitlin"

    public val isAuthorizationRequired: Boolean get() = userClient.accessToken == null

    public fun initialize(apiKey: String, apiSecret: String, body: Builder.() -> Unit) {
        val builder = Builder().also(body)
        builder.antilog?.let(Napier::base)
        defaultTimeZone = builder.timeZone
        userClient = UserClient(apiKey, apiSecret, builder.accessToken)
        appClient = AppClient(apiKey, apiSecret, builder.bearerToken)
        v2UserClient = V2UserClient(apiKey, apiSecret, builder.accessToken)
        v2AppClient = V2AppClient(apiKey, apiSecret, builder.bearerToken)
        Napier.i("Twitlin has been initialized.")
    }

    internal lateinit var userClient: UserClient private set
    internal lateinit var appClient: AppClient private set
    internal lateinit var v2UserClient: V2UserClient private set
    internal lateinit var v2AppClient: V2AppClient private set
    internal var defaultTimeZone: TimeZone = TimeZone.UTC

    public class Builder internal constructor() {
        public var accessToken: AccessToken? = null
        public var bearerToken: BearerToken? = null
        public var timeZone: TimeZone = TimeZone.UTC
        public var antilog: Antilog? = defaultAntilog
    }
}

internal expect val defaultAntilog: Antilog
