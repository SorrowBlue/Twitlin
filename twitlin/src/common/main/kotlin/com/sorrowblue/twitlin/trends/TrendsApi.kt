/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.trends

import com.sorrowblue.twitlin.client.Response

/**
 * ### Get trends near a location
 * An API to return the [trending topics](https://support.twitter.com/articles/101125) near
 * a specific latitude, longitude location.
 *
 * ### Get locations with trending topics
 * Retrieve a full or nearby locations list of trending topics by location.
 */
public interface TrendsApi {

    /**
     * Returns the top 50 trending topics for a specific [id], if trending information is available
     * for it.
     *
     * Note: The [id] parameter for this endpoint is the "where on earth identifier" or WOEID, which
     * is a legacy identifier created by Yahoo and has been deprecated. Twitter API v1.1 still uses
     * the numeric value to identify town and country trend locations. Reference our legacy
     * [blog post](https://blog.twitter.com/engineering/en_us/a/2010/woeids-in-twitters-trends.html)
     * , or [archived data](https://archive.org/details/geoplanet_data_7.10.0.zip0).
     *
     * Example WOEID locations include: Worldwide: 1 UK: 23424975 Brazil: 23424768 Germany: 23424829
     * Mexico: 23424900 Canada: 23424775 United States: 23424977 New York: 2459115
     *
     * To identify other ids, please use the [TrendsApi.available] endpoint.
     *
     * The response is an array of [Trend] that encode the name of the trending topic, the query
     * parameter that can be used to search for the topic on
     * [Twitter Search](http://search.twitter.com/), and the Twitter Search URL.
     *
     * The most up to date info available is returned on request. The [TrendPlaces.createdAt] field
     * will show when the oldest trend started trending. The [TrendPlaces.asOf] field contains the
     * timestamp when the list of trends was created.
     *
     * The [Trend.tweetVolume] for the last 24 hours is also returned for many trends if this is
     * available.
     *
     * @param id The numeric value that represents the location from where to return trending
     * information for from. Formerly linked to the Yahoo! Where On Earth ID Global information is
     * available by using 1 as the WOEID.
     * @param exclude Setting this equal to hashtags will remove all hashtags from the trends list.
     * @return TODO
     */
    public suspend fun place(id: Int, exclude: Boolean? = null): Response<List<TrendPlaces>>

    /**
     * Returns the locations that Twitter has trending topic information for.
     *
     * The response is an array of "locations" that encode the location's `WOEID` and some other
     * human-readable information such as a canonical name and country the location belongs in.
     *
     * Note: This endpoint uses the "where on earth identifier" or WOEID, which is a legacy
     * identifier created by Yahoo and has been deprecated. Twitter API v1.1 still uses the numeric
     * value to identify town and country trend locations. Reference our legacy
     * [blog post](https://blog.twitter.com/engineering/en_us/a/2010/woeids-in-twitters-trends.html)
     * , or [archived data](https://archive.org/details/geoplanet_data_7.10.0.zip0).
     * The url returned in the response,
     * [where.yahooapis.com](https://where.yahooapis.com) is no longer valid.
     *
     * @return TODO
     */
    public suspend fun available(): Response<List<TrendPlaces>>

    /**
     * Returns the locations that Twitter has trending topic information for,
     * closest to a specified location.
     *
     * The response is an array of "locations" that encode the location's `WOEID` and some other
     * human-readable information such as a canonical name and country the location belongs in.
     *
     * Note: The "where on earth identifier" or WOEID, is a legacy identifier created by Yahoo and
     * has been deprecated. Twitter API v1.1 still uses the numeric value to identify town and
     * country trend locations. Reference our legacy
     * [blog post](https://blog.twitter.com/engineering/en_us/a/2010/woeids-in-twitters-trends.html)
     * , or [archived data](https://archive.org/details/geoplanet_data_7.10.0.zip0).
     * The url returned in the response,
     * [where.yahooapis.com](https://where.yahooapis.com) is no longer valid.
     *
     * @param lat If provided with a [long] parameter the available trend locations will be sorted
     * by distance, nearest to furthest, to the co-ordinate pair. The valid ranges for longitude is
     * `-180.0` to `+180.0` (West is negative, East is positive) inclusive.
     * @param long If provided with a [lat] parameter the available trend locations will be sorted
     * by distance, nearest to furthest, to the co-ordinate pair. The valid ranges for longitude is
     * `-180.0` to `+180.0` (West is negative, East is positive) inclusive.
     * @return TODO
     */
    public suspend fun closest(lat: Double, long: Double): Response<List<TrendPlace>>

}
