package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Coordinates type
 *
 * @constructor Create empty Coordinates type
 */
@Serializable
public enum class CoordinatesType : JvmSerializable {
    @SerialName("Polygon")
    POLYGON,

    @SerialName("Point")
    POINT
}
