package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.AndroidParcel
import com.sorrowblue.twitlin.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Places are specific, named locations with corresponding geo coordinates.
 * When users decide to assign a location to their Tweet, they are presented with a list of candidate Twitter Places.
 * When using the API to post a Tweet, a Twitter Place can be attached by specifying a **place_id** when posting the Tweet.
 * Tweets associated with Places are not necessarily issued from that location but could also potentially be about that location.
 *
 * @property id ID representing this place. Note that this is represented as a string, not an integer.
 * @property url ID representing this place. Note that this is represented as a string, not an integer.
 * @property placeType The type of location represented by this place.
 * @property name Short human-readable representation of the place’s name.
 * @property fullName Full human-readable representation of the place’s name.
 * @property countryCode Shortened country code representing the country containing this place.
 * @property country Name of the country containing this place.
 * @property boundingBox A bounding box of coordinates which encloses this place.
 * @property attributes When using PowerTrack, 30-Day and Full-Archive Search APIs,
 * and Volume Streams this hash is `null`.
 */
@Parcelize
@Serializable
data class Place(
	val id: String,
	val url: String,
	@SerialName("place_type")
	val placeType: String,
	val name: String,
	@SerialName("full_name")
	val fullName: String,
	@SerialName("country_code")
	val countryCode: String,
	val country: String,
	@SerialName("bounding_box")
	val boundingBox: BoundingBox
//	val attributes: JsonObject? = null
) : AndroidParcel
