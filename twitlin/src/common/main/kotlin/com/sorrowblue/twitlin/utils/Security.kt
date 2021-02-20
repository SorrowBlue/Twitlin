/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

internal expect object Security {
    fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray
}
