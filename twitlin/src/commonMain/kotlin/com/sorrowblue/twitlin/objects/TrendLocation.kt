package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.AndroidParcel
import com.sorrowblue.twitlin.AndroidParcelize
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
data class TrendLocation(
	val country: String,
	val countryCode: String?,
	val name: String,
	val parentid: Int,
	val placeType: PlaceType,
	val url: String,
	val woeid: Int
) : AndroidParcel{

	@AndroidParcelize
	@Serializable
	data class PlaceType(
		val code: Int,
		val name: String
	): AndroidParcel
}
