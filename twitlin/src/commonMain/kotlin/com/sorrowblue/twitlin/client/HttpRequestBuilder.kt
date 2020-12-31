/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.utils.urlEncode
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlinx.datetime.Clock
import kotlin.random.Random

@OptIn(InternalAPI::class)
internal val nextNonce
    get() = Random.nextBytes(32).encodeBase64().trim('=')

internal val nowTimestamp
    get() = Clock.System.now().epochSeconds.toString()

internal val Array<out Pair<String, Any?>>.notNullParams: List<Pair<String, String>>
    get() = mapNotNull { pair -> pair.second?.let { pair.first to it.toString() } }

internal fun HttpRequestBuilder.createSignature(
    consumerKey: String,
    consumerSecret: String,
    nonce: String,
    timestamp: String,
    accessToken: AccessToken?,
    params: List<Pair<String, String>>,
): String {
    val parameterString =
        collectParameters(consumerKey, nonce, timestamp, accessToken?.oauthToken, params)
    val baseString = creatingSignatureBaseString(
        method.value,
        "${url.protocol.name}://${url.host}${url.encodedPath}",
        parameterString
    )
    val signingKey = getSigningKey(consumerSecret, accessToken?.oauthTokenSecret)
    return calculateSignature(baseString, signingKey)
}

internal val List<Pair<String, String>>.oauthParams get() = takeWhile { it.first.startsWith("oauth_") }

internal fun HttpRequestBuilder.headerForTwitter(
    consumerKey: String,
    consumerSecret: String,
    params: List<Pair<String, String>>,
    accessToken: AccessToken?
) {
    val nc = nextNonce
    val ts = nowTimestamp
    val sign =
        createSignature(consumerKey, consumerSecret, nc, ts, accessToken, params)
    val parameters =
        collectParameters(consumerKey, nc, sign, ts, accessToken?.oauthToken, params.oauthParams)
    header(HttpHeaders.Authorization, buildHeaderString(parameters))
}

internal fun HttpRequestBuilder.headerForTwitter(bearerToken: BearerToken?) =
    header(HttpHeaders.Authorization, "Bearer ${bearerToken?.accessToken}")

internal fun HttpRequestBuilder.bodyForTwitter(params: List<Pair<String, String>>) {
    body = TextContent(
        params
            .mapNotNull { if (it.first.startsWith("oauth_")) null else it }
            .joinToString("&") { "${it.first.urlEncode()}=${it.second.urlEncode()}" },
        contentType = ContentType.Application.FormUrlEncoded
    )
    Napier.d(body.toString(), tag = "TAG")
}
