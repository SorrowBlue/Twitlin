package com.sorrowblue.twitlin.core

import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.SIMPLE
import kotlinx.datetime.TimeZone

public object Twitlin {

    public var defaultTimeZone: TimeZone = TimeZone.UTC

    public var clientLogger: Logger = Logger.SIMPLE

    public var clientLogLevel: LogLevel = LogLevel.ALL
}
