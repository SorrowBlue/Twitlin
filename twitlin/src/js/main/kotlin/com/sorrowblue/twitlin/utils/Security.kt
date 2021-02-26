/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

import kotlin.experimental.and

@JsModule("jssha")
@JsName("jsSHA")
@JsNonModule
private external class JsSHA1(shaMode: String, shaType: String, options: Options) {

    fun update(value: String)
    fun setHMACKey(key: String, type: String)
    fun getHMAC(type: String): String
}

internal class Options(val encoding: String)

internal actual object Security {

    actual fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray {
        println(
            """
            key: ${key.toHexString()}
            value: ${value.toHexString()}
            """.trimIndent()
        )
        val jsSha = JsSHA1("SHA-1", "HEX", Options("UTF8"))
        jsSha.setHMACKey(key.toHexString(), "HEX")
        jsSha.update(value.toHexString())
        return jsSha.getHMAC("HEX").also {
            println("hex=$it")
        }.hexToByteArray()
    }

    fun ByteArray.toHexString() =
        joinToString("") { (0xFF and it.toInt()).toString(16).padStart(2, '0') }

    private fun String.hexToByteArray(): ByteArray {
        fun hexStringToByte(hexString: String) = hexString.toInt(16).toByte()
        return ByteArray(length / 2) {
            val pointer = it * 2
            hexStringToByte(substring(pointer, pointer + 2))
        }
    }

    private val hexArray = "0123456789ABCDEF".toCharArray()

    fun ByteArray.toHex(): String {
        val hexChars = CharArray(size * 2)
        forEachIndexed { index, byte ->
            val v = (byte and 0xFF.toByte()).toInt()

            hexChars[index * 2] = hexArray[v ushr 4]
            hexChars[index * 2 + 1] = hexArray[v and 0x0F]
        }
        return hexChars.concatToString()
    }
}
