package com.sorrowblue.twitlin.api.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Coordinates object data dictionary
 *
 * @property coordinates The longitude and latitude of the Tweet’s location,
 * as a collection in the form [**longitude**, **latitude**].
 * @property type The type of data encoded in the coordinates property.
 * This will be “Point” for Tweet coordinates fields.
 */
@AndroidParcelize
@Serializable
public data class Coordinates(val coordinates: List<Double>, val type: CoordinatesType) :
    AndroidParcelable, JvmSerializable
