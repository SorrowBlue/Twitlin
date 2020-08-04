package com.sorrowblue.twitlin.net

@JsModule("crypto-js/hmac-sha1")
@JsNonModule
private external fun HmacSHA1(value: String, key: String): String

@JsModule("crypto-js/enc-base64")
@JsNonModule
private external class Base64 {
	companion object {
		fun stringify(str: String): String
	}
}

internal actual fun hmacSHA1(key: ByteArray, value: ByteArray): String {
	return Base64.stringify(HmacSHA1(value.decodeToString(), key.decodeToString()))
}
