package com.sorrowblue.twitlin.api.application.impl

import com.sorrowblue.twitlin.api.API_APPLICATION
import com.sorrowblue.twitlin.api.application.ApplicationApi
import com.sorrowblue.twitlin.api.application.RateLimitStatus
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import io.ktor.client.request.parameter

internal class ApplicationApiImpl(private val client: TwitterClient) : ApplicationApi {

    override suspend fun rateLimitStatus(resourceFamily: List<String>?): Response<RateLimitStatus> {
        return client.get(
            "$API_APPLICATION/rate_limit_status.json",
            Response.serializer(RateLimitStatusResponse.serializer())
        ) {
            parameter("resources", resourceFamily?.joinToString(","))
        }.convertData(RateLimitStatusResponse::toRateLimitState)
    }
}
