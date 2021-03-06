/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.objects

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
