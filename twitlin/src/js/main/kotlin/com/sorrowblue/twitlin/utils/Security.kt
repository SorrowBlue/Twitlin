/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

@JsModule("crypto-js/hmac-sha1")
@JsNonModule
@JsName("HmacSHA1")
private external fun hmacSHA1(value: String, key: String): Any

internal actual object Security {

    actual fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray {
        return hmacSHA1(value.decodeToString(), key.decodeToString()).toString().hexToByteArray()

    }

    private fun String.hexToByteArray(): ByteArray {
        fun hexStringToByte(hexString: String) = hexString.toInt(16).toByte()
        return ByteArray(length / 2) {
            val pointer = it * 2
            hexStringToByte(substring(pointer, pointer + 2))
        }
    }


}
