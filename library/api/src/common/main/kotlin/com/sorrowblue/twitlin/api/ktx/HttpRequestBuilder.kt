package com.sorrowblue.twitlin.api.ktx

import com.sorrowblue.twitlin.api.client.ConsumerKeys
import com.sorrowblue.twitlin.api.client.calculateSignatureBase64
import com.sorrowblue.twitlin.api.client.creatingSignatureBaseString
import com.sorrowblue.twitlin.api.client.gettingSigningKey
import com.sorrowblue.twitlin.api.oauth.AccessToken
import com.sorrowblue.twitlin.api.oauth2.BearerToken
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.encodedPath
import io.ktor.util.encodeBase64
import kotlin.random.Random
import kotlinx.datetime.Clock

internal fun HttpRequestBuilder.createSignature(
    httpMethod: HttpMethod,
    consumerKeys: ConsumerKeys,
    nonce: String,
    timestamp: String,
    accessToken: AccessToken?,
    params: List<Pair<String, String>>
): String {
    val parameterString =
        com.sorrowblue.twitlin.api.client.collectingParameters(consumerKeys.key, nonce, timestamp, accessToken?.oauthToken, params)
    println("parameterString: $parameterString")
    val noQueryUrl = "${url.protocol.name}://${url.host}${url.encodedPath}"
    val baseString = creatingSignatureBaseString(httpMethod.value, noQueryUrl, parameterString)
    println("baseString: $baseString")
    val signingKey = gettingSigningKey(consumerKeys.secret, accessToken?.oauthTokenSecret)
    println("signingKey: $signingKey")
    return calculateSignatureBase64(baseString, signingKey)
}

internal fun HttpRequestBuilder.headerAuthorization(
    httpMethod: HttpMethod,
    consumerKeys: ConsumerKeys,
    accessToken: AccessToken?,
    postParams: List<Pair<String, String>> = emptyList()
) {
    val params = url.parameters.build().entries().map { it.key to it.value.first() } + postParams
    val nc = Random.nextBytes(32).encodeBase64().trim('=')
    val ts = Clock.System.now().epochSeconds.toString()
    val sign =
        createSignature(httpMethod, consumerKeys, nc, ts, accessToken, params)
    val parameters =
        collectingParameters(consumerKeys.key, nc, sign, ts, accessToken?.oauthToken, params)
    val headerValue = buildHeaderString(parameters)
    header(HttpHeaders.Authorization, headerValue)
}

public fun HttpRequestBuilder.headerAuthorization(bearerToken: BearerToken?) {
    header(HttpHeaders.Authorization, "Bearer ${bearerToken?.accessToken}")
}
