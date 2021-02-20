/*
 * (c) 2020-2021 SorrowBlue.
 */

package kotlinx.datetime

import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeKtxKtTest {

    private val instant = Instant.fromEpochMilliseconds(1610536964000)
    private val rfc822Str = "Wed Jan 13 11:22:44 +0000 2021"

    @Test
    fun testToInstantForRFC822() {
        val expected = rfc822Str.toInstantForRFC822()
        assertEquals(expected, instant)
    }

    @Test
    fun testFormatRFC822() {
        val expected = instant.formatRFC822()
        assertEquals(expected, rfc822Str)
    }
}
