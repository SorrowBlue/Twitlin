/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Coordinates object data dictionary
 *
 * @property coordinates The longitude and latitude of the Tweet’s location,
 * as a collection in the form [**longitude**, **latitude**].
 * @property type The type of data encoded in the coordinates property.
 * This will be “Point” for Tweet coordinates fields.
 */
@Serializable
public data class Coordinates(
    val coordinates: List<Double>,
    val type: CoordinatesType
) : JvmSerializable
