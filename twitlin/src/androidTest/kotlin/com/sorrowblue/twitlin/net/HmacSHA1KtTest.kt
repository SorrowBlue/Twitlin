package com.sorrowblue.twitlin.net

import kotlin.test.Test
import kotlin.test.assertEquals

class HmacSHA1KtTest {

    @Test
    fun hmacSHA1Test() {
        val key = "hmacSHA1Test_key"
        val value = "hmacSHA1Test_value"
        val sha1 = hmacSHA1(key.encodeToByteArray(), value.encodeToByteArray())
        println("[Android] HmacSHA1KtTest.hmacSHA1Test() sha1 = $sha1 ")
        assertEquals("rCPgm2nXs4jICb4YsmK/j53ox2Q=", sha1)
    }

}
