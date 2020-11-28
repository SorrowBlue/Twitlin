package com.sorrowblue.twitlin.v2.serializers

import com.sorrowblue.twitlin.v2.serializer.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LocalDateTimeSerializerTest {

    private val source = LocalDateTime(2010, 6, 1, 22, 19, 44, 475)
    private val strSource = """{"date":"2010-06-01T22:19:44.475Z"}"""


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
    @Serializable(LocalDateTimeSerializer::class)
    val date: LocalDateTime
)
