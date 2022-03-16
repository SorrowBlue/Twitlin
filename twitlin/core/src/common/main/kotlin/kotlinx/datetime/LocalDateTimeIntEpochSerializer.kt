package kotlinx.datetime

import com.sorrowblue.twitlin.core.Twitlin
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object LocalDateTimeIntEpochSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime =
        Instant.fromEpochMilliseconds(decoder.decodeInt().toString().padEnd(13, '0').toLong())
            .toLocalDateTime(Twitlin.defaultTimeZone)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(Twitlin.defaultTimeZone).toEpochMilliseconds().toString()
            .substring(0..9)
        encoder.encodeInt(s.toInt())
    }
}
