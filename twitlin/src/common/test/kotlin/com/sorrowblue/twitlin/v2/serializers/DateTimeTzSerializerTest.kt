/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.serializers

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeISOSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
internal class LocalDateTimeRFC822SerializerTest {

    private val source = LocalDateTime(2010, 6, 1, 22, 19, 44, 475)
    private val strSource = """{"date":"2010-06-01T22:19:44.000000475Z"}"""

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
class TestData(
    @Serializable(LocalDateTimeISOSerializer::class)
    val date: LocalDateTime
)
