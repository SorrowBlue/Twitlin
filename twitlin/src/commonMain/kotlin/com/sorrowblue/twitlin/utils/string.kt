/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.utils

import io.ktor.http.encodeURLParameter

/**
 * TODO
 */
internal fun String.urlEncode() = encodeURLParameter()

/**
 * TODO
 *
 * @return TODO
 */
internal fun String.toMap(): Map<String, String> =
    split('&').associate {
        val params = it.split('=')
        if (params.size != 2) {
            throw IllegalStateException("\"$it\" is not parameter format.")
        }
        params[0] to params[1]
    }
