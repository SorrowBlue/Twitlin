/*
 * (c) 2020-2021 SorrowBlue.
 */

package kotlinx.datetime

import com.sorrowblue.twitlin.Twitlin
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * [LocalDateTime]用シリアライザ
 */
internal object LocalDateTimeStrEpochSerializer : KSerializer<LocalDateTime> {
    override val descriptor =
        PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) =
        Instant.fromEpochMilliseconds(decoder.decodeString().padEnd(13, '0').toLong())
            .toLocalDateTime(Twitlin.defaultTimeZone)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(Twitlin.defaultTimeZone).toEpochMilliseconds().toString()
        encoder.encodeString(s)
    }
}
