/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.client.AppClient
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.buildHeaderString
import com.sorrowblue.twitlin.core.calculateSignatureBase64
import com.sorrowblue.twitlin.core.collectingParameters
import com.sorrowblue.twitlin.core.creatingSignatureBaseString
import com.sorrowblue.twitlin.core.gettingSigningKey
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlin.random.Random
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import mu.KLogger
import mu.KotlinLogging
import com.sorrowblue.twitlin.v2.client.AppClient as V2AppClient
import com.sorrowblue.twitlin.v2.client.UserClient as V2UserClient

/**
 * TODO
 */
public object Twitlin {

    private val logger = KotlinLogging.logger("com.sorrowblue.twitlin.Twitlin")

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
        defaultTimeZone = builder.timeZone
        userClient = UserClient(apiKey, apiSecret, builder.accessToken)
        appClient = AppClient(apiKey, apiSecret, builder.bearerToken)
        v2UserClient = V2UserClient(apiKey, apiSecret, builder.accessToken)
        v2AppClient = V2AppClient(apiKey, apiSecret, builder.bearerToken)
        logger.info { "Twitlin has been initialized." }
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
    }

    @OptIn(InternalAPI::class)
    public fun authorizationHeader(url: String): String {
        val nc = Random.nextBytes(32).encodeBase64().trim('=')
        val ts = Clock.System.now().epochSeconds.toString()

        val parameterString =
            collectingParameters(v2UserClient.apiKey, nc, ts, accessToken?.oauthToken, emptyList())
        val baseString = creatingSignatureBaseString("GET", url, parameterString)
        val signingKey = gettingSigningKey(v2UserClient.secretKey, accessToken?.oauthTokenSecret)
        val sign = calculateSignatureBase64(baseString, signingKey)
        val parameters =
            collectingParameters(v2UserClient.apiKey, nc, sign, ts, accessToken?.oauthToken, emptyList())
        return buildHeaderString(parameters)
    }
}

internal expect fun logLevel(logger: KLogger): KLogger
