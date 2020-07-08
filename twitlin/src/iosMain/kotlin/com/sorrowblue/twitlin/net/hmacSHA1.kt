package com.sorrowblue.twitlin.net

import com.soywiz.krypto.sha1

internal actual fun hmacSHA1(key: ByteArray, value: ByteArray): String {
	return key.sha1().base64
}
