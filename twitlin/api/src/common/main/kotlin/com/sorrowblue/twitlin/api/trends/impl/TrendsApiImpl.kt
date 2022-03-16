package com.sorrowblue.twitlin.api.trends.impl

import com.sorrowblue.twitlin.api.objects.TrendLocation
import com.sorrowblue.twitlin.api.trends.TrendPlace
import com.sorrowblue.twitlin.api.trends.TrendPlaces
import com.sorrowblue.twitlin.api.trends.TrendsApi
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.util.API_TRENDS
import io.ktor.client.request.parameter
import kotlinx.serialization.builtins.ListSerializer

internal class TrendsApiImpl(private val client: TwitterClient) : TrendsApi {

    override suspend fun place(id: Int, exclude: Boolean?): Response<List<TrendPlaces>> {
        return client.get("$API_TRENDS/place.json", Response.serializer(ListSerializer(TrendPlaces.serializer()))) {
            parameter("id", id)
            parameter("exclude", exclude)
        }
    }

    override suspend fun available(): Response<List<TrendLocation>> {
        return client.get("$API_TRENDS/available.json", Response.serializer(ListSerializer(TrendLocation.serializer())))
    }

    override suspend fun closest(lat: Double, long: Double): Response<List<TrendPlace>> {
        return client.get("$API_TRENDS/available.json", Response.serializer(ListSerializer(TrendPlace.serializer()))) {
            parameter("lat", lat)
            parameter("long", long)
        }
    }
}
