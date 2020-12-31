/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class CoordinatesType : JvmSerializable {
    @SerialName("Polygon")
    POLYGON,

    @SerialName("Point")
    POINT
}
