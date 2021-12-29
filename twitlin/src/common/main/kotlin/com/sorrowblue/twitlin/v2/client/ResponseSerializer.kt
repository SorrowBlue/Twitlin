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
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement

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
            return Response.Error(listOf(UndefinedProblem()))
        }
        return kotlin.runCatching {
            if ("data" in element || "meta" in element) {
                println("decode success")
                Response.Success(decoder.json.decodeFromJsonElement(dataSerializer, element))
            } else {
                println("decode error")
                element.asError(decoder)
            }
        }.getOrElse {
            Response.Error(listOf(UndefinedProblem()))
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
        if ("type" in this) {
            return Response.Error(listOf(decoder.json.decodeFromJsonElement(Problem.serializer(), this)))
        }
        return Response.Error(
            decoder.json.decodeFromJsonElement(ListSerializer(Problem.serializer()), getValue("errors"))
        )
    }
}
