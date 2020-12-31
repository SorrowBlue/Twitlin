/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.parseRFC822
import kotlinx.datetime.toLocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeTest {

    @Test
    fun parseISO8601Test() {
        val v2datetime = "2019-06-04T23:12:08.000Z"
        val expected = LocalDateTime(2019, 6, 4, 23, 12, 8, 0)
        val actual = Instant.parse(v2datetime)
        assertEquals(expected, actual.toLocalDateTime(TimeZone.UTC))
    }

    @Test
    fun parseRFC822Test() {
        val datetime = "Wed Oct 10 20:19:24 +0000 2018"
        val expected = LocalDateTime(2018, 10, 10, 20, 19, 24, 0)
        val actual = Instant.parseRFC822(datetime)
        assertEquals(expected, actual.toLocalDateTime(TimeZone.UTC))
    }
}
