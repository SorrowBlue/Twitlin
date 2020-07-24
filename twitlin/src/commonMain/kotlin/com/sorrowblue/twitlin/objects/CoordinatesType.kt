package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.AndroidParcel
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
enum class CoordinatesType : AndroidParcel {
	@SerialName("Polygon")
	POLYGON,
	@SerialName("Point")
	POINT
}
