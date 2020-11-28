package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Place(
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
    @SerialName("placeType")
    val place_type: String? = null,
) : Parcelable
