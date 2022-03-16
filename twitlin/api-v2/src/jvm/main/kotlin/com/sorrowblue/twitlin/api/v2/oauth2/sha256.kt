package com.sorrowblue.twitlin.api.v2.oauth2

import java.security.MessageDigest

internal actual fun sha256(value: ByteArray): ByteArray {
    return MessageDigest.getInstance("SHA-256").apply {
        update(value)
    }.digest()
}
