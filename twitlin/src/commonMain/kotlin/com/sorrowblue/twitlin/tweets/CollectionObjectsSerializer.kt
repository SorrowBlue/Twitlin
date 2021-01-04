/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.objects.User
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

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
public object CollectionObjectsSerializer : KSerializer<CollectionObjects> {
    override val descriptor: SerialDescriptor =
        buildSerialDescriptor("CollectionObjects", PolymorphicKind.SEALED) {
            element("Default", buildClassSerialDescriptor("Default") {
                element<Map<String, User>>("users")
                element<Map<String, CollectionTimeline>>("timelines")
            })
            element("HasTweets", buildClassSerialDescriptor("HasTweets") {
                element<Map<String, User>>("users")
                element<Map<String, CollectionTweet>>("tweets")
                element<Map<String, CollectionTimeline>>("timelines")
            })
        }

    override fun deserialize(decoder: Decoder): CollectionObjects {
//         Decoder -> JsonDecoder
        require(decoder is JsonDecoder) // this class can be decoded only by Json
//         JsonDecoder -> JsonElement
        val element = kotlin.runCatching {
            decoder.decodeJsonElement()
        }.getOrElse { JsonObject(emptyMap()) }
//         JsonElement -> value
        Napier.d("JSON: $element", tag = "DEBUG")
        return if (element is JsonObject && "tweets" in element)
            decoder.json.decodeFromJsonElement<CollectionObjects.HasTweets>(element)
        else
            decoder.json.decodeFromJsonElement<CollectionObjects.Default>(element)
    }

    override fun serialize(encoder: Encoder, value: CollectionObjects) {
//        Encoder -> JsonEncoder
        require(encoder is JsonEncoder) // This class can be encoded only by Json
        encoder.encodeJsonElement(encoder.json.encodeToJsonElement(value))
    }
}
