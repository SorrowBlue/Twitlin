/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.serializer

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [LocalDateTime]用シリアライザ
 */
internal object LocalDateTimeSerializer : KSerializer<LocalDateTime> {
    override val descriptor =
        PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) =
        decoder.decodeString().toInstant().toLocalDateTime(TimeZone.currentSystemDefault())

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(TimeZone.UTC).toString()
        encoder.encodeString(s)
    }
}
