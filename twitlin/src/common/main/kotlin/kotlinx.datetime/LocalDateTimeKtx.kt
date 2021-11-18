package kotlinx.datetime

import com.sorrowblue.twitlin.Twitlin

public fun Int.epochToLocalDateTime(): LocalDateTime =
    Instant.fromEpochSeconds(toLong()).toLocalDateTime(Twitlin.defaultTimeZone)

public fun Long.epochToLocalDateTime(): LocalDateTime =
    Instant.fromEpochSeconds(this).toLocalDateTime(Twitlin.defaultTimeZone)

public fun LocalDateTime.toEpochSeconds(): Long {
    return toInstant(Twitlin.defaultTimeZone).toEpochMilliseconds().toString().dropLast(3).toLong()
}
