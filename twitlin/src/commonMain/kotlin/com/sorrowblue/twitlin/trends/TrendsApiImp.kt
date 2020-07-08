package com.sorrowblue.twitlin.trends

import com.sorrowblue.twitlin.net.Client
import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.net.Urls
import kotlinx.serialization.Serializable

private const val ROOT = "${Urls._1_1}/trends"

internal class TrendsApiImp(private val client: Client) : TrendsApi {

	override suspend fun available(): Response<List<TrendsPlace>> =
		client.getList("$ROOT/available.json", useOAuth2 = true)

	override suspend fun closest(lat: Double, long: Double): Response<List<TrendsPlace>> {
		val query = listOf("lat" to lat.toString(), "long" to long.toString())
		return client.getList("$ROOT/available.json", query, useOAuth2 = true)
	}

	override suspend fun place(woeid: Int, exclude: Boolean): Response<List<PlaceTrend>> {
		val query = listOf("id" to woeid.toString(), "exclude" to exclude.toString())
		return client.getList("$ROOT/place.json", query, useOAuth2 = true)
	}
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
