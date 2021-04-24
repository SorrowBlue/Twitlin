/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities.response

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.utilities.RateLimitStatus
import com.sorrowblue.twitlin.utilities.ResourceFamily
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeIntEpochSerializer
import kotlinx.datetime.toIntEpoch
import kotlinx.serialization.Serializable

@Serializable
internal class RateLimitStatusResponse(
    private val rate_limit_context: RateLimitStatus.RateLimitContext,
    private val resources: Map<ResourceFamily, Map<String, Info>> = emptyMap()
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

        fun toInfo(path: String) = RateLimitStatus.Resource.Info(path, limit, remaining, reset.toIntEpoch())
    }
}

private fun Map.Entry<ResourceFamily, Map<String, RateLimitStatusResponse.Info>>.toResource() =
    RateLimitStatus.Resource(key, value.entries.map { it.value.toInfo(it.key) })
