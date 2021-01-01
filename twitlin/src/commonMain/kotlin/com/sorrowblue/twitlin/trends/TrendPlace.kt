/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.trends

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property name
 * @property placeType
 * @property url
 * @property parentid
 * @property country
 * @property woeid
 * @property countryCode
 */
@Serializable
public data class TrendPlace(
    val name: String,
    val placeType: Type,
    val url: String,
    val parentid: Int,
    val country: String,
    val woeid: Int,
    val countryCode: String
) : JvmSerializable {

    /**
     * TODO
     *
     * @property code
     * @property name
     */
    @Serializable
    public data class Type(val code: Int, val name: String) : JvmSerializable

}
