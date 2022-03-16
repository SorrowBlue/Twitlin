package com.sorrowblue.twitlin.api.utilities.application.impl

import com.sorrowblue.twitlin.api.utilities.application.ApplicationApi
import com.sorrowblue.twitlin.api.utilities.application.RateLimitStatus
import com.sorrowblue.twitlin.api.utilities.application.ResourceFamily
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.util.API_APPLICATION
import io.ktor.client.request.parameter

internal class ApplicationApiImpl(private val client: TwitterClient) : ApplicationApi {

    override suspend fun rateLimitStatus(resourceFamily: List<ResourceFamily>?): Response<RateLimitStatus> {
        return client.get(
            "$API_APPLICATION/rate_limit_status.json",
            Response.serializer(RateLimitStatusResponse.serializer())
        ) {
            parameter("resources", resourceFamily?.joinToString(",", transform = ResourceFamily::value))
        }.convertData(RateLimitStatusResponse::toRateLimitState)
    }
}
