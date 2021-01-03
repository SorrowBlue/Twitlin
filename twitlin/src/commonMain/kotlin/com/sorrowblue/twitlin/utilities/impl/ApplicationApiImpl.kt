/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.utilities.ApplicationApi
import com.sorrowblue.twitlin.utilities.RateLimitStatus
import com.sorrowblue.twitlin.utilities.RateLimitStatusResponse
import com.sorrowblue.twitlin.utilities.ResourceFamily

private const val APPLICATION = "${Urls.V1}/application"

internal class ApplicationApiImpl(private val client: UserClient) : ApplicationApi {

    override suspend fun rateLimitStatus(resourceFamily: List<ResourceFamily>): Response<RateLimitStatus> =
        client.get<RateLimitStatusResponse>(
            "$APPLICATION/rate_limit_status.json",
            "resources" to resourceFamily.joinToString(",") { it.name.toLowerCase() }
        ).convertData(RateLimitStatusResponse::toRateLimitState)
}
