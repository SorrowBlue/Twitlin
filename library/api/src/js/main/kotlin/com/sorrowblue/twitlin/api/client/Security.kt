package com.sorrowblue.twitlin.api.client

@JsModule("jssha")
@JsName("jsSHA")
@JsNonModule
private external class JsSHA1(shaMode: String, shaType: String, options: Options) {

    fun update(value: String)
    fun setHMACKey(key: String, type: String)
    fun getHMAC(type: String): String
}

internal class Options(val encoding: String)

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
