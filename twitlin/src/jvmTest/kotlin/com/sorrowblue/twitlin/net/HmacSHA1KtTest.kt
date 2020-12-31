/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.net

import com.sorrowblue.twitlin.utils.hmacSHA1
import kotlin.test.Test

class HmacSHA1KtTest {

    @Test
    fun hmacSHA1Test() {
        val key = "hmacSHA1Test_key"
        val value = "hmacSHA1Test_value"
        val sha1 = hmacSHA1(key.encodeToByteArray(), value.encodeToByteArray())
        println("jvm hmacSHA1Test() sha1 = $sha1")
        // rCPgm2nXs4jICb4YsmK/j53ox2Q=
    }

}
