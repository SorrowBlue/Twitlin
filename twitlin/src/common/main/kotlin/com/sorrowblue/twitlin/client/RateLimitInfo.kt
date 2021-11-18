package com.sorrowblue.twitlin.client

import com.sorrowblue.twitlin.Twitlin
import io.ktor.http.Headers
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime

/**
 * Rate limit info
 *
 * @property limit
 * @property remaing
 * @property reset
 * @constructor Create empty Rate limit info
 */
public data class RateLimitInfo(
    val limit: Int,
    val remaing: Int,
    val reset: LocalDateTime?
) {

    public companion object {

        /**
         * From headers
         *
         * @param headers
         * @return
         */
        public fun fromHeaders(headers: Headers): RateLimitInfo = RateLimitInfo(
            headers["x-rate-limit-limit"]?.toIntOrNull() ?: -1,
            headers["x-rate-limit-remaining"]?.toIntOrNull() ?: -1,
            headers["x-rate-limit-reset"]?.toLongOrNull()?.let {
                Instant.fromEpochSeconds(it).toLocalDateTime(Twitlin.defaultTimeZone)
            }
        )
    }
}
