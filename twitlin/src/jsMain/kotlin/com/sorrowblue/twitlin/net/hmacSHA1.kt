package com.sorrowblue.twitlin.net

external fun HmacSHA1(value: String, key: String): String

external class Base64 {
	companion object {
		fun stringify(str: String): String
	}
}

internal actual fun hmacSHA1(key: ByteArray, value: ByteArray): String =
	Base64.stringify(HmacSHA1(value.decodeToString(), key.decodeToString())) as String
