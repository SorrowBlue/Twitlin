package com.sorrowblue.twitlin.geo

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.client.Error
import com.sorrowblue.twitlin.objects.Coordinates
import com.sorrowblue.twitlin.objects.Place
import kotlinx.serialization.Serializable

/**
 * TODO
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
     * TODO
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
         * TODO
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
     * TODO
     *
     * @property places
     */
    @AndroidParcelize
    @Serializable
    public data class Result(
        val places: List<Place>
    ) : AndroidParcelable, JvmSerializable
}
