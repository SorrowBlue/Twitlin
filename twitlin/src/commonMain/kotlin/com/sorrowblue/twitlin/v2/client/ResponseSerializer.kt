/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.github.aakira.napier.Napier
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
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
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
internal class ResponseSerializer<T : Any>(private val dataSerializer: KSerializer<T>) :
    KSerializer<Response<T>> {

    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("Response", PolymorphicKind.SEALED) {
            element("Success", buildClassSerialDescriptor("Success") {
                element<String>("data")
            })
            element("Error", dataSerializer.descriptor)
        }

    override fun deserialize(decoder: Decoder): Response<T> {
        require(decoder is JsonDecoder)
        val element = runCatching(decoder::decodeJsonElement).getOrElse { JsonObject(emptyMap()) }
        Napier.d("JsonElement: $element", tag = "ResponseSerializer")
        return kotlin.runCatching {
/*            if (element.jsonObject.isEmpty()) {
                Napier.d("empty", tag = "ResponseSerializer")
                Response.Success(Unit) as Response<T>
            } else*/ if ("data" in element.jsonObject) {
            Napier.d("success", tag = "ResponseSerializer")
            Response.Success(
                decoder.json.decodeFromJsonElement(
                    dataSerializer,
                    element
                )
            )
        } else {
            Napier.d("errors", tag = "ResponseSerializer")
            val errors =
                decoder.json.decodeFromJsonElement(
                    ListSerializer(Error.serializer()),
                    element.jsonObject["errors"]?.jsonArray!!
                )
            val jsonObject = element.jsonObject

            fun JsonObject.getString(key: String) = get(key)?.jsonPrimitive?.content
            Response.Error(
                errors,
                jsonObject.getString("title"),
                jsonObject.getString("detail"),
                jsonObject.getString("type")
            )
        }
        }.getOrElse {
            Napier.d("catch error: ${it.message}", tag = "ResponseSerializer")
            Response.Error(listOf())
        }
    }

    override fun serialize(encoder: Encoder, value: Response<T>) {
        require(encoder is JsonEncoder)
        val element = encoder.json.encodeToJsonElement(value)
        encoder.encodeJsonElement(element)
    }
}
