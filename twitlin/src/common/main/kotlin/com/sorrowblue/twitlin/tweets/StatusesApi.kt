/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.utilities.LanguageCode
import kotlinx.coroutines.flow.Flow

/**
 * TODO
 */
public interface StatusesApi {

    /**
     * TODO
     *
     * @param status
     * @param inReplyToStatusId
     * @param autoPopulateReplyMetadata
     * @param excludeReplyUserIds
     * @param attachmentUrl
     * @param mediaIds
     * @param possiblySensitive
     * @param lat
     * @param long
     * @param placeId
     * @param displayCoordinates
     * @param trimUser
     * @param enableDmcommands
     * @param failDmcommands
     * @param cardUri
     * @return
     */
    public suspend fun update(
        status: String,
        inReplyToStatusId: String? = null,
        autoPopulateReplyMetadata: Boolean = false,
        excludeReplyUserIds: List<Long>? = null,
        attachmentUrl: String? = null,
        mediaIds: List<Long>? = null,
        possiblySensitive: Boolean = false,
        lat: Float? = null,
        long: Float? = null,
        placeId: String? = null,
        displayCoordinates: Boolean? = null,
        trimUser: Boolean = false,
        enableDmcommands: Boolean = false,
        failDmcommands: Boolean = true,
        cardUri: String? = null
    ): Response<Tweet>

    /**
     * Destroys the status specified by the required ID parameter. The authenticating user must be
     * the author of the specified status. Returns the destroyed status if successful.
     *
     * @param id The numerical ID of the desired status.
     * @param trimUser When set to either `true` each tweet returned in a timeline will include
     * a user object including only the status authors numerical ID. Omit this parameter to
     * receive the complete [User].
     * @return TODO
     */
    public suspend fun destroy(id: String, trimUser: Boolean = false): Response<Tweet>

    /**
     * Returns a single [Tweet], specified by the id parameter. The Tweet's author will also be
     * embedded within the Tweet.
     *
     * See [StatusesApi.lookup] for getting Tweets in bulk (up to 100 per call). See also
     * [Embedded Timelines](https://developer.twitter.com/web/embedded-timelines),
     * [Embedded Tweets](https://developer.twitter.com/web/embedded-tweets), and
     * [StatusesApi.oembed] for tools to render Tweets according to Display Requirements.
     *
     * @param id The numerical ID of the desired Tweet.
     * @param trimUser When set to either `true` each Tweet returned in a timeline will include
     * a user object including only the status authors numerical ID. Omit this parameter to receive
     * the complete user object.
     * @param includeMyRetweet When set to either `true` any Tweets returned that have been
     * retweeted by the authenticating user will include an additional current_user_retweet node,
     * containing the ID of the source status for the retweet.
     * @param includeEntities The entities node will not be included when set to false.
     * @param includeExtAltText If alt text has been added to any attached media entities, this
     * parameter will return an ext_alt_text value in the top-level key for the media entity.
     * If no value has been set, this will be returned as [null].
     * @param includeCardUri When set to either `true` , the retrieved Tweet will include a
     * card_uri attribute when there is an ads card attached to the Tweet and when that card
     * was attached using the card_uri value.
     * @return TODO
     */
    public suspend fun show(
        id: String,
        trimUser: Boolean = false,
        includeMyRetweet: Boolean = false,
        includeEntities: Boolean = true,
        includeExtAltText: Boolean? = null,
        includeCardUri: Boolean = false
    ): Response<Tweet>

    /**
     * Returns a single Tweet, specified by either a Tweet web URL or the Tweet ID, in an
     * [oEmbed](http://oembed.com/)-compatible format. The returned HTML snippet will be
     * automatically recognized as an
     * [Embedded Tweet](https://developer.twitter.com/web/embedded-tweets) when
     * [Twitter's widget JavaScript is included on the page](https://developer.twitter.com/web/javascript/loading).
     *
     * The oEmbed endpoint allows customization of the final appearance of an Embedded Tweet by
     * setting the corresponding properties in HTML markup to be interpreted by Twitter's JavaScript
     * bundled with the HTML response by default. The format of the returned markup may change over
     * time as Twitter adds new features or adjusts its Tweet representation.
     *
     * The Tweet fallback markup is meant to be cached on your servers for up to the suggested cache
     * lifetime specified in the [TweetOembed.cacheAge].
     *
     * @param url The URL of the Tweet to be embedded
     * @param maxWidth The maximum width of a rendered Tweet in whole pixels. A supplied value under
     * or over the allowed range will be returned as the minimum or maximum supported width
     * respectively; the reset width value will be reflected in the returned [TweetOembed.width]
     * property. Note that Twitter does not support the oEmbed `maxheight` parameter. Tweets are
     * fundamentally text, and are therefore of unpredictable height that cannot be scaled like an
     * image or video. Relatedly, the oEmbed response will not provide a value for
     * [TweetOembed.height]. Implementations that need consistent heights for Tweets should refer to
     * the [hideThread] and [hideMedia] parameters below.
     * @param hideMedia When set to `true` links in a Tweet are not expanded to photo, video, or
     * link previews.
     * @param hideThread When set to `true` a collapsed version of the previous Tweet in a
     * conversation thread will not be displayed when the requested Tweet is in reply to another
     * Tweet.
     * @param omitScript When set to `true` the `<script>` responsible for loading `widgets.js` will
     * not be returned. Your webpages should include their own reference to `widgets.js` for use
     * across all Twitter widgets including
     * [Embedded Tweets](https://developer.twitter.com/web/embedded-tweets).
     * @param align Specifies whether the embedded Tweet should be floated left, right, or center
     * in the page relative to the parent element.
     * @param related A comma-separated list of Twitter usernames related to your content. This
     * value will be forwarded to [Tweet action intents](https://developer.twitter.com/web/intents)
     * if a viewer chooses to reply, like, or retweet the embedded Tweet.
     * @param lang Request returned HTML and a rendered Tweet in the specified
     * [Twitter language supported by embedded Tweets](https://developer.twitter.com/web/overview/languages).
     * @param theme When set to [Theme.DARK], the Tweet is displayed with light text over a dark
     * background.
     * @param linkColor Adjust the color of Tweet text links with a
     * [hexadecimal color value](https://en.wikipedia.org/wiki/Web_colors#Hex_triplet).
     * @param widgetType Set to [WidgetType.VIDEO] to return a Twitter Video embed for the given
     * Tweet.
     * @param dnt When set to `true`, the Tweet and its embedded page on your site are not used for
     * purposes that include
     * [personalized suggestions](https://support.twitter.com/articles/20169421) and
     * [personalized ads](https://support.twitter.com/articles/20170405).
     * @return TDOD
     */
    public suspend fun oembed(
        url: String,
        maxWidth: Int = 325,
        hideMedia: Boolean = false,
        hideThread: Boolean = false,
        omitScript: Boolean = false,
        align: Align = Align.NONE,
        related: String? = null,
        lang: LanguageCode = LanguageCode.ENGLISH,
        theme: Theme = Theme.LIGHT,
        linkColor: String? = null,
        widgetType: WidgetType? = null,
        dnt: Boolean = false
    ): Response<TweetOembed>

    /**
     * Returns fully-hydrated [Tweet] for up to 100 Tweets per request, as specified by
     * comma-separated values passed to the [id] parameter.
     *
     * This method is especially useful to get the details (hydrate) a collection of Tweet IDs.
     *
     * [StatusesApi.show] is used to retrieve a single Tweet object.
     *
     * There are a few things to note when using this method.
     *
     * * You must be following a protected user to be able to see their most recent Tweets. If you
     * don't follow a protected user their status will be removed.
     * * The order of Tweet IDs may not match the order of Tweets in the returned array.
     * * If a requested Tweet is unknown or deleted, then that Tweet will not be returned in the
     * results list, unless the [map] parameter is set to `true`, in which case it will be returned
     * with a value of `null`.
     * * If none of your lookup criteria matches valid Tweet IDs an empty array will be returned for
     * `map=false`.
     * * You are strongly encouraged to use a POST for larger requests.
     *
     * @param id A comma separated list of Tweet IDs, up to 100 are allowed in a single request.
     * @param includeEntities The entities node that may appear within embedded statuses will not be
     * included when set to `false`.
     * @param trimUser When set to either `true` each Tweet returned in a timeline will include a
     * [User] including only the status authors numerical ID. Omit this parameter to receive the
     * complete user object.
     * @param map When using the map parameter, Tweets that do not exist or cannot be viewed by the
     * current user will still have their key represented but with an explicitly null value paired
     * with it.
     * @param includeExtAltText If alt text has been added to any attached media entities, this
     * parameter will return an ext_alt_text value in the top-level key for the media entity.
     * If no value has been set, this will be returned as `null`.
     * @param includeCardUri When set to either `true` each Tweet returned will include a card_uri
     * attribute when there is an ads card attached to the Tweet and when that card was attached
     * using the card_uri value.
     * @return TODO
     */
    public suspend fun lookup(
        id: List<String>,
        includeEntities: Boolean = false,
        trimUser: Boolean = false,
        map: Boolean? = null,
        includeExtAltText: Boolean? = null,
        includeCardUri: Boolean = false
    ): Response<List<Tweet>>

    /**
     * Retweets a tweet. Returns the original Tweet with Retweet details embedded.
     *
     * **Usage Notes:**
     * * This method is subject to update limits. A HTTP 403 will be returned if this limit as been
     * hit.
     * * Twitter will ignore attempts to perform duplicate retweets.
     * * The [Tweet.retweetCount] will be current as of when the payload is generated and may not
     * reflect the exact count. It is intended as an approximation.
     *
     * @param id The numerical ID of the desired status.
     * @param trimUser When set to either `true` each tweet returned in a timeline will include
     * a [User] including only the status authors numerical ID.
     * Omit this parameter to receive the complete [User].
     * @return TODO
     */
    public suspend fun retweet(id: String, trimUser: Boolean = false): Response<Tweet>

    /**
     * Untweets a retweeted status. Returns the original Tweet with Retweet details embedded.
     *
     * **Usage Notes:**
     * * This method is subject to update limits.
     * A HTTP 429 will be returned if this limit has been hit.
     * * The untweeted retweet status ID must be authored by the user backing the authentication
     * token.
     * * An application must have write privileges to POST. A HTTP 401 will be returned for
     * read-only applications.
     * * When passing a source status ID instead of the retweet status ID a HTTP 200 response will
     * be returned with the same Tweet object but no action.
     *
     * @param id The numerical ID of the desired status.
     * @param trimUser When set to either `true` each Tweet returned in a timeline will include a
     * [User] including only the status authors numerical ID. Omit this parameter to receive the
     * complete [User].
     * @return TODO
     */
    public suspend fun unretweet(id: String, trimUser: Boolean = false): Response<Tweet>

    /**
     * Returns a collection of the 100 most recent retweets of the Tweet specified by the [id]
     * parameter.
     *
     * @param id The numerical ID of the desired status.
     * @param count Specifies the number of records to retrieve. Must be less than or equal to 100.
     * @param trimUser When set to either `true` each tweet returned in a timeline will include a
     * [User] including only the status authors numerical ID. Omit this parameter to receive the
     * complete [User].
     * @return TODO
     */
    public suspend fun retweets(
        id: String,
        count: Int? = null,
        trimUser: Boolean = false
    ): Response<List<Tweet>>

    /**
     * Returns the most recent Tweets authored by the authenticating user that have been retweeted
     * by others. This timeline is a subset of the user's [StatusesApi.userTimeline].
     *
     * @param count Specifies the number of records to retrieve. Must be less than or equal to 100.
     * If omitted, 20 will be assumed.
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the
     * specified ID. There are limits to the number of Tweets which can be accessed through the API.
     * If the limit of Tweets has occured since the [sinceId], the [sinceId] will be forced to the
     * oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the
     * specified ID.
     * @param trimUser When set to either `true` each tweet returned in a timeline will include a
     * [User] including only the status authors numerical ID. Omit this parameter to receive the
     * complete [User].
     * @param includeEntities The tweet entities node will not be included when set to `false`.
     * @param includeUserEntities The user entities node will not be included when set to `false`.
     * @return TODO
     */
    public suspend fun retweetsOfMe(
        count: Int = 20,
        sinceId: Long? = null,
        maxId: Long? = null,
        trimUser: Boolean = false,
        includeEntities: Boolean = true,
        includeUserEntities: Boolean = true
    ): Response<List<Tweet>>

    /**
     * Returns a collection of up to 100 user IDs belonging to users who have retweeted the Tweet
     * specified by the [id] parameter.
     *
     * This method offers similar data to [StatusesApi.retweets].
     *
     * @param id The numerical ID of the desired status.
     * @param count Specifies the number of records to retrieve. Must be less than or equal to 100.
     * @param cursor Causes the list of IDs to be broken into pages of no more than 100 IDs at a
     * time. The number of IDs returned is not guaranteed to be 100 as suspended users are filtered
     * out after connections are queried. If no cursor is provided, a value of -1 will be assumed,
     * which is the first "page." The response from the API will include a
     * [PagingIds.previousCursor] and [PagingIds.nextCursor] to allow paging back and forth.
     * See [our cursor docs](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information. While this method supports the cursor parameter, the entire result set
     * can be returned in a single cursored collection. Using the [count] parameter with this method
     * will not provide segmented cursors for use with this parameter.
     * @return TODO
     */
    public suspend fun retweetersIds(
        id: String,
        count: Int? = null,
        cursor: String = "-1"
    ): Response<PagingIds>

    /**
     * Returns a collection of the most recent [Tweets] and Retweets posted by the authenticating
     * user and the users they follow. The home timeline is central to how most users interact with
     * the Twitter service.
     *
     * Up to 800 Tweets are obtainable on the home timeline. It is more volatile for users that
     * follow many users or follow users who Tweet frequently.
     *
     * See [Working with Timelines](https://developer.twitter.com/en/docs/tweets/timelines/guides/working-with-timelines)
     * for instructions on traversing timelines efficiently.
     *
     * @param count Specifies the number of records to retrieve. Must be less than or equal to 200.
     * Defaults to 20. The value of count is best thought of as a limit to the number of tweets to
     * return because suspended or deleted content is removed after the count has been applied.
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the
     * specified ID. There are limits to the number of Tweets which can be accessed through the API.
     * If the limit of Tweets has occured since the since_id, the since_id will be forced to the
     * oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the
     * specified ID.
     * @param trimUser When set to either `true` each Tweet returned in a timeline will include
     * a [User] including only the status authors numerical ID. Omit this parameter to receive
     * the complete [User].
     * @param excludeReplies This parameter will prevent replies from appearing in the returned
     * timeline. Using [excludeReplies] with the [count] parameter will mean you will receive up-to
     * count Tweets — this is because the [count] parameter retrieves that many Tweets before
     * filtering out retweets and replies.
     * @param includeEntities The entities node will not be included when set to false.
     * @return TODO
     */
    public suspend fun homeTimeline(
        count: Int = 20,
        sinceId: String? = null,
        maxId: String? = null,
        trimUser: Boolean = false,
        excludeReplies: Boolean = false,
        includeEntities: Boolean = true
    ): Response<List<Tweet>>

    /**
     * **Important notice:** On June 19, 2019, we began enforcing a limit of 100,000 requests per
     * day to the [StatusesApi.userTimeline] endpoint, in addition to existing user-level and
     * app-level rate limits. This limit is applied on a per-application basis, meaning that a
     * single developer app can make up to 100,000 calls during any single 24-hour period.
     *
     * Returns a collection of the most recent [Tweet] posted by the [User] indicated by the
     * [screenName] or [userId] parameters.
     *
     * User timelines belonging to protected users may only be requested when the authenticated
     * user either "owns" the timeline or is an approved follower of the owner.
     *
     * The timeline returned is the equivalent of the one seen as a user's profile on Twitter.
     *
     * This method can only return up to 3,200 of a user's most recent Tweets. Native retweets of
     * other statuses by the user is included in this total, regardless of whether [includeRts] is
     * set to `false` when requesting this resource.
     *
     * See [Working with Timelines](https://developer.twitter.com/en/docs/tweets/timelines/guides/working-with-timelines)
     * for instructions on traversing timelines.
     *
     * See [Embedded Timelines](https://developer.twitter.com/web/embedded-timelines),
     * [Embedded Tweets](https://developer.twitter.com/web/embedded-tweets), and
     * [StatusesApi.oembed] for tools to render Tweets according to
     * [Display Requirements](https://about.twitter.com/company/display-requirements).
     *
     * @param userId The ID of the user for whom to return results.
     * @param screenName The screen name of the user for whom to return results.
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the
     * specified ID. There are limits to the number of Tweets that can be accessed through the API.
     * If the limit of Tweets has occured since the [sinceId], the [sinceId] will be forced to
     * the oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the
     * specified ID.
     * @param count Specifies the number of Tweets to try and retrieve, up to a maximum of 200 per
     * distinct request. The value of count is best thought of as a limit to the number of Tweets
     * to return because suspended or deleted content is removed after the count has been applied.
     * We include retweets in the count, even if [includeRts] is not supplied. It is recommended you
     * always send `includeRts=1` when using this API method.
     * @param trimUser When set to either `true` each Tweet returned in a timeline will include a
     * [User] including only the status authors numerical ID. Omit this parameter to receive the
     * complete [User].
     * @param excludeReplies This parameter will prevent replies from appearing in the returned
     * timeline. Using [excludeReplies] with the count parameter will mean you will receive up-to
     * count tweets — this is because the [count] parameter retrieves that many Tweets before
     * filtering out retweets and replies.
     * @param includeRts When set to `false`, the timeline will strip any native retweets
     * (though they will still count toward both the maximal length of the timeline and the slice
     * selected by the [count] parameter). Note: If you're using the [trimUser] parameter in
     * conjunction with [includeRts], the retweets will still contain a full [User].
     * @return TODO
     */
    public suspend fun userTimeline(
        userId: String? = null,
        screenName: String? = null,
        sinceId: String? = null,
        maxId: String? = null,
        count: Int? = null,
        trimUser: Boolean = false,
        excludeReplies: Boolean = false,
        includeRts: Boolean = true,
    ): Response<List<Tweet>>

    /**
     * **Important notice:** On June 19, 2019, we began enforcing a limit of 100,000 requests per
     * day to the [StatusesApi.mentionsTimeline] endpoint. This is in addition to existing
     * user-level rate limits (75 requests / 15-minutes). This limit is enforced on a
     * per-application basis, meaning that a single developer app can make up to 100,000 calls
     * during any single 24-hour period.
     *
     * Returns the 20 most recent mentions (Tweets containing a users's @screen_name) for the
     * authenticating user.
     *
     * The timeline returned is the equivalent of the one seen when you view
     * [your mentions](http://twitter.com/mentions) on twitter.com.
     *
     * This method can only return up to 800 tweets.
     *
     * See [Working with Timelines](https://developer.twitter.com/en/docs/tweets/timelines/guides/working-with-timelines)
     * for instructions on traversing timelines.
     *
     * @param count Specifies the number of Tweets to try and retrieve, up to a maximum of 200.
     * The value of count is best thought of as a limit to the number of tweets to return because
     * suspended or deleted content is removed after the count has been applied. We include retweets
     * in the count, even if `include_rts` is not supplied. It is recommended you always send
     * `include_rts=1` when using this API method.
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the
     * specified ID. There are limits to the number of Tweets which can be accessed through the API.
     * If the limit of Tweets has occured since the [sinceId], the [sinceId] will be forced to the
     * oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the
     * specified ID.
     * @param trimUser When set to either `true` each tweet returned in a timeline will include
     * a [User] including only the status authors numerical ID. Omit this parameter to receive
     * the complete [User].
     * @param includeEntities The entities node will not be included when set to `false`.
     * @return TODO
     */
    public suspend fun mentionsTimeline(
        count: Int? = null,
        sinceId: String? = null,
        maxId: String? = null,
        trimUser: Boolean = false,
        includeEntities: Boolean = true
    ): Response<List<Tweet>>

    public suspend fun filter(
        follow: List<String>? = null,
        track: List<String>? = null,
        locations: List<Double>? = null,
        delimited: Int? = null,
        stallWarnings: Boolean? = null
    ): Flow<Response<Unit>>
}
