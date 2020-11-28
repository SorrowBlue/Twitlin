package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.v2.Response
import com.sorrowblue.twitlin.v2.objects.AddSearchStreamRule
import com.sorrowblue.twitlin.v2.objects.AddSearchStreamRuleResult
import com.sorrowblue.twitlin.v2.objects.DeleteSearchStreamRuleResult
import com.sorrowblue.twitlin.v2.objects.Hidden
import com.sorrowblue.twitlin.v2.objects.SearchRecent
import com.sorrowblue.twitlin.v2.objects.SearchStreamRule
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.users.TwitterAPIV2
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime


/**
 * TODO
 *
 */
@TwitterAPIV2
interface TweetsApi {
    /**
     * Returns a variety of information about a single Tweet specified by the requested ID.
     *
     * @param id Unique identifier of the Tweet to request.
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
     * @return A single Tweet specified by the requested ID.
     */
    suspend fun tweets(
        id: String,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
    ): Response<Tweet>

    /**
     * Returns a variety of information about the Tweet specified by the requested ID or list of IDs.
     *
     * @param ids List of Tweet IDs. Up to 100 are allowed in one request.
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
     * @return Tweet specified by the requested ID or list of IDs.
     */
    suspend fun tweets(
        ids: List<String>,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
    ): Response<List<Tweet>>

    /**
     * Hides or unhides a reply to a Tweet.
     *
     * @param id Unique identifier of the Tweet to hide or unhide. The Tweet must belong to a conversation initiated by the authenticating user.
     * @param isHidden Indicates the action to perform. Specify `true` to hide the Tweet, `false` to unhide.
     * Trying to hide a Tweet that's already hidden (or unhide a Tweet that is not hidden) will result in a successful call.
     * If [isHidden] is not specified, it will be hidden.
     * @return `true` if the reply is visible, `false` if hidden.
     */
    suspend fun hidden(id: String, isHidden: Boolean = true): Response<Hidden>

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
    suspend fun searchRecent(
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
    ): Response<SearchRecent>

    /**
     * Streams about 1% of all Tweets in real-time.
     *
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
     * @return About 1% of all Tweets.
     */
    fun sampleStream(
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Flow<Response<Tweet>>

    /**
     * Streams Tweets in real-time based on a specific set of filter rules.
     *
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
     * @return About 1% of all Tweets.
     */
    fun searchStream(
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
    suspend fun searchStreamRules(ids: List<String>? = null): Response<SearchStreamRule>

    /**
     * Add or delete rules to your stream.
     * To delete one or more rules, specify [ids] as an array of a list of existing rule IDs.
     *
     * @param ids List of rule IDs, each one representing a rule already active in your stream.
     * @param dryRun Set to true to test a the syntax of your rule without submitting it.
     * This is useful if you want to check the syntax of a rule before removing one or more of your existing rules.
     * @return
     */
    suspend fun deleteSearchStreamRules(
        ids: List<String>, dryRun: Boolean? = null
    ): Response<DeleteSearchStreamRuleResult>

    /**
     * Add or delete rules to your stream.
     * To create one or more rules, specify [addSearchStreamRules] with an array of StreamRules.
     *
     * @param rules Specifies the operation you want to perform on the rules
     * @param dryRun Set to true to test a the syntax of your rule without submitting it.
     * This is useful if you want to check the syntax of a rule before removing one or more of your existing rules.
     * @return
     */
    suspend fun addSearchStreamRules(
        rules: List<AddSearchStreamRule>, dryRun: Boolean? = null
    ): Response<AddSearchStreamRuleResult>

}
