/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * Places are specific, named locations with corresponding geo coordinates. When users decide to
 * assign a location to their Tweet, they are presented with a list of candidate Twitter Places.
 * When using the API to post a Tweet, a Twitter Place can be attached by specifying a place_id
 * when posting the Tweet. Tweets associated with Places are not necessarily issued from that
 * location but could also potentially be about that location.
 *
 * @property id ID representing this place. Note that this is represented as a string,
 * not an integer.
 * @property name Short human-readable representation of the place’s name.
 * @property fullName Full human-readable representation of the place’s name.
 * @property country Name of the country containing this place.
 * @property countryCode Shortened country code representing the country containing this place.
 * @property url URL representing the location of additional place metadata for this place.
 * @property placeType The type of location represented by this place.
 * @property attributes When using PowerTrack, 30-Day and Full-Archive Search APIs, and Volume
 * Streams this hash is null.
 * @property boundingBox A bounding box of coordinates which encloses this place.
 * @property centroid TODO
 * @property containedWithin TODO
 * @property polylines TODO
 * @property geometry TODO
 */
@Serializable
public data class Place(
    val id: String,
    val name: String,
    @SerialName("full_name")
    val fullName: String,
    val country: String,
    @SerialName("country_code")
    val countryCode: String,
    val url: String,
    @SerialName("place_type")
    val placeType: PlaceType,
    val attributes: Map<String, String>,
    @SerialName("bounding_box")
    val boundingBox: BoundingBox,
    val centroid: List<Double>? = null,
    @SerialName("contained_within")
    val containedWithin: List<Place>? = null,
    val polylines: JsonObject? = null,
    val geometry: JsonObject? = null
) : JvmSerializable {

    /**
     * A bounding box of coordinates which encloses this place.
     *
     * @property type The type of data encoded in the coordinates property. This will be “Polygon”
     * for bounding boxes and “Point” for Tweets with exact coordinates.
     * @property coordinates A series of longitude and latitude points, defining a box which will
     * contain the Place entity this bounding box is related to. Each point is an array in the form
     * of [longitude, latitude]. Points are grouped into an array per bounding box. Bounding box
     * arrays are wrapped in one additional array to be compatible with the polygon notation.
     */
    @Serializable
    public data class BoundingBox(
        val type: String,
        val coordinates: List<List<List<Float>>>
    ) : JvmSerializable
}
