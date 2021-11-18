package com.sorrowblue.twitlin.utils

internal expect fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray

internal expect fun sha256(value: ByteArray): ByteArray
