package com.sorrowblue.twitlin.tweets.collections

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.CollectionId
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.objects.UserId

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
        id: CollectionId,
        count: Int? = null,
        maxPosition: String? = null,
        minPosition: String? = null
    ): Response<Collections<CollectionObjects.HasTweets, CollectionResponse.TimelinePosition>>

    /**
     * Find Collections created by a specific user or containing a specific curated Tweet.
     * Results are organized in a cursored collection.
     *
     * @param userId The ID of the user for whom to return results.
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
        userId: UserId,
        tweetId: TweetId? = null,
        count: Int = 20,
        cursor: String? = null
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.List>>

    /**
     * Find Collections created by a specific user or containing a specific curated Tweet.
     * Results are organized in a cursored collection.
     *
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
        screenName: String,
        tweetId: TweetId? = null,
        count: Int = 20,
        cursor: String? = null
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.List>>

    /**
     * Retrieve information associated with a specific Collection.
     *
     * @param id The identifier of the Collection for which to return results.
     * @return TODO
     */
    public suspend fun show(id: CollectionId): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>>

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

    /**
     * Permanently delete a Collection owned by the currently authenticated user.
     *
     * @param id The identifier of the Collection to destroy.
     * @return TODO
     */
    public suspend fun destroy(id: CollectionId): Response<CollectionDestroy>

    /**
     * Update information concerning a Collection owned by the currently authenticated user.
     *
     * Partial updates are not currently supported: please provide [name], [description], and [url]
     * whenever using this method. Omitted [description] or [url] parameters will be treated as
     * if an empty string was passed, overwriting any previously stored value for the Collection.
     *
     * @param id
     * @param name
     * @param description
     * @param url
     * @return TODO
     */
    public suspend fun update(
        id: CollectionId,
        name: String? = null,
        description: String? = null,
        url: String? = null,
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>>

    /**
     * Add a specified Tweet to a Collection.
     *
     * A collection will store up to a few thousand Tweets. Adding a Tweet to a collection beyond
     * its allowed capacity will remove the oldest Tweet in the collection based on the time
     * it was added to the collection.
     *
     * Use [CollectionsApi.curateEntries] to add Tweets to a Collection in bulk.
     *
     * @param id The identifier of the Collection receiving the Tweet.
     * @param tweetId The identifier of the Tweet to add to the Collection.
     * @param relativeTo The identifier of the Tweet used for relative positioning in a
     * [TimelineOrder.CURATION_REVERSE_CHRON] ordered collection.
     * @param above Set to false to insert the specified [tweetId] below the [relativeTo] Tweet
     * in the collection. Default: true
     * @return TODO
     */
    public suspend fun addEntries(
        id: CollectionId,
        tweetId: TweetId,
        relativeTo: TweetId? = null,
        above: Boolean = true
    ): Response<Collections<Unit, CollectionResponse.Errors>>

    /**
     * Curate a Collection by adding or removing Tweets in bulk.
     * Updates must be limited to 100 cumulative additions or removals per request.
     *
     * Use [CollectionsApi.addEntries] and [CollectionsApi.removeEntries] to add or remove
     * a single Tweet.
     *
     * @param id Set timeline id.
     * @param changes TODO
     * @return TODO
     */
    public suspend fun curateEntries(
        id: CollectionId,
        changes: List<CollectionChange>
    ): Response<Collections<Unit, CollectionResponse.Errors>>

    /**
     * Move a specified Tweet to a new position in a [TimelineOrder.CURATION_REVERSE_CHRON]
     * ordered collection.
     *
     * @param id The identifier of the Collection receiving the Tweet.
     * @param tweetId The identifier of the Tweet to add to the Collection.
     * @param relativeTo The identifier of the Tweet used for relative positioning.
     * @param above Set to false to insert the specified [tweetId] below the [relativeTo] Tweet
     * in the collection.
     * @return TODO
     */
    public suspend fun moveEntries(
        id: CollectionId,
        tweetId: TweetId,
        relativeTo: TweetId,
        above: Boolean = true
    ): Response<Collections<Unit, CollectionResponse.Errors>>

    /**
     * Remove the specified Tweet from a Collection.
     * Use [CollectionsApi.curateEntries] to remove Tweets from a Collection in bulk.
     *
     * @param id The identifier of the target Collection.
     * @param tweetId The identifier of the Tweet to remove.
     * @return TODO
     */
    public suspend fun removeEntries(
        id: CollectionId,
        tweetId: TweetId
    ): Response<Collections<Unit, CollectionResponse.Errors>>
}
