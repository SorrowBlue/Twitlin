package kotlinx.datetime

public fun String.epochToLocalDateTime(): LocalDateTime = toLong().epochToLocalDateTime()
