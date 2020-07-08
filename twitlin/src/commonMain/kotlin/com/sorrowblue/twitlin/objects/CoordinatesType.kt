package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.AndroidParcel
import com.sorrowblue.twitlin.AndroidParcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
enum class CoordinatesType : AndroidParcel {
	@SerialName("Polygon")
	POLYGON,
	@SerialName("Point")
	POINT
}
