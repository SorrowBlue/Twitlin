package com.sorrowblue.twitlin.utils

import io.ktor.util.InternalAPI
import io.ktor.util.decodeBase64Bytes
import io.ktor.util.encodeBase64
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray

@JsModule("jssha")
@JsName("jsSHA")
@JsNonModule
private external class JsSHA1(shaMode: String, shaType: String, options: Options) {

    fun update(value: String)
    fun setHMACKey(key: String, type: String)
    fun getHMAC(type: String): String
    fun getHash(type: String): String
}

internal class Options(val encoding: String)

@OptIn(InternalAPI::class)
internal actual fun hmacSHA1(key: ByteArray, value: ByteArray): ByteArray {
    val jsSha = JsSHA1("SHA-1", "HEX", Options("UTF8"))
    jsSha.setHMACKey(key.toHexString(), "HEX")
    jsSha.update(value.toHexString())
    return jsSha.getHMAC("HEX").hexToByteArray()
}

private fun ByteArray.toHexString() =
    joinToString("") { (0xFF and it.toInt()).toString(16).padStart(2, '0') }

private fun String.hexToByteArray(): ByteArray {
    fun hexStringToByte(hexString: String) = hexString.toInt(16).toByte()
    return ByteArray(length / 2) {
        val pointer = it * 2
        hexStringToByte(substring(pointer, pointer + 2))
    }
}

@OptIn(InternalAPI::class)
internal actual fun sha256(value: ByteArray): ByteArray {
    val jsSha = JsSHA1("SHA-256", "TEXT", Options("UTF8"))
    jsSha.update(value.decodeToString())
    return jsSha.getHash("BYTES").toByteArray(Charsets.ISO_8859_1)
}
