package com.sorrowblue.twitlin.api.client

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

internal actual fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray =
    Mac.getInstance("HmacSHA1").run {
        init(SecretKeySpec(key, algorithm))
        doFinal(value)
    }
