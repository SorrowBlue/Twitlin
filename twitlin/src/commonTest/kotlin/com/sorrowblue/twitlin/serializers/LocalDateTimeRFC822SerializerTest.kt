/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.serializers

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LocalDateTimeRFC822SerializerTest {

    private val source = LocalDateTime(2017, 7, 11, 8, 38, 19)
        .toInstant(TimeZone.UTC)
        .toLocalDateTime(TimeZone.currentSystemDefault())
    private val strSource = """{"date":"Tue Jul 11 08:38:19 +0000 2017"}"""

    @Test
    fun deserialize() {
        val json = Json.decodeFromString<TestData>(strSource)
        assertEquals(source, json.date)
    }

    @Test
    fun serialize() {
        assertEquals(strSource, Json.encodeToString(TestData(source)))
    }
}

@Serializable
private class TestData(
    @Serializable(LocalDateTimeRFC822Serializer::class)
    val date: LocalDateTime
)
