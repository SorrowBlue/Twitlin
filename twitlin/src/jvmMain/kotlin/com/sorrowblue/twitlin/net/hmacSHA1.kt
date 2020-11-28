package com.sorrowblue.twitlin.net

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@OptIn(InternalAPI::class)
internal actual fun hmacSHA1(key: ByteArray, value: ByteArray): String {
    val signature = Mac.getInstance("HmacSHA1").run {
        init(SecretKeySpec(key, algorithm))
        doFinal(value)
    }!!
    return signature.encodeBase64()
}
