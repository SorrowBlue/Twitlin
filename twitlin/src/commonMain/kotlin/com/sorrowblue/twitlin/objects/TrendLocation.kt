/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

@Serializable
public data class TrendLocation(
    val country: String,
    val countryCode: String?,
    val name: String,
    val parentid: Int,
    val placeType: PlaceType,
    val url: String,
    val woeid: Int
) : JvmSerializable {

    @Serializable
    public data class PlaceType(val code: Int, val name: String) : JvmSerializable
}
