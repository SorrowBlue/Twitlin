package com.sorrowblue.twitlin.api.v2

import com.sorrowblue.twitlin.api.v2.oauth2.sha256
import io.ktor.utils.io.core.toByteArray
import kotlin.test.Test
import kotlin.test.assertEquals

class SecurityTest {

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
