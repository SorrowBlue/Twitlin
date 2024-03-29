package com.sorrowblue.twitlin.api.trends

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
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
@AndroidParcelize
@Serializable
public data class TrendPlace(
    val name: String,
    val placeType: Type,
    val url: String,
    val parentid: Int,
    val country: String,
    val woeid: Int,
    val countryCode: String
) : AndroidParcelable, JvmSerializable {

    /**
     * TODO
     *
     * @property code
     * @property name
     */
    @AndroidParcelize
    @Serializable
    public data class Type(val code: Int, val name: String) : AndroidParcelable, JvmSerializable
}
