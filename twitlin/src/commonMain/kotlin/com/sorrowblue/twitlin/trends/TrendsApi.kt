package com.sorrowblue.twitlin.trends

import com.sorrowblue.twitlin.net.Response

/**
 * @author SorrowBlue
 * @since 0.0.1
 */
interface TrendsApi {

	/**
	 * Returns the locations that Twitter has trending topic information for.
	 * The response is an array of "locations" that encode the location's [TrendsPlace.woeid] and some other human-readable
	 * information such as a canonical name and country the location belongs in.
	 *
	 * @return
	 * @since 0.0.1
	 */
	suspend fun available(): Response<List<TrendsPlace>>

	/**
	 * Returns the locations that Twitter has trending topic information for, closest to a specified location.
	 * The response is an array of "locations" that encode the location's [TrendsPlace.woeid] and some other human-readable
	 * information such as a canonical name and country the location belongs in.
	 *
	 * @param lat If provided with a long parameter the available trend locations will be sorted by distance,
	 * nearest to furthest, to the co-ordinate pair.
	 * The valid ranges for longitude is -180.0 to +180.0 (West is negative, East is positive) inclusive.
	 * @param long If provided with a lat parameter the available trend locations will be sorted by distance,
	 * nearest to furthest, to the co-ordinate pair.
	 * The valid ranges for longitude is -180.0 to +180.0 (West is negative, East is positive) inclusive.
	 * @return
	 * @since 0.0.1
	 */
	suspend fun closest(lat: Double, long: Double): Response<List<TrendsPlace>>

	/**
	 * Returns the top 50 trending topics for a specific **WOEID**, if trending information is available for it.
	 * The response is an array of trend objects that encode the name of the trending topic,
	 * the query parameter that can be used to search for the topic on Twitter Search, and the Twitter Search URL.
	 * This information is cached for 5 minutes. Requesting more frequently than that will not return any more data,
	 * and will count against rate limit usage.
	 * The tweet_volume for the last 24 hours is also returned for many trends if this is available.
	 *
	 * @param woeid
	 * @param exclude
	 */
	suspend fun place(woeid: Int, exclude: Boolean? = null): Response<List<PlaceTrend>>
}
