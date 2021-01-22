/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Place(
    @SerialName("full_name")
    val fullName: String,
    val id: String,
    @SerialName("contained_within")
    val containedWithin: List<String>? = null,
    val country: String? = null,
    @SerialName("country_code")
    val countryCode: String? = null,
    val geo: Geo? = null,
    val name: String? = null,
    @SerialName("place_type")
    val placeType: String? = null,
) : JvmSerializable {

    @Serializable
    public data class Geo(
        val type: String,
        val bbox: List<Double>,
        val properties: Map<String, String>,
    ) : JvmSerializable {

        @Serializable
        public data class Coordinates(
            val type: String,
            val coordinates: List<Double>,
        ) : JvmSerializable
    }
}
