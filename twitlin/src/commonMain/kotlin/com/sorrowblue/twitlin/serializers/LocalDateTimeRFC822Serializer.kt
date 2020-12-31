/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.serializers

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.formatRFC822
import kotlinx.datetime.toInstant
import kotlinx.datetime.toInstantForRFC822
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [LocalDateTime]用シリアライザ
 */
internal object LocalDateTimeRFC822Serializer : KSerializer<LocalDateTime> {
    override val descriptor =
        PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) =
        decoder.decodeString().toInstantForRFC822().toLocalDateTime(TimeZone.currentSystemDefault())

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(TimeZone.UTC).formatRFC822()
        encoder.encodeString(s)
    }
}
