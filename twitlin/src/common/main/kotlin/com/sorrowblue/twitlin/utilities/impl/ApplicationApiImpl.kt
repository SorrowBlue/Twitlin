/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.utilities.ApplicationApi
import com.sorrowblue.twitlin.utilities.RateLimitStatus
import com.sorrowblue.twitlin.utilities.ResourceFamily
import com.sorrowblue.twitlin.utilities.response.RateLimitStatusResponse

private const val APPLICATION = "${Urls.V1}/application"

internal class ApplicationApiImpl(private val client: UserClient) : ApplicationApi {

    override suspend fun rateLimitStatus(resourceFamily: List<ResourceFamily>?): Response<RateLimitStatus> {
        return client.get(
            "$APPLICATION/rate_limit_status.json",
            Response.serializer(RateLimitStatusResponse.serializer()),
            "resources" to resourceFamily?.joinToString(",") { it.name.lowercase() }
        ).convertData(RateLimitStatusResponse::toRateLimitState)
    }
}
