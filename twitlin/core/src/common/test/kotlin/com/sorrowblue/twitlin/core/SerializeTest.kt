package com.sorrowblue.twitlin.core

import kotlin.jvm.JvmInline
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SerializeTest {

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun emptyTest() {
        val json = "{}"
        val value = Json.decodeFromString<Unit>(json)
        println("value: $value")
        assertEquals(Unit, value)
        val string = Json.encodeToString(Unit)
        println("string: $string")
        assertEquals(json, string)
    }

    @Test
    fun testInline() {
        val json =
            """
                {"id":"a68wd416aw8d4a6wda27e8r4g"}
            """.trimIndent()
        val error = Json.decodeFromString<TestModel>(json)
        print(error)
    }

    @Test
    fun testInlinea() {
        val json = TestModel(TestInline("a68wd416aw8d4a6wda27e8r4g"))
        val error = Json.encodeToString(TestModel.serializer(), json)
        print(error)
    }
}

@JvmInline
@Serializable
value class TestInline(val value: String)

@Serializable
data class TestModel(val id: TestInline)
