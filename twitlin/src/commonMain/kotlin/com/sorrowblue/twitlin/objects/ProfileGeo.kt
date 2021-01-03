/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ProfileGeo(
    val country: String,
    val country_code: String,
    val locality: String,
    val region: String,
    @SerialName("sub_region")
    val subRegion: String,
    @SerialName("full_name")
    val full_name: String,
    val geo: Coordinates
) : JvmSerializable
