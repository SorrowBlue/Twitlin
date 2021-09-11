/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.core

import com.sorrowblue.twitlin.authentication.AccessToken
import com.sorrowblue.twitlin.authentication.BearerToken
import com.sorrowblue.twitlin.utils.urlEncode
import com.sorrowblue.twitlin.v2.client.ConsumerKeys
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.content.TextContent
import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import kotlin.random.Random
import kotlinx.datetime.Clock
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
    val noQueryUrl = "${url.protocol.name}://${url.host}${url.encodedPath}"
    return AuthenticationUtils.createSignature(
        method,
        noQueryUrl,
        consumerKey,
        consumerSecret,
        nonce,
        timestamp,
        accessToken,
        params
    )
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
        AuthenticationUtils.collectingParameters(consumerKey, nc, sign, ts, accessToken?.oauthToken, params)
    val headerValue = AuthenticationUtils.buildHeaderString(parameters)
    header(HttpHeaders.Authorization, headerValue)
}

internal fun HttpRequestBuilder.createSignature(
    consumerKeys: ConsumerKeys,
    nonce: String,
    timestamp: String,
    accessToken: AccessToken?,
    params: List<Pair<String, String>>
): String {
    val noQueryUrl = "${url.protocol.name}://${url.host}${url.encodedPath}"
    return AuthenticationUtils.createSignature(
        method,
        noQueryUrl,
        consumerKeys,
        nonce,
        timestamp,
        accessToken,
        params
    )
}

@OptIn(InternalAPI::class)
internal fun HttpRequestBuilder.headerAuthorization(
    consumerKeys: ConsumerKeys,
    accessToken: AccessToken?
) {
    val nc = Random.nextBytes(32).encodeBase64().trim('=')
    val ts = Clock.System.now().epochSeconds.toString()
    val params = url.parameters.entries().map { it.key to it.value.first() }
    println("parameters: $params")
    val sign =
        createSignature(consumerKeys, nc, ts, accessToken, params)
    val parameters =
        AuthenticationUtils.collectingParameters(consumerKeys.key, nc, sign, ts, accessToken?.oauthToken, params)
    val headerValue = AuthenticationUtils.buildHeaderString(parameters)
    header(HttpHeaders.Authorization, headerValue)
}

internal fun HttpRequestBuilder.headerAuthorization(bearerToken: BearerToken?) =
    header(HttpHeaders.Authorization, "Bearer ${bearerToken?.accessToken}")

internal fun HttpRequestBuilder.bodyFormUrlEncoded(params: List<Pair<String, String>>, contentType: ContentType = ContentType.Application.FormUrlEncoded) {
    params.mapNotNull { if (it.first.startsWith("oauth_")) null else it }
        .joinToString("&") { "${it.first.urlEncode()}=${it.second.urlEncode()}" }.also {
            body = TextContent(it, contentType)
        }
}

internal inline fun <reified V : Any> HttpRequestBuilder.bodyJson(clazz: V) {
    body = TextContent(Json.encodeToString(clazz), ContentType.Application.Json)
}
