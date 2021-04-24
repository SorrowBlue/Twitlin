/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import mu.KLogger
import mu.KotlinLoggingConfiguration
import mu.KotlinLoggingLevel

internal actual fun logLevel(logger: KLogger): KLogger {
    KotlinLoggingConfiguration.LOG_LEVEL = KotlinLoggingLevel.TRACE
    return logger
}
