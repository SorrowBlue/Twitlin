package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.v2.client.Error
import kotlin.jvm.JvmInline
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
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

    @OptIn(ExperimentalSerializationApi::class)
    @Test
    fun errorTest() {
        val json = """
            {"title":"Forbidden","type":"about:blank","detail":"Forbidden"}
        """.trimIndent()
        val error = Json.decodeFromString<Error>(json)
        print(error)
    }

    @Test
    fun testInline() {
        val json = """
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
