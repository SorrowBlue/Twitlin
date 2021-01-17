/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.client

import com.github.aakira.napier.Napier
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal class ResponseSerializer<T : Any>(private val dataSerializer: KSerializer<T>) :
    KSerializer<Response<T>> {
    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("Response", PolymorphicKind.SEALED) {
            element("Success", buildClassSerialDescriptor("Success") {
                element<String>("message")
            })
            element("Error", dataSerializer.descriptor)
        }

    override fun deserialize(decoder: Decoder): Response<T> {
//         Decoder -> JsonDecoder
        require(decoder is JsonDecoder) // this class can be decoded only by Json
//         JsonDecoder -> JsonElement
        val element = kotlin.runCatching {
            decoder.decodeJsonElement()
        }.getOrElse { JsonObject(emptyMap()) }
//         JsonElement -> value
        Napier.d("JSON: $element", tag = "DEBUG")
        return if (element is JsonObject && "errors" in element)
            Response.Error(decoder.json.decodeFromString<List<Error>>(element["errors"]!!.toString()))
        else
            Response.Success(decoder.json.decodeFromJsonElement(dataSerializer, element))
    }

    override fun serialize(encoder: Encoder, value: Response<T>) {
//        Encoder -> JsonEncoder
        require(encoder is JsonEncoder) // This class can be encoded only by Json
//        value -> JsonElement
        val element = when (value) {
            is Response.Success -> encoder.json.encodeToJsonElement(dataSerializer, value.data)
            is Response.Error -> buildJsonObject {
                put("errors", encoder.json.encodeToJsonElement(value.errors))
            }
        }
//        JsonElement -> JsonEncoder
        encoder.encodeJsonElement(element)
    }
}
