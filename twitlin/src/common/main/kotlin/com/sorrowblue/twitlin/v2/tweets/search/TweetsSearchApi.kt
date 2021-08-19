/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.objects.PagingData
import com.sorrowblue.twitlin.v2.objects.Tweet
import kotlinx.datetime.LocalDateTime

/**
 * * Search Tweets: recent search
 *
 * TODO
 */
public interface TweetsSearchApi {

    /**
     * The recent search endpoint returns Tweets from the last 7 days that match a search query.
     *
     * @param query One query/rule/filter for matching Tweets. Up to 512 characters.
     * @param maxResults The maximum number of search results to be returned by a request.
     * A number between 10 and the system limit (currently 100). By default, a request response will return 10 results.
     * @param nextToken This parameter is used to get the next 'page' of results.
     * The value used with the parameter is pulled directly from the response provided by the API, and should not be modified.
     * @param sinceId Returns results with a Tweet ID greater than (that is, more recent than) the specified ID.
     * The ID specified is exclusive and responses will not include it. If included with the same request as a [startTime] parameter, only [sinceId] will be used.
     * @param startTime The oldest UTC timestamp (from most recent 7 days) from which the Tweets will be provided.
     * Timestamp is in second granularity and is inclusive (i.e. 12:00:01 includes the first second of the minute).
     * If included with the same request as a [sinceId] parameter, only [sinceId] will be used. By default,
     * a request will return Tweets from up to 7 days ago if you do not include this parameter.
     * @param untilId Returns results with a Tweet ID less than (that is, older than) the specified ID.
     * The ID specified is exclusive and responses will not include it.
     * @param endTime The newest, most recent UTC timestamp to which the Tweets will be provided.
     * Timestamp is in second granularity and is exclusive (i.e. 12:00:01 excludes the first second of the minute).
     * By default, a request will return Tweets from as recent as 30 seconds ago if you do not include this parameter.
     * @param expansions List of extensions. [Expansion] enable requests to expand an ID into a full object in the `includes` response object.
     * @param mediaFields List of additional fields to return in the [Media](com.sorrowblue.twitlin.v2.objects.Media) object.
     * The response will contain the selected fields only if a Tweet contains media attachments.
     * @param placeFields List of additional fields to return in the [Place](com.sorrowblue.twitlin.v2.objects.Place) object.
     * The response will contain the selected fields only if location data is present in any of the response objects.
     * @param pollFields List of additional fields to return in the [Poll](com.sorrowblue.twitlin.v2.objects.Poll) object.
     * The response will contain the selected fields only if a Tweet contains a poll.
     * @param tweetFields List of additional fields to return in the [Tweet] object. By default, the endpoint only returns `id` and `text`.
     * @param userFields List of additional fields to return in the [User](com.sorrowblue.twitlin.v2.objects.User) object.
     * By default, the endpoint does not return any user field. To use this parameter, you must include the [TweetField.AUTHOR_ID] expansion parameter in the request.
     * @return Recent tweets specified by parameters
     */
    public suspend fun searchRecent(
        query: String,
        maxResults: Int? = null,
        nextToken: String? = null,
        sinceId: String? = null,
        startTime: LocalDateTime? = null,
        untilId: String? = null,
        endTime: LocalDateTime? = null,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<Tweet>>

    public suspend fun addStreamRules(
        rules: List<SearchStreamRule>,
        dryRun: Boolean?
    ): Response<AddedSearchStreamRules>
}
