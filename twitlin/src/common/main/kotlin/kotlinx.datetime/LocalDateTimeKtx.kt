/*
 * (c) 2020-2021 SorrowBlue.
 */

package kotlinx.datetime

private val month =
    listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

internal fun String.toInstantForRFC822(): Instant {
    val splits = split(' ')
    val year = splits[5]
    val month = month.indexOf(splits[1]).plus(1).toString().padStart(2, '0')
    val day = splits[2]
    val time = splits[3].split(":")
    val hour = time[0]
    val minute = time[1]
    val second = time[2]
    return Instant.parse("$year-$month-${day}T$hour:$minute:$second.000Z")
}

internal fun Instant.formatRFC822(): String {
    val localDateTime = toLocalDateTime(TimeZone.UTC)
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
    return "$w $month $d $h:$m:$s +0000 $y"
}

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

internal fun LocalDateTime.encodeToISOString() =
    toInstant(TimeZone.UTC).formatISO8601()
