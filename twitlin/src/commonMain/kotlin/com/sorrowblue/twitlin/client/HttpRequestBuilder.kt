/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.core.buildHeaderString
import com.sorrowblue.twitlin.core.calculateSignatureBase64
import com.sorrowblue.twitlin.core.collectingParameters
import com.sorrowblue.twitlin.core.creatingSignatureBaseString
import com.sorrowblue.twitlin.core.gettingSigningKey
import com.sorrowblue.twitlin.utils.urlEncode
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.random.Random

internal val Array<out Pair<String, Any?>>.notNullParams: List<Pair<String, String>>
    get() = mapNotNull { pair -> pair.second?.let { pair.first to it.toString() } }

internal fun HttpRequestBuilder.createSignature(
    consumerKey: String,
    consumerSecret: String,
    nonce: String,
    timestamp: String,
    accessToken: AccessToken?,
    params: List<Pair<String, String>>
): String {
    val parameterString =
        collectingParameters(consumerKey, nonce, timestamp, accessToken?.oauthToken, params)
    val noQueryUrl = "${url.protocol.name}://${url.host}${url.encodedPath}"
    val baseString = creatingSignatureBaseString(method.value, noQueryUrl, parameterString)
    val signingKey = gettingSigningKey(consumerSecret, accessToken?.oauthTokenSecret)
    return calculateSignatureBase64(baseString, signingKey)
}


@OptIn(InternalAPI::class)
internal fun HttpRequestBuilder.headerAuthorization(
    consumerKey: String,
    consumerSecret: String,
    params: List<Pair<String, String>>,
    accessToken: AccessToken?
) {
    val nc = Random.nextBytes(32).encodeBase64().trim('=')
    val ts = Clock.System.now().epochSeconds.toString()
    val sign =
        createSignature(consumerKey, consumerSecret, nc, ts, accessToken, params)
    val parameters =
        collectingParameters(consumerKey, nc, sign, ts, accessToken?.oauthToken, params)
    val headerValue = buildHeaderString(parameters)
    header(HttpHeaders.Authorization, headerValue)
}

internal fun HttpRequestBuilder.headerAuthorization(bearerToken: BearerToken?) =
    header(HttpHeaders.Authorization, "Bearer ${bearerToken?.accessToken}")

internal fun HttpRequestBuilder.bodyFormUrlEncoded(params: List<Pair<String, String>>) {
    params.mapNotNull { if (it.first.startsWith("oauth_")) null else it }
        .joinToString("&") { "${it.first.urlEncode()}=${it.second.urlEncode()}" }.also {
            body = TextContent(it, ContentType.Application.FormUrlEncoded)
        }
}

internal inline fun <reified V : Any> HttpRequestBuilder.bodyJson(clazz: V) {
    body = TextContent(Json.encodeToString(clazz), ContentType.Application.Json)
}
