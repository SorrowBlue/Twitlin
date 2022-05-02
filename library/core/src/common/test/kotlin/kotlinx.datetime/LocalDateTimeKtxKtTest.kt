package kotlinx.datetime

import io.kotest.core.spec.style.FunSpec
import kotlin.test.assertEquals

class LocalDateTimeKtxKtTest : FunSpec({

    val instant = Instant.fromEpochMilliseconds(1610536964000)
    val rfc822Str = "Wed Jan 13 11:22:44 +0000 2021"

    test("ToInstantForRFC822") {
        val expected = Instant.parseRfc822(rfc822Str)
        assertEquals(expected, instant)
    }

    test("EpochToLocalDateTime") {
        val epoch = Int.MAX_VALUE
        println(epoch.epochToLocalDateTime())
        val longEpoch = 31556889832780799L
        println(longEpoch.epochToLocalDateTime())
    }

    test("RFC822") {
        val epoch = "Wed Oct 10 20:19:24 +0000 2018"
        println(Instant.parseRfc822(epoch).toLocalDateTime(TimeZone.UTC))
    }
})
