package com.sorrowblue.twitlin.core.util

internal expect fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray
