/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.trends

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.serializer.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * TODO
 *
 * @property trends
 * @property asOf
 * @property createdAt
 * @property locations
 */
@Serializable
public data class TrendPlaces(
    val trends: List<Trend>,
    @SerialName("as_of") @Serializable(LocalDateTimeSerializer::class) val asOf: LocalDateTime,
    @SerialName("created_at")
    @Serializable(LocalDateTimeSerializer::class) val createdAt: LocalDateTime,
    val locations: List<Location>
) : JvmSerializable {

    /**
     * TODO
     *
     * @property name
     * @property woeid
     */
    @Serializable
    public data class Location(val name: String, val woeid: Int) : JvmSerializable

}
