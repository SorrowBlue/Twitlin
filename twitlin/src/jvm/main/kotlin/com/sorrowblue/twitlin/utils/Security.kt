/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

internal actual object Security {

    actual fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray =
        Mac.getInstance("HmacSHA1").run {
            init(SecretKeySpec(key, algorithm))
            doFinal(value)
        }

    actual fun sha256(key: ByteArray): ByteArray {
        return MessageDigest.getInstance("SHA-256")
            .digest(key)
    }
}
