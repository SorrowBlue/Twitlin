/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.geo.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.geo.GeoApi
import com.sorrowblue.twitlin.geo.GeoSearch
import com.sorrowblue.twitlin.geo.ReverseGeocode
import com.sorrowblue.twitlin.objects.Place
import com.sorrowblue.twitlin.objects.PlaceType

private const val GEO = "${Urls.V1}/geo"

internal class GeoApiImpl(private val client: UserClient) : GeoApi {

    override suspend fun id(placeId: String): Response<Place> {
        return client.get("$GEO/id/$placeId.json", Response.serializer(Place.serializer()))
    }

    override suspend fun reverseGeocode(
        lat: Double,
        long: Double,
        accuracy: String?,
        granularity: PlaceType?,
        maxResults: Int?
    ): Response<ReverseGeocode> {
        return client.get(
            "$GEO/reverse_geocode.json",
            Response.serializer(ReverseGeocode.serializer()),
            "lat" to lat,
            "long" to long,
            "accuracy" to accuracy,
            "granularity" to granularity,
            "max_results" to maxResults
        )
    }

    override suspend fun search(
        lat: Double?,
        long: Double?,
        query: String?,
        ip: String?,
        granularity: PlaceType?,
        maxResults: Int?
    ): Response<GeoSearch> {
        return client.get(
            "$GEO/search.json",
            Response.serializer(GeoSearch.serializer()),
            "lat" to lat,
            "long" to long,
            "query" to query,
            "ip" to ip,
            "granularity" to granularity,
            "max_results" to maxResults
        )
    }
}
