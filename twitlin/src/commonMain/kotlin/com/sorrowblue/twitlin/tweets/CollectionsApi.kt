/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.client.Response
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * ### Collections
 * A collection is an editable group of Tweets hand-selected by a Twitter user or programmatically
 * managed via collection APIs. Each collection is public and has its own page on twitter.com,
 * making it easy to share and embed in your website and apps.
 */
public interface CollectionsApi {

    /**
     * Retrieve the identified Collection, presented as a list of the Tweets curated within.
     *
     * The response structure of this method differs significantly from timelines you may be used
     * to working with elsewhere in the Twitter API.
     *
     * To navigate a Collection, use the position object of a response, which includes attributes
     * for [CollectionResponse.Position.maxPosition], [CollectionResponse.Position.minPosition],
     * and [CollectionResponse.Position.wasTruncated].
     * [CollectionResponse.Position.wasTruncated] indicates whether additional Tweets exist in
     * the collection outside of the range of the current request. To retrieve Tweets further back
     * in time, use the value of [CollectionResponse.Position.minPosition] found in the current
     * response as the [CollectionResponse.Position.maxPosition] parameter in the next call to
     * this endpoint.
     *
     * @param id The identifier of the Collection for which to return results.
     * @param count Specifies the maximum number of results to include in the response. Specify
     * a count between 1 and 200. A next_cursor value will be provided in the response if
     * additional results are available.
     * @param maxPosition Returns results with a position value less than or equal to the specified
     * position.
     * @param minPosition Returns results with a position greater than the specified position.
     * @return TODO
     */
    public suspend fun entries(
        id: String,
        count: Int? = null,
        maxPosition: String? = null,
        minPosition: String? = null
    ): Response<Collections<CollectionObjects.HasTweets, CollectionResponse.TimelinePosition>>

    /**
     * Find Collections created by a specific user or containing a specific curated Tweet.
     * Results are organized in a cursored collection.
     *
     * @param userId The ID of the user for whom to return results.
     * @param screenName The screen name of the user for whom to return results.
     * @param tweetId The identifier of the Tweet for which to return results.
     * @param count Specifies the maximum number of results to include in the response.
     * Specify a count between 1 and 200. A [CollectionResponse.Cursor.nextCursor] value will be
     * provided in the response if additional results are available.
     * @param cursor A string identifying the segment of the current result set to retrieve.
     * Values for this parameter are yielded in the cursors node attached to response objects.
     * Usage of the count parameter affects cursoring.
     * @return TODO
     */
    public suspend fun list(
        userId: String? = null,
        screenName: String? = null,
        tweetId: String? = null,
        count: Int? = null,
        cursor: String? = null
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.List>>

    /**
     * Retrieve information associated with a specific Collection.
     *
     * @param id The identifier of the Collection for which to return results.
     * @return TODO
     */
    public suspend fun show(id: String): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>>

    /**
     * Create a Collection owned by the currently authenticated user.
     *
     * The API endpoint may refuse to complete the request if the authenticated user has exceeded
     * the total number of allowed collections for their account.
     *
     * @param name The title of the collection being created, in 25 characters or less.
     * @param description A brief description of this collection in 160 characters or fewer.
     * @param url A fully-qualified URL to associate with this collection.
     * @param timelineOrder Order Tweets chronologically or in the order they are added to
     * a Collection.
     * * [TimelineOrder.CURATION_REVERSE_CHRON] - order added (default)
     * * [TimelineOrder.TWEET_CHRON] - oldest first
     * * [TimelineOrder.TWEET_REVERSE_CHRON] - most recent first
     * @return TODO
     */
    public suspend fun create(
        name: String,
        description: String? = null,
        url: String? = null,
        timelineOrder: TimelineOrder? = null
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>>
}

@Serializable
public enum class TimelineOrder {

    @SerialName("curation_reverse_chron")
    CURATION_REVERSE_CHRON,

    @SerialName("tweet_chron")
    TWEET_CHRON,

    @SerialName("tweet_reverse_chron")
    TWEET_REVERSE_CHRON,
}
