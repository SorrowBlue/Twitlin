package com.sorrowblue.twitlin.api.trends

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
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
@AndroidParcelize
@Serializable
public data class TrendPlaces(
    val trends: List<Trend>,
    @SerialName("as_of")
    internal val _asOf: String,
    @SerialName("created_at")
    internal val _createdAt: String,
    val locations: List<Location>
) : AndroidParcelable, JvmSerializable {

    val asOf: LocalDateTime get() = _asOf.isoToLocalDateTime()
    val createdAt: LocalDateTime get() = _createdAt.isoToLocalDateTime()

    /**
     * TODO
     *
     * @property name
     * @property woeid
     */
    @AndroidParcelize
    @Serializable
    public data class Location(val name: String, val woeid: Int) : AndroidParcelable, JvmSerializable
}
