package kotlinx.datetime

import com.sorrowblue.twitlin.Twitlin
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serializer for date strings in ISO format
 */
internal object LocalDateTimeISOSerializer : KSerializer<LocalDateTime> {

    override val descriptor = PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = decoder.decodeString().isoToLocalDateTime()

    override fun serialize(encoder: Encoder, value: LocalDateTime) = encoder.encodeString(value.encodeToISOString())
}

/**
 * Iso to local date time
 *
 * @param timeZone
 * @return
 */
public fun String.isoToLocalDateTime(timeZone: TimeZone = Twitlin.defaultTimeZone): LocalDateTime =
    toInstant().toLocalDateTime(timeZone)

internal fun Instant.formatISO8601(): String {
    val localDateTime = toLocalDateTime(TimeZone.UTC)
    val month = localDateTime.month.number.toString().padStart(2, '0')
    val d = localDateTime.dayOfMonth
    val h = localDateTime.hour.toString().padStart(2, '0')
    val m = localDateTime.minute.toString().padStart(2, '0')
    val s = localDateTime.second.toString().padStart(2, '0')
    val y = localDateTime.year.toString().padStart(4, '0')
    return "$y-$month-${d}T$h:$m:${s}Z"
}

internal fun LocalDateTime.encodeToISOString() = toInstant(TimeZone.UTC).formatISO8601()
