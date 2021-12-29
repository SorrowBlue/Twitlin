package com.sorrowblue.twitlin.client

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal class ResponseSerializer<T : Any>(private val dataSerializer: KSerializer<T>) : KSerializer<Response<T>> {

    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("Response", PolymorphicKind.SEALED) {
            element("Success", dataSerializer.descriptor)
            element("Error", dataSerializer.descriptor)
        }

    override fun deserialize(decoder: Decoder): Response<T> {
//        Decoder -> JsonDecoder
        require(decoder is JsonDecoder) // this class can be decoded only by Json
//        JsonDecoder -> JsonElement
        val element = runCatching(decoder::decodeJsonElement).getOrElse { JsonObject(emptyMap()) }
//        JsonElement -> value
        return kotlin.runCatching {
            if (element is JsonObject && element.size == 1 && "errors" in element) {
                decoder.json.decodeFromJsonElement(Response.Error.serializer(dataSerializer), element)
            } else {
                Response.Success(decoder.json.decodeFromJsonElement(dataSerializer, element))
            }
        }.getOrElse {
            Response.error(Error(it.message.orEmpty(), 0))
        }
    }

    override fun serialize(encoder: Encoder, value: Response<T>) {
//        Encoder -> JsonEncoder
        require(encoder is JsonEncoder) // This class can be encoded only by Json
//        value -> JsonElement
        val element = encoder.json.encodeToJsonElement(value)
//        JsonElement -> JsonEncoder
        encoder.encodeJsonElement(element)
    }
}
