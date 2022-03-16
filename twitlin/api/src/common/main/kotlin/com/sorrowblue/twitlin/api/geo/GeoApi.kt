package com.sorrowblue.twitlin.api.geo

import com.sorrowblue.twitlin.api.objects.Place
import com.sorrowblue.twitlin.api.objects.PlaceType
import com.sorrowblue.twitlin.api.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.core.client.Response
import com.sorrowblue.twitlin.core.objects.PlaceId

/**
 * Returns all the information about a known "place". Please see the Geo objects data dictionary
 * for more details.
 */
public interface GeoApi {

    /**
     * Returns all the information about a known [Place].
     *
     * @param placeId A place in the world. These IDs can be retrieved from [GeoApi.reverseGeocode].
     * @return
     */
    public suspend fun id(placeId: PlaceId): Response<Place>

    /**
     * Given a latitude and a longitude, searches for up to 20 places that can be used as a
     * `placeId` when updating a status.
     *
     * This request is an informative call and will deliver generalized results about geography.
     *
     * @param lat The latitude to search around. This parameter will be ignored unless it is inside
     * the range `-90.0` to `+90.0` (North is positive) inclusive. It will also be ignored if there
     * isn't a corresponding [long] parameter.
     * @param long The longitude to search around. The valid ranges for longitude are `-180.0` to
     * `+180.0` (East is positive) inclusive. This parameter will be ignored if outside that range,
     * if it is not a number, if geo_enabled is turned off, or if there not a corresponding `lat`
     * parameter.
     * @param accuracy A hint on the "region" in which to search. If a number, then this is a radius
     * in meters, but it can also take a string that is suffixed with ft to specify feet. If this is
     * not passed in, then it is assumed to be `0m`. If coming from a device, in practice, this
     * value is whatever accuracy the device has measuring its location (whether it be coming from a
     * GPS, WiFi triangulation, etc.).
     * @param granularity This is the minimal granularity of place types to return and must be one
     * of: [PlaceType.NEIGHBORHOOD], [PlaceType.CITY], [PlaceType.ADMIN] or
     * [PlaceType.COUNTRY]. If no granularity is provided for the request neighborhood is assumed.
     * Setting this to [PlaceType.CITY], for example, will find places which have a type of
     * [PlaceType.CITY], [PlaceType.ADMIN] or [PlaceType.COUNTRY].
     * @param maxResults A hint as to the number of results to return. This does not guarantee that
     * the number of results returned will equal max_results, but instead informs how many "nearby"
     * results to return. Ideally, only pass in the number of places you intend to display to the
     * user here.
     * @return
     */
    public suspend fun reverseGeocode(
        lat: Double,
        long: Double,
        accuracy: String? = null,
        granularity: PlaceType? = null,
        maxResults: Int? = null
    ): Response<ReverseGeocode>

    /**
     * Search for places that can be attached to a Tweet via [StatusesApi.update]. Given a latitude
     * and a longitude pair, an IP address, or a name, this request will return a list of all the
     * valid places that can be used as the `placeId` when updating a status.
     *
     * Conceptually, a query can be made from the user's location, retrieve a list of places, have
     * the user validate the location they are at, and then send the ID of this location with a call
     * to [StatusesApi.update].
     *
     * This is the recommended method to use find places that can be attached to
     * [StatusesApi.update]. Unlike [GeoApi.reverseGeocode] which provides raw data access, this
     * endpoint can potentially re-order places with regards to the user who is authenticated. This
     * approach is also preferred for interactive place matching with the user.
     *
     * Some parameters in this method are only required based on the existence of other parameters.
     * For instance, [lat] is required if [long] is provided, and vice-versa. Authentication is
     * recommended, but not required with this method.
     *
     * @param lat The latitude to search around. This parameter will be ignored unless it is inside
     * the range `-90.0` to `+90.0` (North is positive) inclusive. It will also be ignored if there
     * isn't a corresponding [long] parameter.
     * @param long The longitude to search around. The valid ranges for longitude are `-180.0` to
     * `+180.0` (East is positive) inclusive. This parameter will be ignored if outside that range,
     * if it is not a number, if geo_enabled is turned off, or if there not a corresponding [lat]
     * parameter.
     * @param query Free-form text to match against while executing a geo-based query, best suited
     * for finding nearby locations by name. Remember to URL encode the query.
     * @param ip An IP address. Used when attempting to fix geolocation based off of the user's IP
     * address.
     * @param granularity This is the minimal granularity of place types to return and must be one
     * of: [PlaceType.NEIGHBORHOOD], [PlaceType.CITY], [PlaceType.ADMIN] or
     * [PlaceType.COUNTRY]. If no granularity is provided for the request neighborhood is assumed.
     * Setting this to [PlaceType.CITY], for example, will find places which have a type of
     * [PlaceType.CITY], [PlaceType.ADMIN] or [PlaceType.COUNTRY].
     * @param maxResults A hint as to the number of results to return. This does not guarantee that
     * the number of results returned will equal max_results, but instead informs how many "nearby"
     * results to return. Ideally, only pass in the number of places you intend to display to the
     * user here.
     * @return
     */
    public suspend fun search(
        lat: Double? = null,
        long: Double? = null,
        query: String? = null,
        ip: String? = null,
        granularity: PlaceType? = null,
        maxResults: Int? = null
    ): Response<GeoSearch>
}
