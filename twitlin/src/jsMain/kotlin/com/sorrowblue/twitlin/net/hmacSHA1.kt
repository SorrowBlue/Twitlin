package com.sorrowblue.twitlin.net

@JsModule("crypto-js/hmac-sha1")
@JsNonModule
@JsName("HmacSHA1")
private external fun hmacSHA1(value: String, key: String): String

@JsModule("crypto-js/enc-base64")
@JsNonModule
private external class Base64 {
    companion object {
        fun stringify(str: String): String
    }
}

internal actual fun hmacSHA1(key: ByteArray, value: ByteArray): String {
    return Base64.stringify(hmacSHA1(value.decodeToString(), key.decodeToString()))
}
