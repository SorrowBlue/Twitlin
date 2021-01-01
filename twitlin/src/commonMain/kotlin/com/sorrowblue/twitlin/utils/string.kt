/*
 * (c) 2020 SorrowBlue.
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
internal fun String.toMap(): Map<String, Any> =
    split('&').associate {
        val params = it.split('=')
        if (params.size != 2) {
            throw IllegalStateException("\"$it\" is not parameter format.")
        }
        val value = params[1]
        params[0] to if (value == "true" || value == "false") {
            value.toBoolean()
        } else {
            value
        }
    }
