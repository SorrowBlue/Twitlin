/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.trends.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.trends.TrendPlace
import com.sorrowblue.twitlin.trends.TrendPlaces
import com.sorrowblue.twitlin.trends.TrendsApi
import kotlinx.serialization.builtins.ListSerializer

private const val TRENDS = "${Urls.V1}/trends"

internal class TrendsApiImpl(private val client: UserClient) : TrendsApi {

    override suspend fun place(id: Int, exclude: Boolean?): Response<List<TrendPlaces>> {
        return client.get(
            "$TRENDS/place.json",
            Response.serializer(ListSerializer(TrendPlaces.serializer())),
            "id" to id,
            "exclude" to exclude
        )
    }

    override suspend fun available(): Response<List<TrendPlaces>> {
        return client.get(
            "$TRENDS/available.json",
            Response.serializer(ListSerializer(TrendPlaces.serializer()))
        )
    }

    override suspend fun closest(lat: Double, long: Double): Response<List<TrendPlace>> {
        return client.get(
            "$TRENDS/available.json",
            Response.serializer(ListSerializer(TrendPlace.serializer())),
            "lat" to lat,
            "long" to long
        )
    }

}
