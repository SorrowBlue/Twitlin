/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.client.Response
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

@Serializable
internal class RateLimitStatusResponse(
    private val rate_limit_context: RateLimitStatus.RateLimitContext,
    private val resources: JsonObject? = null
) {

    companion object {
        fun onSuccess(response: RateLimitStatusResponse, httpResponse: HttpResponse) =
            Response.Success(
                RateLimitStatus(
                    response.rate_limit_context,
                    response.resources?.entries?.map(::parseResource).orEmpty()
                ), httpResponse.status.value
            )
    }
}

private fun parseResource(it: Map.Entry<String, JsonElement>): RateLimitStatus.Resource {
    val family = ResourceFamily.valueOf(it.key.toUpperCase())
    val info = it.value.jsonObject.entries.map(::parseInfo)
    return RateLimitStatus.Resource(family, info)
}

private fun parseInfo(entry: Map.Entry<String, JsonElement>): RateLimitStatus.Resource.Info {
    val limit = entry.value.jsonObject.getValue("limit").jsonPrimitive.int
    val remaining = entry.value.jsonObject.getValue("remaining").jsonPrimitive.int
    val reset = entry.value.jsonObject.getValue("reset").jsonPrimitive.long
        .let(Instant.Companion::fromEpochMilliseconds)
        .toLocalDateTime(TimeZone.currentSystemDefault())
    return RateLimitStatus.Resource.Info(entry.key, limit, remaining, reset)
}
