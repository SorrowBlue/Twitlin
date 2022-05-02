package kotlinx.datetime

import com.sorrowblue.twitlin.core.TwitlinConfig

/**
 * Parses to RFC822 format date string LocalDateTime
 *
 * The only supported format is `Wed Oct 10 20:19:24 +0000 2018`.
 *
 * @return
 */
public fun String.rfc822ToLocalDateTime(timeZone: TimeZone = TwitlinConfig.defaultTimeZone): LocalDateTime =
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
