package com.sorrowblue.twitlin.api.v2.tweets.search

import com.sorrowblue.twitlin.api.v2.TwitterAPIv2
import com.sorrowblue.twitlin.api.v2.client.Includes
import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.MediaField
import com.sorrowblue.twitlin.api.v2.field.PlaceField
import com.sorrowblue.twitlin.api.v2.field.PollField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.objects.Media
import com.sorrowblue.twitlin.api.v2.objects.PagingData
import com.sorrowblue.twitlin.api.v2.objects.Place
import com.sorrowblue.twitlin.api.v2.objects.Poll
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.objects.User
import com.sorrowblue.twitlin.api.v2.tweets.Expansion
import com.sorrowblue.twitlin.core.objects.TweetId
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime

/**
 * Search Tweets: recent search
 */
public interface TweetsSearchApi : TwitterAPIv2 {

    // Filtered stream

    /**
     * Streams Tweets in real-time based on a specific set of filter rules.
     *
     * The Tweets returned by this endpoint count towards the Project-level Tweet cap.
     *
     * @param expansions List of extensions. [Expansion] enable requests to expand an ID into a full object in the `includes` response object.
     * @param mediaFields List of additional fields to return in the [Media](com.sorrowblue.twitlin.api.v2.objects.Media) object.
     * The response will contain the selected fields only if a Tweet contains media attachments.
     * @param placeFields List of additional fields to return in the [Place](com.sorrowblue.twitlin.api.v2.objects.Place) object.
     * The response will contain the selected fields only if location data is present in any of the response objects.
     * @param pollFields List of additional fields to return in the [Poll](com.sorrowblue.twitlin.api.v2.objects.Poll) object.
     * The response will contain the selected fields only if a Tweet contains a poll.
     * @param tweetFields List of additional fields to return in the [Tweet] object. By default, the endpoint only returns `id` and `text`.
     * @param userFields List of additional fields to return in the [User](com.sorrowblue.twitlin.api.v2.objects.User) object.
     * By default, the endpoint does not return any user field. To use this parameter, you must include the [TweetField.AUTHOR_ID] expansion parameter in the request.
     * @return About 1% of all Tweets.
     */
    public fun stream(
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Flow<Response<Tweet>>

    /**
     * Return a list of rules currently active on the streaming endpoint, either as a list or individually.
     *
     * @param ids List of rule IDs. If omitted, all rules are returned.
     * @return List of rules currently active on the streaming endpoint.
     */
    public suspend fun streamRules(ids: List<RuleId> = emptyList()): Response<SearchStreamRules>

    /**
     * Add or delete rules to your stream.
     * To create one or more rules, specify [addStreamRules] with an array of StreamRules.
     *
     * @param rules Specifies the operation you want to perform on the rules
     * @param dryRun Set to true to test a the syntax of your rule without submitting it.
     * This is useful if you want to check the syntax of a rule before removing one or more of your existing rules.
     * @return
     */
    public suspend fun addStreamRules(
        rules: List<SearchStreamRule>,
        dryRun: Boolean = false
    ): Response<AddedSearchStreamRules>

    /**
     * Add or delete rules to your stream.
     * To delete one or more rules, specify [ids] as an array of a list of existing rule IDs.
     *
     * @param ids List of rule IDs, each one representing a rule already active in your stream.
     * @param dryRun Set to true to test a the syntax of your rule without submitting it.
     * This is useful if you want to check the syntax of a rule before removing one or more of your existing rules.
     * @return
     */
    public suspend fun deleteStreamRules(
        ids: List<RuleId>,
        dryRun: Boolean = false
    ): Response<DeletedSearchStreamRules>

    /**
     * This endpoint is only available to those users who have been approved for [Academic Research access](https://developer.twitter.com/en/docs/twitter-api/getting-started/about-twitter-api#v2-access-level).
     * The full-archive search endpoint returns the complete history of public Tweets matching a search query; since the
     * first Tweet was created March 26, 2006.
     *
     * The Tweets returned by this endpoint count towards the Project-level [Tweet cap](https://developer.twitter.com/en/docs/twitter-api/tweet-caps).
     *
     * @param query One query for matching Tweets. Up to 1024 characters.
     * @param maxResults The maximum number of search results to be returned by a request.
     * A number between 10 and the system limit (currently 500). By default,
     * a request response will return 10 results.
     * @param nextToken This parameter is used to get the next 'page' of results. The value used
     * with the parameter is pulled directly from the response provided by the API,
     * and should not be modified.
     * @param sinceId Returns results with a Tweet ID greater than (for example, more recent than)
     * the specified ID. The ID specified is exclusive and responses will not include it.
     * If included with the same request as a [startTime] parameter, only [sinceId] will be used.
     * @param startTime YYYY-MM-DDTHH:mm:ssZ (ISO 8601/RFC 3339). The oldest UTC timestamp from
     * which the Tweets will be provided. Timestamp is in second granularity and is inclusive (for
     * example, 12:00:01 includes the first second of the minute). By default, a request will return
     * Tweets from up to 30 days ago if you do not include this parameter.
     * @param untilId Returns results with a Tweet ID less than (that is, older than) the specified
     * ID. Used with since_id. The ID specified is exclusive and responses will not include it.
     * @param endTime YYYY-MM-DDTHH:mm:ssZ (ISO 8601/RFC 3339). Used with start_time. The newest,
     * most recent UTC timestamp to which the Tweets will be provided. Timestamp is in second
     * granularity and is exclusive (for example, 12:00:01 excludes the first second of the minute).
     * If used without start_time, Tweets from 30 days before end_time will be returned by default.
     * If not specified, end_time will default to [now - 30 seconds].
     * @param expansions Comma-separated list of expansions. [Expansion] enable requests to expand
     * an ID into a full object in the [Includes] response object. Make sure to not include a space
     * between commas and fields.
     * @param mediaFields Comma-separated list of additional fields to return in the [Media].
     * The response will contain the selected fields only if a Tweet contains media attachments.
     * Make sure to not include a space between commas and fields.
     * @param placeFields Comma-separated list of additional fields to return in the [Place].
     * The response will contain the selected fields only if location data is present in any of
     * the response objects. Make sure to not include a space between commas and fields.
     * @param pollFields Comma-separated list of additional fields to return in the [Poll].
     * The response will contain the selected fields only if a Tweet contains a poll.
     * Make sure to not include a space between commas and fields.
     * @param tweetFields Comma-separated list of additional fields to return in the [Tweet] object.
     * By default, the endpoint only returns [Tweet.id] and [Tweet.text]. Make sure to not include
     * a space between commas and fields.
     * @param userFields Comma-separated list of additional fields to return in the [User].
     * By default, the endpoint does not return any user field. To use this parameter,
     * you must include the [Expansion.AUTHOR_ID] parameter in the request. Make sure to not
     * include a space between commas and fields.
     * @return
     */
    public suspend fun all(
        query: String,
        maxResults: Int = 10,
        nextToken: String? = null,
        sinceId: TweetId? = null,
        untilId: TweetId? = null,
        startTime: LocalDateTime? = null,
        endTime: LocalDateTime? = null,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<Tweet>>

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
     * @param mediaFields List of additional fields to return in the [Media](com.sorrowblue.twitlin.api.v2.objects.Media) object.
     * The response will contain the selected fields only if a Tweet contains media attachments.
     * @param placeFields List of additional fields to return in the [Place](com.sorrowblue.twitlin.api.v2.objects.Place) object.
     * The response will contain the selected fields only if location data is present in any of the response objects.
     * @param pollFields List of additional fields to return in the [Poll](com.sorrowblue.twitlin.api.v2.objects.Poll) object.
     * The response will contain the selected fields only if a Tweet contains a poll.
     * @param tweetFields List of additional fields to return in the [Tweet] object. By default, the endpoint only returns `id` and `text`.
     * @param userFields List of additional fields to return in the [User](com.sorrowblue.twitlin.api.v2.objects.User) object.
     * By default, the endpoint does not return any user field. To use this parameter, you must include the [TweetField.AUTHOR_ID] expansion parameter in the request.
     * @return Recent tweets specified by parameters
     */
    public suspend fun recent(
        query: String,
        maxResults: Int = 10,
        nextToken: String? = null,
        sinceId: TweetId? = null,
        untilId: TweetId? = null,
        startTime: LocalDateTime? = null,
        endTime: LocalDateTime? = null,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<Tweet>>
}
