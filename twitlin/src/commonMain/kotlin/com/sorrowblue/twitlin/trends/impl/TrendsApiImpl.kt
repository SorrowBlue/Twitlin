/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.trends.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.trends.TrendPlace
import com.sorrowblue.twitlin.trends.TrendPlaces
import com.sorrowblue.twitlin.trends.TrendsApi

private const val TRENDS = "${Urls.V1}/trends"

internal class TrendsApiImpl(private val client: TwitlinClient) : TrendsApi {

    override suspend fun place(id: Int, exclude: Boolean?): Response<List<TrendPlaces>> =
        client.get("$TRENDS/place.json", "id" to id, "exclude" to exclude)

    override suspend fun available(): Response<List<TrendPlaces>> =
        client.get("$TRENDS/available.json")

    override suspend fun closest(lat: Double, long: Double): Response<List<TrendPlace>> =
        client.get("$TRENDS/available.json", "lat" to lat, "long" to long)

}
