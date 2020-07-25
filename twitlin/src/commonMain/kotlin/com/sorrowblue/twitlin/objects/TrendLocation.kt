package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class TrendLocation(
	val country: String,
	val countryCode: String?,
	val name: String,
	val parentid: Int,
	val placeType: PlaceType,
	val url: String,
	val woeid: Int
) : Parcelable{

	@Parcelize
	@Serializable
	data class PlaceType(
		val code: Int,
		val name: String
	): Parcelable
}
