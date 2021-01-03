/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

import io.ktor.util.InternalAPI
import io.ktor.util.encodeBase64
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@OptIn(InternalAPI::class)
public actual fun hmacSHA1(key: ByteArray, value: ByteArray): String {
    val signature = Mac.getInstance("HmacSHA1").run {
        init(SecretKeySpec(key, algorithm))
        doFinal(value)
    }!!
    return signature.encodeBase64()
}
