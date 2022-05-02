package com.sorrowblue.twitlin.api.geo

import com.sorrowblue.twitlin.api.client.Error
import com.sorrowblue.twitlin.api.objects.Coordinates
import com.sorrowblue.twitlin.api.objects.Place
import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Reverse geocode
 *
 * @property query
 * @property result
 * @property errors
 */
@AndroidParcelize
@Serializable
public data class ReverseGeocode(
    val query: Query,
    val result: Result? = null,
    val errors: List<Error>? = null
) : AndroidParcelable, JvmSerializable {

    /**
     * Query
     *
     * @property url
     * @property type
     * @property params
     */
    @AndroidParcelize
    @Serializable
    public data class Query(
        val url: String,
        val type: String,
        val params: Params
    ) : AndroidParcelable, JvmSerializable {

        /**
         * Params
         *
         * @property accuracy
         * @property coordinates
         * @property granularity
         */
        @AndroidParcelize
        @Serializable
        public data class Params(
            val accuracy: Float,
            val coordinates: Coordinates,
            val granularity: String
        ) : AndroidParcelable, JvmSerializable
    }

    /**
     * Result
     *
     * @property places
     */
    @AndroidParcelize
    @Serializable
    public data class Result(
        val places: List<Place>
    ) : AndroidParcelable, JvmSerializable
}
