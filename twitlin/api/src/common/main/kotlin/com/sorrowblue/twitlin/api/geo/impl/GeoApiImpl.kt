package com.sorrowblue.twitlin.api.geo.impl

import com.sorrowblue.twitlin.api.geo.GeoApi
import com.sorrowblue.twitlin.api.geo.GeoSearch
import com.sorrowblue.twitlin.api.geo.ReverseGeocode
import com.sorrowblue.twitlin.api.objects.Place
import com.sorrowblue.twitlin.api.objects.PlaceType
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.PlaceId
import com.sorrowblue.twitlin.core.util.API_GEO
import io.ktor.client.request.parameter

internal class GeoApiImpl(private val client: TwitterClient) : GeoApi {

    override suspend fun id(placeId: PlaceId): Response<Place> {
        return client.get("$API_GEO/id/${placeId.id}.json", Response.serializer(Place.serializer()))
    }

    override suspend fun reverseGeocode(
        lat: Double,
        long: Double,
        accuracy: String?,
        granularity: PlaceType?,
        maxResults: Int?
    ): Response<ReverseGeocode> {
        return client.get("$API_GEO/reverse_geocode.json", Response.serializer(ReverseGeocode.serializer())) {
            parameter("lat", lat)
            parameter("long", long)
            parameter("accuracy", accuracy)
            parameter("granularity", granularity)
            parameter("max_results", maxResults)
        }
    }

    override suspend fun search(
        lat: Double?,
        long: Double?,
        query: String?,
        ip: String?,
        granularity: PlaceType?,
        maxResults: Int?
    ): Response<GeoSearch> {
        return client.get("$API_GEO/search.json", Response.serializer(GeoSearch.serializer())) {
            parameter("lat", lat)
            parameter("long", long)
            parameter("query", query)
            parameter("ip", ip)
            parameter("granularity", granularity)
            parameter("max_results", maxResults)
        }
    }
}
