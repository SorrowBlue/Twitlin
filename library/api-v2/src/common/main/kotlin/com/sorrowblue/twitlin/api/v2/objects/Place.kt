package com.sorrowblue.twitlin.api.v2.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.objects.PlaceId
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Place
 *
 * @property fullName
 * @property id
 * @property containedWithin
 * @property country
 * @property countryCode
 * @property geo
 * @property name
 * @property placeType
 * @constructor Create empty Place
 */
@AndroidParcelize
@Serializable
public data class Place(
    @SerialName("full_name")
    val fullName: String,
    val id: PlaceId,
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

    /**
     * Geo
     *
     * @property type
     * @property bbox
     * @property properties
     * @constructor Create empty Geo
     */
    @AndroidParcelize
    @Serializable
    public data class Geo(
        val type: String,
        val bbox: List<Double>,
        val properties: Map<String, String>,
    ) : AndroidParcelable, JvmSerializable
}
