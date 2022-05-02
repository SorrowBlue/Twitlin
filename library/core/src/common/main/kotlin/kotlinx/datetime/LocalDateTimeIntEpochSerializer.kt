package kotlinx.datetime

import com.sorrowblue.twitlin.core.TwitlinConfig
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
            .toLocalDateTime(TwitlinConfig.defaultTimeZone)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(TwitlinConfig.defaultTimeZone).toEpochMilliseconds().toString()
            .substring(0..9)
        encoder.encodeInt(s.toInt())
    }
}

public object LocalDateTimeStringEpochSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return Instant.fromEpochMilliseconds(decoder.decodeString().padEnd(13, '0').toLong())
            .toLocalDateTime(TwitlinConfig.defaultTimeZone)
    }

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(TwitlinConfig.defaultTimeZone).toEpochMilliseconds().toString()
            .substring(0..9)
        encoder.encodeInt(s.toInt())
    }
}
