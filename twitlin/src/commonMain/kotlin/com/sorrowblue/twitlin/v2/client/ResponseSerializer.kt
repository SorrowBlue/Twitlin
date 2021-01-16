/*
 * (c) 2021 SorrowBlue.
 */

/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.client

import com.github.aakira.napier.Napier
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
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
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

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
        require(decoder is JsonDecoder)
        val element = runCatching(decoder::decodeJsonElement).getOrElse { JsonObject(emptyMap()) }
        Napier.d("v2.ResponseSerializer JSON: $element", tag = "DEBUG")
        return kotlin.runCatching {
            Response.Success(decoder.json.decodeFromJsonElement(dataSerializer, element))
        }.getOrNull() ?: kotlin.runCatching {
            Napier.d("decode error: Response.Success", tag = "DEBUG")
            if (element.jsonObject.isEmpty()) {
                Response.Success(Unit) as Response<T>
            } else {
                Napier.d("decode error: Response.Error", tag = "DEBUG")
                Response.Error(listOf(decoder.json.decodeFromJsonElement(element)))
            }
        }.getOrElse {
            Napier.d("decode error: Error", tag = "DEBUG")
            Response.Error(listOf())
        }
    }

    override fun serialize(encoder: Encoder, value: Response<T>) {
        require(encoder is JsonEncoder)
        val element = encoder.json.encodeToJsonElement(value)
        encoder.encodeJsonElement(element)
    }
}
