package com.sorrowblue.twitlin.api.objects

import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Coordinates type
 */
@Serializable
public enum class CoordinatesType : JvmSerializable {
    @SerialName("Polygon")
    POLYGON,

    @SerialName("Point")
    POINT
}
