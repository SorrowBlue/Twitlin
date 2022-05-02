package com.sorrowblue.twitlin.api.geo

import com.sorrowblue.twitlin.api.objects.Coordinates
import com.sorrowblue.twitlin.api.objects.CoordinatesType
import com.sorrowblue.twitlin.api.objects.Place
import com.sorrowblue.twitlin.api.objects.PlaceType
import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Geo search
 *
 * @property query
 * @property result
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
         */
        @AndroidParcelize
        @Serializable
        public data class Params(
            val granularity: PlaceType,
            val query: String,
            @SerialName("trim_place")
            val trimPlace: Boolean,
            val coordinates: Coordinates,
            val type: CoordinatesType? = null
        ) : AndroidParcelable, JvmSerializable
    }

    @AndroidParcelize
    @Serializable
    public data class Result(
        val places: List<Place>
    ) : AndroidParcelable, JvmSerializable
}
