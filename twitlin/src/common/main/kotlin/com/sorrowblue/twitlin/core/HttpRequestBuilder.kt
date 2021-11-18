package com.sorrowblue.twitlin.core

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.client.ConsumerKeys
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlin.random.Random
import kotlinx.datetime.Clock

internal fun HttpRequestBuilder.createSignature(
    consumerKeys: ConsumerKeys,
    nonce: String,
    timestamp: String,
    accessToken: AccessToken?,
    params: List<Pair<String, String>>
): String {
    val parameterString =
        collectingParameters(consumerKeys.key, nonce, timestamp, accessToken?.oauthToken, params)
    val noQueryUrl = "${url.protocol.name}://${url.host}${url.encodedPath}"
    val baseString = creatingSignatureBaseString(method.value, noQueryUrl, parameterString)
    val signingKey = gettingSigningKey(consumerKeys.secret, accessToken?.oauthTokenSecret)
    return calculateSignatureBase64(baseString, signingKey)
}

@OptIn(InternalAPI::class)
internal fun HttpRequestBuilder.headerAuthorization(
    consumerKeys: ConsumerKeys,
    accessToken: AccessToken?,
    postParams: List<Pair<String, String>> = emptyList()
) {
    val params = url.parameters.build().entries().map { it.key to it.value.first() } + postParams
    val nc = Random.nextBytes(32).encodeBase64().trim('=')
    val ts = Clock.System.now().epochSeconds.toString()
    val sign =
        createSignature(consumerKeys, nc, ts, accessToken, params)
    val parameters =
        collectingParameters(consumerKeys.key, nc, sign, ts, accessToken?.oauthToken, params)
    val headerValue = buildHeaderString(parameters)
    header(HttpHeaders.Authorization, headerValue)
}

internal fun HttpRequestBuilder.headerAuthorization(bearerToken: BearerToken?) =
    header(HttpHeaders.Authorization, "Bearer ${bearerToken?.accessToken}")
