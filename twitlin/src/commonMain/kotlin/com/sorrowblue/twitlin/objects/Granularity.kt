/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.objects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class Granularity {

    @SerialName("neighborhood")
    NEIGHBORHOOD,

    @SerialName("city")
    CITY,

    @SerialName("admin")
    ADMIN,

    @SerialName("country")
    COUNTRY,

}
