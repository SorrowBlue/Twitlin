package com.sorrowblue.twitlin.api.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import kotlinx.serialization.Serializable

/**
 * Bounding box
 *
 * @property coordinates A series of longitude and latitude points,
 * defining a box which will contain the Place entity this bounding box is related to.
 * Each point is an array in the form of [**longitude**, **latitude**].
 * Points are grouped into an array per bounding box.
 * Bounding box arrays are wrapped in one additional array to be compatible with the polygon notation.
 * @property type The type of data encoded in the coordinates property.
 * This will be [CoordinatesType.POLYGON] for bounding boxes and [CoordinatesType.POINT] for Tweets with exact coordinates.
 */
@AndroidParcelize
@Serializable
public data class BoundingBox(val coordinates: List<List<List<Double>>>, val type: String) :
    AndroidParcelable, JvmSerializable
