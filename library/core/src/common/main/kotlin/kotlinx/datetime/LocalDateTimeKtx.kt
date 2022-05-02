package kotlinx.datetime

import com.sorrowblue.twitlin.core.TwitlinConfig

public fun Int.epochToLocalDateTime(): LocalDateTime =
    Instant.fromEpochSeconds(toLong()).toLocalDateTime(TwitlinConfig.defaultTimeZone)

public fun Long.epochToLocalDateTime(): LocalDateTime =
    Instant.fromEpochSeconds(this).toLocalDateTime(TwitlinConfig.defaultTimeZone)

public fun LocalDateTime.toEpochSeconds(): Long {
    return toInstant(TwitlinConfig.defaultTimeZone).toEpochMilliseconds().toString().dropLast(3).toLong()
}
