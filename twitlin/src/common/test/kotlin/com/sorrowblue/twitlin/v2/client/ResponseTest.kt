package com.sorrowblue.twitlin.v2.client

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

class ResponseTest {

    @Test
    fun testResponseSuccess() {
        val json = Json
        val a: TestResponse<Int> =
            json.decodeFromString(TestResponseSerializer(Int.serializer()), "{\"data\":0}")
        assertEquals(TestResponse(0), a)
    }
}

@Serializable(TestResponseSerializer::class)
data class TestResponse<T : Any>(val data: T)

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
@Serializer(forClass = TestResponse::class)
class TestResponseSerializer<T : Any>(private val dataSerializer: KSerializer<T>) :
    KSerializer<TestResponse<T>> {

    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("TestResponse") {
            element<Int>("data")
        }

    override fun deserialize(decoder: Decoder): TestResponse<T> {
        require(decoder is JsonDecoder)
        val element = kotlin.runCatching {
            decoder.decodeJsonElement()
        }.getOrElse { JsonObject(emptyMap()) }
        return TestResponse(
            decoder.json.decodeFromJsonElement(
                dataSerializer,
                element.jsonObject["data"]!!
            )
        )
    }

    override fun serialize(encoder: Encoder, value: TestResponse<T>) {
        require(encoder is JsonEncoder)
        val element = encoder.json.encodeToJsonElement(value)
        encoder.encodeJsonElement(element)
    }
}
