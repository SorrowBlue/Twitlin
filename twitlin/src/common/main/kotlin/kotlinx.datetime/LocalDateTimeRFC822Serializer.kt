package kotlinx.datetime

import com.sorrowblue.twitlin.Twitlin
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Serializer for date strings in RFC822 format
 */
internal object LocalDateTimeRFC822Serializer : KSerializer<LocalDateTime> {
    override val descriptor = PrimitiveSerialDescriptor("kotlinx.datetime.toLocalDateTime", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) = decoder.decodeString().rfc822ToLocalDateTime()

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        val s = value.toInstant(Twitlin.defaultTimeZone).formatRFC822()
        encoder.encodeString(s)
    }
}

/**
 * Parses to RFC822 format date string LocalDateTime
 *
 * The only supported format is `Wed Oct 10 20:19:24 +0000 2018`.
 *
 * @return
 */
public fun String.rfc822ToLocalDateTime(timeZone: TimeZone = Twitlin.defaultTimeZone): LocalDateTime =
    Instant.parseRfc822(this).toLocalDateTime(timeZone)

private val month =
    listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

/**
 * Get an instance of Instant from a text string.
 *
 * The only supported format is `Wed Oct 10 20:19:24 +0000 2018`.
 *
 * @param rfc822 the text to parse
 * @return the parsed instant
 * @throws DateTimeFormatException if the text cannot be parsed
 */
internal fun Instant.Companion.parseRfc822(rfc822: String): Instant {
    val splits = rfc822.split(' ')
    val year = splits[5]
    val month = month.indexOf(splits[1]).plus(1).toString().padStart(2, '0')
    val day = splits[2]
    val timeZone = splits[4]
    val time = splits[3].split(":")
    val hour = time[0]
    val minute = time[1]
    val second = time[2]
    return parse("$year-$month-${day}T$hour:$minute:$second${TimeZone.of(timeZone).id}")
}

/**
 * Format r f c822
 *
 * @param timeZone
 * @return
 */
internal fun Instant.formatRFC822(timeZone: TimeZone = Twitlin.defaultTimeZone): String {
    val localDateTime = toLocalDateTime(timeZone)
    val w = localDateTime.dayOfWeek.name.run {
        substring(0..0) + substring(1..2).lowercase()
    }
    val month = localDateTime.month.name.run {
        substring(0..0) + substring(1..2).lowercase()
    }
    val d = localDateTime.dayOfMonth
    val h = localDateTime.hour.toString().padStart(2, '0')
    val m = localDateTime.minute.toString().padStart(2, '0')
    val s = localDateTime.second.toString().padStart(2, '0')
    val y = localDateTime.year.toString().padStart(4, '0')
    val z =
        if (listOf(TimeZone.UTC, TimeZone.of("UTC"), TimeZone.of("GMT")).contains(timeZone)) "+00:00" else timeZone.id
    return "$w $month $d $h:$m:$s ${z.take(3)}${z.drop(4)} $y"
}
