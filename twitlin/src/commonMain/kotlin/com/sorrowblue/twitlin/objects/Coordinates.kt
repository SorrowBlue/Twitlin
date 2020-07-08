package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.AndroidParcel
import com.sorrowblue.twitlin.AndroidParcelize
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
data class Coordinates(
	val coordinates: List<Double>,
	val type: CoordinatesType
): AndroidParcel
