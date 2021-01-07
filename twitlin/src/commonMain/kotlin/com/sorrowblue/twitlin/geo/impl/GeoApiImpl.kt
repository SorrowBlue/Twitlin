/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.geo.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.geo.GeoApi
import com.sorrowblue.twitlin.geo.GeoSearch
import com.sorrowblue.twitlin.geo.ReverseGeocode
import com.sorrowblue.twitlin.objects.Place
import com.sorrowblue.twitlin.objects.PlaceType

private const val GEO = "${Urls.V1}/geo"

internal class GeoApiImpl(private val client: UserClient) : GeoApi {

    override suspend fun id(placeId: String): Response<Place> = client.get("$GEO/id/$placeId.json")

    override suspend fun reverseGeocode(
        lat: Double,
        long: Double,
        accuracy: String?,
        granularity: PlaceType?,
        maxResults: Int?
    ): Response<ReverseGeocode> =
        client.get(
            "$GEO/reverse_geocode.json",
            "lat" to lat,
            "long" to long,
            "accuracy" to accuracy,
            "granularity" to granularity,
            "max_results" to maxResults
        )

    override suspend fun search(
        lat: Double?,
        long: Double?,
        query: String?,
        ip: String?,
        granularity: PlaceType?,
        maxResults: Int?
    ): Response<GeoSearch> =
        client.get(
            "$GEO/search.json",
            "lat" to lat,
            "long" to long,
            "query" to query,
            "ip" to ip,
            "granularity" to granularity,
            "max_results" to maxResults
        )

}