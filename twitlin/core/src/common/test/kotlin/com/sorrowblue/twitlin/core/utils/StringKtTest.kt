@file:Suppress("SpellCheckingInspection")
package com.sorrowblue.twitlin.core.utils

import com.sorrowblue.twitlin.core.util.urlEncode
import kotlin.test.Test
import kotlin.test.assertEquals

class StringKtTest {

    @Test
    fun testUrlEncodeTwitterAPI_1() {
        assertEquals("Ladies%20%2B%20Gentlemen", "Ladies + Gentlemen".urlEncode())
    }

    @Test
    fun testUrlEncodeTwitterAPI_2() {
        assertEquals("An%20encoded%20string%21", "An encoded string!".urlEncode())
    }

    @Test
    fun testUrlEncodeTwitterAPI_3() {
        assertEquals("Dogs%2C%20Cats%20%26%20Mice", "Dogs, Cats & Mice".urlEncode())
    }

    @Test
    fun testUrlEncodeTwitterAPI_4() {
        assertEquals("%E2%98%83", "☃".urlEncode())
    }

    @Test
    fun testUrlEncodeUnreservedAlpha() {
        assertEquals(
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".urlEncode()
        )
    }

    @Test
    fun testUrlEncodeUnreservedDigit() {
        assertEquals("0123456789", "0123456789".urlEncode())
    }

    @Test
    fun testUrlEncodeUnreservedSymbols() {
        assertEquals("-._~", "-._~".urlEncode())
    }

    @Test
    fun testUrlEncodeReservedCharacters() {
        assertEquals(
            "%21%22%23%24%25%26%27%28%29%2A%2B%2C%2F%3A%3B%3C%3D%3E%3F%40%5B%5C%5D%5E%60%7B%7C%7D",
            """!"#$%&'()*+,/:;<=>?@[\]^`{|}""".urlEncode()
        )
    }
}
