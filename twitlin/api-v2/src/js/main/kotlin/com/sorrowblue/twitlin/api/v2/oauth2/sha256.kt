package com.sorrowblue.twitlin.api.v2.oauth2

import io.ktor.util.InternalAPI
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.toByteArray

@JsModule("jssha")
@JsName("jsSHA")
@JsNonModule
private external class JsSHA1(shaMode: String, shaType: String, options: Options) {

    fun update(value: String)
    fun getHash(type: String): String
}

internal class Options(val encoding: String)

@OptIn(InternalAPI::class)
internal actual fun sha256(value: ByteArray): ByteArray {
    val jsSha = JsSHA1("SHA-256", "TEXT", Options("UTF8"))
    jsSha.update(value.decodeToString())
    return jsSha.getHash("BYTES").toByteArray(Charsets.ISO_8859_1)
}
