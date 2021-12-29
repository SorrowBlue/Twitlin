package com.sorrowblue.twitlin.utils

import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

internal actual fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray =
    Mac.getInstance("HmacSHA1").run {
        init(SecretKeySpec(key, algorithm))
        doFinal(value)
    }

private fun ByteArray.toHexString() =
    joinToString("") { (0xFF and it.toInt()).toString(16).padStart(2, '0') }

internal actual fun sha256(value: ByteArray): ByteArray {
    return MessageDigest.getInstance("SHA-256").apply {
        update(value)
    }.digest()
}
