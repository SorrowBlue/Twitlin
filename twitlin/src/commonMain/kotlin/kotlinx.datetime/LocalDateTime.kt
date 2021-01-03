/*
 * (c) 2021 SorrowBlue.
 */

package kotlinx.datetime

private val month =
    listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

internal fun Instant.Companion.parseRFC822(rfcString: String): Instant {
    val splits = rfcString.split(' ')
    val year = splits[5]
    val month = month.indexOf(splits[1]).plus(1).toString().padStart(2, '0')
    val day = splits[2]
    val time = splits[3].split(":")
    val hour = time[0]
    val minute = time[1]
    val second = time[2]
    return parse("$year-$month-${day}T$hour:$minute:${second}.000Z")
}

internal fun String.toInstantForRFC822() = Instant.parseRFC822(this)

internal fun Instant.formatRFC822(): String {
    val localDateTime = toLocalDateTime(TimeZone.UTC)
    val w = localDateTime.dayOfWeek.name.run {
        substring(0..0) + substring(1..2).toLowerCase()
    }
    val month = localDateTime.month.name.run {
        substring(0..0) + substring(1..2).toLowerCase()
    }
    val d = localDateTime.dayOfMonth
    val h = localDateTime.hour.toString().padStart(2, '0')
    val m = localDateTime.minute.toString().padStart(2, '0')
    val s = localDateTime.second.toString().padStart(2, '0')
    val y = localDateTime.year.toString().padStart(4, '0')
    return "$w $month $d $h:$m:$s +0000 $y"
}
