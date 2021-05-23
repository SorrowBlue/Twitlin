package com.sorrowblue.twitlin.geo

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.Coordinates
import com.sorrowblue.twitlin.objects.CoordinatesType
import com.sorrowblue.twitlin.objects.Place
import com.sorrowblue.twitlin.objects.PlaceType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Geo search
 *
 * @property query
 * @property result
 * @constructor Create empty Geo search
 */
@AndroidParcelize
@Serializable
public data class GeoSearch(
    val query: Query,
    val result: Result
) : AndroidParcelable, JvmSerializable {

    /**
     * Query
     *
     * @property params
     * @property type
     * @property url
     * @constructor Create empty Query
     */
    @AndroidParcelize
    @Serializable
    public data class Query(
        val params: Params,
        val type: String,
        val url: String
    ) : AndroidParcelable, JvmSerializable {

        /**
         * Params
         *
         * @property granularity
         * @property query
         * @property trimPlace
         * @property coordinates
         * @property type
         * @constructor Create empty Params
         */
        @AndroidParcelize
        @Serializable
        public data class Params(
            val granularity: PlaceType,
            val query: String,
            @SerialName("trim_place") val trimPlace: Boolean,
            val coordinates: Coordinates,
            val type: CoordinatesType? = null
        ) : AndroidParcelable, JvmSerializable
    }

    @Serializable
    public data class Result(
        val places: List<Place>
    ) : JvmSerializable
}
