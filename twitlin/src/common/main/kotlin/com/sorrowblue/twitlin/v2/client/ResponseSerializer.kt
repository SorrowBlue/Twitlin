/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal class ResponseSerializer<T : Any>(private val dataSerializer: KSerializer<T>) :
    KSerializer<Response<T>> {

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
        if (element !is JsonObject) {
            return Response.Error(listOf())
        }
        return kotlin.runCatching {
            if (("data" in element || "meta" in element)) {
                Response.Success(decoder.json.decodeFromJsonElement(dataSerializer, element))
            } else {
                element.asError(decoder)
            }
        }.getOrElse {
            Response.Error(listOf(), "Decode failed", it.message)
        }
    }

    override fun serialize(encoder: Encoder, value: Response<T>) {
//        Encoder -> JsonEncoder
        require(encoder is JsonEncoder)
//        value -> JsonElement
        val element = encoder.json.encodeToJsonElement(value)
        encoder.encodeJsonElement(element)
    }

    private fun JsonObject.asError(decoder: JsonDecoder): Response.Error<T> {
        val errors = jsonObject["errors"]?.jsonArray?.let {
            decoder.json.decodeFromJsonElement(ListSerializer(Error.serializer()), it)
        }

        fun JsonObject.getString(key: String) = get(key)?.jsonPrimitive?.content
        return Response.Error(
            errors.orEmpty(),
            jsonObject.getString("title"),
            jsonObject.getString("detail"),
            type = jsonObject["type"]?.let {
                Json.decodeFromJsonElement(
                    Error.Type.serializer(),
                    it
                )
            }
        )
    }
}
