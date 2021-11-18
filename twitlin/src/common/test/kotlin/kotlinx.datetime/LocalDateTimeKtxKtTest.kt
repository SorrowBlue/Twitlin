package kotlinx.datetime

import com.sorrowblue.twitlin.Twitlin
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeKtxKtTest {

    private val instant = Instant.fromEpochMilliseconds(1610536964000)
    private val rfc822Str = "Wed Jan 13 11:22:44 +0000 2021"

    @Test
    fun testToInstantForRFC822() {
        val expected = Instant.parseRfc822(rfc822Str)
        assertEquals(expected, instant)
    }

    @Test
    fun testFormatRFC822() {
        val expected = instant.formatRFC822()
        assertEquals(expected, rfc822Str)
    }

    @Test
    fun testEpochToLocalDateTime() {
        val epoch = Int.MAX_VALUE
        println(epoch.epochToLocalDateTime())
        val longEpoch = 31556889832780799L
        println(longEpoch.epochToLocalDateTime())
    }

    @Test
    fun testRFC822() {
        val epoch = "Wed Oct 10 20:19:24 +0000 2018"
        println(Instant.parseRfc822(epoch).toLocalDateTime(TimeZone.UTC))
    }

    @Test
    fun testTimeZone() {
        println(Clock.System.now().formatRFC822())
        println(Clock.System.now().formatRFC822(TimeZone.of("+0900")))
        println(Clock.System.now().formatRFC822(TimeZone.of("UTC")))
        println(Clock.System.now().formatRFC822(TimeZone.of("GMT")))
        println(Clock.System.now().formatRFC822(TimeZone.UTC))
    }
}
