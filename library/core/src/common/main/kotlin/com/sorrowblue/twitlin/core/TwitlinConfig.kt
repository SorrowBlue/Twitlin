package com.sorrowblue.twitlin.core

import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.SIMPLE
import kotlinx.datetime.TimeZone

public object TwitlinConfig {

    public var defaultTimeZone: TimeZone = TimeZone.UTC

    public var clientLogger: Logger = Logger.SIMPLE

    public var clientLogLevel: LogLevel = LogLevel.ALL
}
