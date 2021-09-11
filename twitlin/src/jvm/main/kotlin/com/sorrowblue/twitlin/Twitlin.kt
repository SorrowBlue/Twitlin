/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import mu.KLogger
import org.slf4j.simple.SimpleLogger
import org.slf4j.spi.LocationAwareLogger

internal actual fun logLevel(logger: KLogger): KLogger {
    SimpleLogger::class.java.getDeclaredField("currentLogLevel").apply {
        isAccessible = true
        set(logger.underlyingLogger, LocationAwareLogger.TRACE_INT)
    }
    return logger
}
