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
internal object LocalDateTimeIntEpochSerializer : KSerializer<LocalDateTime> {
    override val descriptor =
        PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) =
        Instant.fromEpochMilliseconds(decoder.decodeInt().toString().padEnd(13, '0').toLong())
            .toLocalDateTime(Twitlin.defaultTimeZone)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(Twitlin.defaultTimeZone).toEpochMilliseconds().toString()
            .substring(0..9)
        encoder.encodeInt(s.toInt())
    }
}

public fun Int.epochToLocalDateTime(): LocalDateTime = Instant.fromEpochMilliseconds(toString().padEnd(13, '0').toLong())
    .toLocalDateTime(Twitlin.defaultTimeZone)

public fun LocalDateTime.toIntEpoch(): Int {
    val s = toInstant(Twitlin.defaultTimeZone).toEpochMilliseconds().toString()
        .substring(0..9)
    return s.toInt()
}
