package com.sorrowblue.twitlin.trends.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.objects.TrendLocation
import com.sorrowblue.twitlin.trends.TrendPlace
import com.sorrowblue.twitlin.trends.TrendPlaces
import com.sorrowblue.twitlin.trends.TrendsApi
import com.sorrowblue.twitlin.utils.API_TRENDS
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
