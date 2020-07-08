package com.sorrowblue.twitlin.net

internal expect fun hmacSHA1(key: ByteArray, value: ByteArray): String
