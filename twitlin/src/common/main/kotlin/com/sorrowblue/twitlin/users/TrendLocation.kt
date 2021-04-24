/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Trend location
 *
 * @property country
 * @property countryCode
 * @property name
 * @property parentid
 * @property placeType
 * @property url
 * @property woeid
 * @constructor Create empty Trend location
 */
@AndroidParcelize
@Serializable
public data class TrendLocation(
    val country: String,
    val countryCode: String?,
    val name: String,
    val parentid: Int,
    val placeType: PlaceType,
    val url: String,
    val woeid: Int
) : AndroidParcelable, JvmSerializable {

    /**
     * Place type
     *
     * @property code
     * @property name
     * @constructor Create empty Place type
     */
    @AndroidParcelize
    @Serializable
    public data class PlaceType(val code: Int, val name: String) : AndroidParcelable, JvmSerializable
}
