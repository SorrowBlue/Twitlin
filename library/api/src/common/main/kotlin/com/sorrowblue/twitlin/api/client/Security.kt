package com.sorrowblue.twitlin.api.client

internal expect fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray
