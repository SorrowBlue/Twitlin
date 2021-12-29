package com.sorrowblue.twitlin.utils

import io.ktor.utils.io.core.toByteArray
import kotlin.test.Test
import kotlin.test.assertEquals

class SecurityTest {

    /**
     * [Test Cases for HMAC-MD5 and HMAC-SHA-1](https://www.ietf.org/rfc/rfc2202.txt)
     */
    @Test
    fun testHmacSHA1_1() {

        hmacSHA1(
            hexKey = "0x0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b0b",
            strData = "Hi There",
            hexDigest = "0xb617318655057264e28bc0b6fb378c8ef146be00"
        )
    }

    @Test
    fun testHmacSHA1_2() {
        hmacSHA1(
            strKey = "Jefe",
            strData = "what do ya want for nothing?",
            hexDigest = "0xeffcdf6ae5eb2fa2d27416d5f184df9c259a7c79"
        )
    }

    @Test
    fun testHmacSHA1_3() {
        hmacSHA1(
            hexKey = "0xaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            hexData = "0xdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd",
            hexDigest = "0x125d7342b9ac11cd91a39af48aa17b4f63f175d3"
        )
    }

    @Test
    fun testHmacSHA1_4() {
        hmacSHA1(
            hexKey = "0x0102030405060708090a0b0c0d0e0f10111213141516171819",
            hexData = ("0x" + List(50) { "cd" }.joinToString("")),
            hexDigest = "0x4c9007f4026250c6bc8414f9bf50c86c2d7235da"
        )
    }

    @Test
    fun testHmacSHA1_5() {
        hmacSHA1(
            hexKey = "0x0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c0c",
            strData = "Test With Truncation",
            hexDigest = "0x4c1a03424b55e07fe7f27be1d58bb9324a9a5a04"
        )
    }

    @Test
    fun testHmacSHA1_6() {
        hmacSHA1(
            hexKey = ("0x" + List(80) { "aa" }.joinToString("")),
            strData = "Test Using Larger Than Block-Size Key - Hash Key First",
            hexDigest = "0xaa4ae5e15272d00e95705637ce8a3b55ed402112"
        )
    }

    @Test
    fun testHmacSHA1_7() {
        hmacSHA1(
            hexKey = ("0x" + List(80) { "aa" }.joinToString("")),
            strData = "Test Using Larger Than Block-Size Key and Larger Than One Block-Size Data",
            hexDigest = "0xe8e99d0f45237d786d6bbaa7965c7808bbff1a91"
        )
    }

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

    @Test
    fun testSHA256_1() {
        val sha = sha256("abc".encodeToByteArray())
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", sha.toHexString())
    }

    @Test
    fun testSHA256_2() {
        val sha = sha256("".encodeToByteArray())
        assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", sha.toHexString())
    }

    @Test
    fun testSHA256_3() {
        val sha = sha256("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq".toByteArray())
        assertEquals("248d6a61d20638b8e5c026930c3e6039a33ce45964ff2167f6ecedd419db06c1", sha.toHexString())
    }

    @Test
    fun testSHA256_4() {
        val sha =
            sha256("abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu".encodeToByteArray())
        assertEquals("cf5b16a778af8380036ce59e7b0492370b249b11e8f07a51afac45037afee9d1", sha.toHexString())
    }

    @Test
    fun testSHA256_5() {
        val sha = sha256(List(1000000) { "a" }.joinToString("").also { println(it) }.encodeToByteArray())
        assertEquals("cdc76e5c9914fb9281a1c7e284d73e67f1809a48a497200e046d39ccc7112cd0", sha.toHexString())
    }

    private fun ByteArray.toHexString() =
        joinToString("") { (0xFF and it.toInt()).toString(16).padStart(2, '0') }
}
