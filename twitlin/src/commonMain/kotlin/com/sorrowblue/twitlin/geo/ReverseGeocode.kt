/*
 * (c) 2020 SorrowBlue.
 */

package com.sorrowblue.twitlin.geo

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.client.ErrorMessages
import com.sorrowblue.twitlin.objects.Coordinates
import com.sorrowblue.twitlin.objects.Granularity
import com.sorrowblue.twitlin.objects.Place
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property query
 * @property result
 * @property errors
 */
@Serializable
public data class ReverseGeocode(
    val query: Query,
    val result: Result? = null,
    val errors: List<ErrorMessages.Error>? = null
) : JvmSerializable {

    /**
     * TODO
     *
     * @property url
     * @property type
     * @property params
     */
    @Serializable
    public data class Query(
        val url: String,
        val type: String,
        val params: Params
    ) : JvmSerializable {

        /**
         * TODO
         *
         * @property accuracy
         * @property coordinates
         * @property granularity
         */
        @Serializable
        public data class Params(
            val accuracy: Int,
            val coordinates: Coordinates,
            val granularity: Granularity
        ) : JvmSerializable

    }

    /**
     * TODO
     *
     * @property places
     */
    @Serializable
    public data class Result(val places: List<Place>) : JvmSerializable

}
