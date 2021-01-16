/*
 * (c) 2021 SorrowBlue.
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
internal object LocalDateTimeISOSerializer : KSerializer<LocalDateTime> {
    override val descriptor =
        PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) =
        decoder.decodeString().toInstant().toLocalDateTime(Twitlin.defaultTimeZone)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(Twitlin.defaultTimeZone).toString()
        encoder.encodeString(s)
    }
}
