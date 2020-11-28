package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ProfileGeo(
    val country: String,
    val country_code: String,
    val locality: String,
    val region: String,
    @SerialName("sub_region")
    val subRegion: String,
    @SerialName("full_name")
    val full_name: String,
    val geo: Coordinates
) : Parcelable
