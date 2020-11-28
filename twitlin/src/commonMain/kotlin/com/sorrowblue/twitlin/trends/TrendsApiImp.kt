package com.sorrowblue.twitlin.trends

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import kotlinx.serialization.Serializable

private const val ROOT = "${Urls._1_1}/trends"

internal class TrendsApiImp(private val client: Client) : TrendsApi {

    override suspend fun available(): Response<List<TrendsPlace>> =
        client.get("$ROOT/available.json")

    override suspend fun closest(lat: Double, long: Double): Response<List<TrendsPlace>> =
        client.get("$ROOT/available.json", "lat" to lat, "long" to long)

    override suspend fun place(woeid: Int, exclude: Boolean?): Response<List<PlaceTrend>> =
        client.get("$ROOT/place.json", "id" to woeid, "exclude" to exclude)
}

@Serializable
data class TrendsPlace(
    val country: String,
    val countryCode: String?,
    val name: String,
    val parentid: Int,
    val placeType: Type,
    val url: String,
    val woeid: Int
) {

    @Serializable
    data class Type(
        val code: Int,
        val name: String
    )
}
