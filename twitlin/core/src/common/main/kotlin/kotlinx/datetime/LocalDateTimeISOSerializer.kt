package kotlinx.datetime

import com.sorrowblue.twitlin.core.Twitlin

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

public fun LocalDateTime.encodeToISOString(): String = toInstant(TimeZone.UTC).formatISO8601()
