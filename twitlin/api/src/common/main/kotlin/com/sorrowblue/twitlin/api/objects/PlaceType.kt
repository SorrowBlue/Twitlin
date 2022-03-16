package com.sorrowblue.twitlin.api.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The type of location represented by this place.
 */
@Serializable
public enum class PlaceType {

    @SerialName("neighborhood")
    NEIGHBORHOOD,

    @SerialName("city")
    CITY,

    @SerialName("admin")
    ADMIN,

    @SerialName("country")
    COUNTRY,

    @SerialName("poi")
    POI
}
