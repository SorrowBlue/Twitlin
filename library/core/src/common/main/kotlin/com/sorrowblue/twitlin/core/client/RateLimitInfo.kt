package com.sorrowblue.twitlin.core.client

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.annotation.KotlinIgnoredOnParcel
import io.ktor.http.Headers
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.epochToLocalDateTime
import kotlinx.datetime.toEpochSeconds
import kotlinx.serialization.Serializable

/**
 * Rate limit info
 *
 * @property limit
 * @property remaing
 * @property reset
 */
@AndroidParcelize
@Serializable
public data class RateLimitInfo internal constructor(
    val limit: Int,
    val remaing: Int,
    internal val _reset: String?
) : AndroidParcelable, JvmSerializable {

    public constructor(limit: Int, remaing: Int, reset: LocalDateTime?) : this(
        limit,
        remaing,
        reset?.toEpochSeconds()?.toString()
    )

    @KotlinIgnoredOnParcel
    public val reset: LocalDateTime?
        get() = _reset?.epochToLocalDateTime()

    public companion object {

        public fun fromHeaders(headers: Headers): RateLimitInfo = RateLimitInfo(
            headers["x-rate-limit-limit"]?.toIntOrNull() ?: -1,
            headers["x-rate-limit-remaining"]?.toIntOrNull() ?: -1,
            headers["x-rate-limit-reset"]
        )
    }
}
