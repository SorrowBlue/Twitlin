package com.sorrowblue.twitlin.api.application.impl

import com.sorrowblue.twitlin.api.application.RateLimitStatus
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeIntEpochSerializer
import kotlinx.datetime.toEpochSeconds
import kotlinx.serialization.Serializable

@Serializable
internal class RateLimitStatusResponse(
    private val rate_limit_context: RateLimitStatus.RateLimitContext,
    private val resources: Map<String, Map<String, Info>> = emptyMap()
) {

    fun toRateLimitState() =
        RateLimitStatus(rate_limit_context, resources.entries.map { it.toResource() })

    @Serializable
    data class Info(
        val limit: Int,
        val remaining: Int,
        @Serializable(LocalDateTimeIntEpochSerializer::class)
        val reset: LocalDateTime,
    ) : JvmSerializable {

        fun toInfo(path: String) = RateLimitStatus.Resource.Info(path, limit, remaining, reset.toEpochSeconds())
    }
}

private fun Map.Entry<String, Map<String, RateLimitStatusResponse.Info>>.toResource() =
    RateLimitStatus.Resource(key, value.entries.map { it.value.toInfo(it.key) })
