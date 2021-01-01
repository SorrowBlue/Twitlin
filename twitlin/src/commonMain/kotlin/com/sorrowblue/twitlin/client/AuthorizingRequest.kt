/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.utils.urlEncode

internal fun collectParameters(
    consumerKey: String,
    nonce: String,
    signature: String,
    timestamp: String,
    oauthToken: String?,
    params: List<Pair<String, String>> = emptyList()
): List<Pair<String, String>> = listOf(
    "oauth_consumer_key" to consumerKey,
    "oauth_nonce" to nonce,
    "oauth_signature" to signature,
    "oauth_signature_method" to "HMAC-SHA1",
    "oauth_timestamp" to timestamp,
    "oauth_version" to "1.0"
).run {
    oauthToken?.let { plus("oauth_token" to oauthToken) } ?: this
}.plus(params).sortedBy { it.first }

internal fun buildHeaderString(params: List<Pair<String, String>>) =
    "OAuth ${params.joinToString(", ") { "${it.first.urlEncode()}=\"${it.second.urlEncode()}\"" }}".also {
        Napier.i("buildHeaderString() = $it", tag = "TwitlinClient")
    }
