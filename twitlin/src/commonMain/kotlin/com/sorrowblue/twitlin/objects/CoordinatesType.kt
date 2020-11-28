package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
enum class CoordinatesType : Parcelable {
    @SerialName("Polygon")
    POLYGON,

    @SerialName("Point")
    POINT
}
