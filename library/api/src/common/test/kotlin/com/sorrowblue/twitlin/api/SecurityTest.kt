package com.sorrowblue.twitlin.api

import com.sorrowblue.twitlin.api.client.hmacSHA1
import io.kotest.core.spec.style.FunSpec
import kotlin.test.assertEquals

/**
 * [Test Cases for HMAC-MD5 and HMAC-SHA-1](https://www.ietf.org/rfc/rfc2202.txt)
 */
class SecurityTest : FunSpec({

    test("HmacSHA1_1") {
        hmacSHA1(
            hexKey = "0x0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b",
            strData = "Hi There",
            hexDigest = "0xb617318655057264e28bc0b6fb378c8ef146be00"
        )
    }

    test("HmacSHA1_2") {
        hmacSHA1(
            strKey = "Jefe",
            strData = "what do ya want for nothing?",
            hexDigest = "0xeffcdf6ae5eb2fa2d27416d5f184df9c259a7c79"
        )
    }

    test("HmacSHA1_3") {
        hmacSHA1(
            hexKey = "0xaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            hexData = "0xdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",
            hexDigest = "0x125d7342b9ac11cd91a39af48aa17b4f63f175d3"
        )
    }

    test("HmacSHA1_4") {
        hmacSHA1(
            hexKey = "0x0102030405060708090a0b0c0d0e0f10111213141516171819",
            hexData = ("0x" + List(50) { "cd" }.joinToString("")),
            hexDigest = "0x4c9007f4026250c6bc8414f9bf50c86c2d7235da"
        )
    }

    test("HmacSHA1_5") {
        hmacSHA1(
            hexKey = "0x0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c",
            strData = "Test With Truncation",
            hexDigest = "0x4c1a03424b55e07fe7f27be1d58bb9324a9a5a04"
        )
    }

    test("HmacSHA1_6") {
        hmacSHA1(
            hexKey = ("0x" + List(80) { "aa" }.joinToString("")),
            strData = "Test Using Larger Than Block-Size Key - Hash Key First",
            hexDigest = "0xaa4ae5e15272d00e95705637ce8a3b55ed402112"
        )
    }

    test("HmacSHA1_7") {
        hmacSHA1(
            hexKey = ("0x" + List(80) { "aa" }.joinToString("")),
            strData = "Test Using Larger Than Block-Size Key and Larger Than One Block-Size Data",
            hexDigest = "0xe8e99d0f45237d786d6bbaa7965c7808bbff1a91"
        )
    }
})

private fun hexStringToByte(hexString: String) = hexString.toInt(16).toByte()

private fun hmacSHA1(
    hexKey: String? = null,
    strKey: String? = null,
    key: ByteArray? = null,
    hexData: String? = null,
    strData: String? = null,
    data: ByteArray? = null,
    hexDigest: String
) {
    val keyByte = hexKey?.drop(2)?.hexToByteArray() ?: strKey?.encodeToByteArray() ?: key
    val valueByte =
        hexData?.drop(2)?.hexToByteArray() ?: strData?.encodeToByteArray() ?: data
    val digest = hexDigest.drop(2).hexToByteArray()
    val result = hmacSHA1(keyByte!!, valueByte!!)
    assertEquals(digest.toList(), result.toList())
}

private fun String.hexToByteArray(): ByteArray {
    return ByteArray(length / 2) {
        val pointer = it * 2
        hexStringToByte(substring(pointer, pointer + 2))
    }
}
