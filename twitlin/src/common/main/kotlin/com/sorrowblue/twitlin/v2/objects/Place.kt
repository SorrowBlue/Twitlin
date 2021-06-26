/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
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
) : AndroidParcelable, JvmSerializable {

    @AndroidParcelize
    @Serializable
    public data class Geo(
        val type: String,
        val bbox: List<Double>,
        val properties: Map<String, String>,
    ) : AndroidParcelable, JvmSerializable
}
