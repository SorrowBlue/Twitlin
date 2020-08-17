package com.sorrowblue.twitlin.accounts_users.lists

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.PagingTwitterUser
import com.sorrowblue.twitlin.objects.TwitterTweet
import com.sorrowblue.twitlin.objects.TwitterUser

interface ListsApi {

	/**
	 * Returns all lists the authenticating or specified user subscribes to, including their own.
	 * The user is specified using the [userId] or [screenName] parameters. If no user is given,
	 * the authenticating user is used. A maximum of `100` results will be returned by this call.
	 * Subscribed lists are returned first, followed by owned lists. This means that if a user subscribes to 90 lists and owns 20 lists,
	 * this method returns 90 subscriptions and 10 owned lists. The [reverse] method returns owned lists first,
	 * so with `[reverse]=true`, 20 owned lists and 80 subscriptions would be returned.
	 * If your goal is to obtain every list a user owns or subscribes to,
	 * use [ListsApi.ownerships] and/or [ListsApi.subscriptions] instead.
	 *
	 * @param userId The ID of the user for whom to return results. Helpful for disambiguating
	 * when a valid user ID is also a valid screen name.<br />
	 * **Note: ** Specifies the ID of the user to get lists from.
	 * Helpful for disambiguating when a valid user ID is also a valid screen name.
	 * @param screenName The screen name of the user for whom to return results.
	 * Helpful for disambiguating when a valid screen name is also a user ID.
	 * @param reverse Set this to `true` if you would like owned lists to be returned first.
	 * See description above for information on how this parameter works.
	 * @return List of Twitter users
	 */
	suspend fun list(
		userId: Int? = null,
		screenName: String? = null,
		reverse: Boolean? = null,
	): Response<List<TwitterList>>

	/**
	 * Returns the members of the specified list.
	 * Private list members will only be shown if the authenticated user owns the specified list.
	 *
	 * @param listId The numerical [TwitterList.id] of the list.
	 * @param count Specifies the number of results to return per page (see [cursor] below).
	 * The default is `20`, with a maximum of `5,000`.
	 * @param cursor Causes the collection of list members to be broken into "pages" of consistent sizes (specified by the [count] parameter).
	 * If no cursor is provided, a value of `-1` will be assumed, which is the first "page".
	 * @param includeEntities The [com.sorrowblue.twitlin.objects.TwitterUser] node will not be included when set to `false`.
	 * @param skipStatus When set to either `true` statuses will not be included in the returned user objects.
	 * @return Paged Twitter User
	 */
	suspend fun members(
		listId: Long,
		count: Int? = null,
		cursor: Long? = null,
		includeEntities: Boolean? = null,
		skipStatus: Boolean? = null,
	): Response<PagingTwitterUser>

	/**
	 * Returns the members of the specified list.
	 * Private list members will only be shown if the authenticated user owns the specified list.
	 *
	 * @param slug You can identify a list by its slug instead of its numerical id.
	 * If you decide to do so, note that you'll also have to specify the list owner using the [ownerId] or [ownerScreenName] parameters.
	 * @param ownerScreenName The screen name of the user who owns the list being requested by a [slug].
	 * @param ownerId The user ID of the user who owns the list being requested by a [slug].
	 * @param count Specifies the number of results to return per page (see [cursor] below).
	 * The default is `20`, with a maximum of `5,000`.
	 * @param cursor Causes the collection of list members to be broken into "pages" of consistent sizes (specified by the [count] parameter).
	 * If no cursor is provided, a value of *-1* will be assumed, which is the first "page".
	 * @param includeEntities    The [com.sorrowblue.twitlin.objects.TwitterUser.entities] node will not be included when set to `false`.
	 * @param skipStatus When set to either `true` statuses will not be included in the returned user objects.
	 * @return Paged Twitter User
	 */
	suspend fun members(
		slug: String,
		ownerScreenName: String? = null,
		ownerId: Long? = null,
		count: Int? = null,
		cursor: Long? = null,
		includeEntities: Boolean? = null,
		skipStatus: Boolean? = null,
	): Response<PagingTwitterUser>

	/**
	 * Check if the specified user is a member of the specified list.
	 *
	 * @param listId The numerical id of the list. Required if there is no [userId].
	 * @param userId The ID of the user for whom to return results.
	 * Helpful for disambiguating when a valid user ID is also a valid screen name.
	 * Required if there is no [listId].
	 * @param includeEntities When set to either `true` each tweet will include a node called "entities".
	 * This node offers a variety of metadata about the tweet in a discreet structure, including [com.sorrowblue.twitlin.objects.Entities.userMentions],
	 * [com.sorrowblue.twitlin.objects.Entities.urls], and [com.sorrowblue.twitlin.objects.Entities.hashtags].
	 * While entities are opt-in on timelines at present, they will be made a default component of output in the future.
	 * See [Tweet Entities](https://developer.twitter.com/overview/api/tweets) for more details.
	 * @param skipStatus When set to either `true` statuses will not be included in the returned user objects.
	 * @return specified user is a member of the specified list
	 */
	suspend fun membersShow(
		listId: Long? = null,
		userId: Long? = null,
		includeEntities: Boolean? = null,
		skipStatus: Boolean? = null,
	): Response<TwitterUser>

	/**
	 * Check if the specified user is a member of the specified list.
	 *
	 * @param slug You can identify a list by its slug instead of its numerical id.
	 * If you decide to do so, note that you'll also have to specify the list owner using the [ownerId] or [ownerScreenName] parameters.
	 * @param ownerScreenName The screen name of the user who owns the list being requested by a [slug].
	 * @param ownerId The user ID of the user who owns the list being requested by a [slug].
	 * @param includeEntities When set to either `true` each tweet will include a node called "entities".
	 * This node offers a variety of metadata about the tweet in a discreet structure, including [com.sorrowblue.twitlin.objects.Entities.userMentions],
	 * [com.sorrowblue.twitlin.objects.Entities.urls], and [com.sorrowblue.twitlin.objects.Entities.hashtags].
	 * While entities are opt-in on timelines at present, they will be made a default component of output in the future.
	 * See [Tweet Entities](https://developer.twitter.com/overview/api/tweets) for more details.
	 * @param skipStatus When set to either `true` statuses will not be included in the returned user objects.
	 * @return specified user is a member of the specified list
	 */
	suspend fun membersShow(
		slug: String,
		ownerScreenName: String? = null,
		ownerId: String? = null,
		includeEntities: Boolean? = null,
		skipStatus: Boolean? = null,
	): Response<TwitterUser>

	/**
	 * Check if the specified user is a member of the specified list.
	 *
	 * @param screenName The screen name of the user for whom to return results.
	 * Helpful for disambiguating when a valid screen name is also a user ID.
	 * @param includeEntities When set to either `true` each tweet will include a node called "entities".
	 * This node offers a variety of metadata about the tweet in a discreet structure, including [com.sorrowblue.twitlin.objects.Entities.userMentions],
	 * [com.sorrowblue.twitlin.objects.Entities.urls], and [com.sorrowblue.twitlin.objects.Entities.hashtags].
	 * While entities are opt-in on timelines at present, they will be made a default component of output in the future.
	 * See [Tweet Entities](https://developer.twitter.com/overview/api/tweets) for more details.
	 * @param skipStatus When set to either `true` statuses will not be included in the returned user objects.
	 * @return specified user is a member of the specified list
	 */
	suspend fun membersShow(
		screenName: String,
		includeEntities: Boolean? = null,
		skipStatus: Boolean? = null,
	): Response<TwitterUser>

	/**
	 * Returns the lists the specified user has been added to. If [userId] or [screenName] are not provided, the memberships for the authenticating user are returned.
	 *
	 * @param userId The ID of the user for whom to return results.
	 * Helpful for disambiguating when a valid user ID is also a valid [screenName].
	 * Required if there is no [screenName].
	 * @param screenName The screen name of the user for whom to return results.
	 * Helpful for disambiguating when a valid screen name is also a [userId].
	 * Required if there is no [userId].
	 * @param count The amount of results to return per page.
	 * Defaults to `20`. No more than `1,000` results will ever be returned in a single page.
	 * @param cursor Breaks the results into pages. Provide a value of `-1` to begin paging.
	 * Provide values as returned in the response body's [PagingTwitterList.nextCursor] and [PagingTwitterList.previousCursor] attributes to page back and forth in the list.
	 * It is recommended to always use cursors when the method supports them. See [Cursoring](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
	 * @param filterToOwnedLists When set to `true` will return just lists the authenticating user owns,
	 * and the user represented by [userId] or [screenName] is a member of.
	 * @return Paged Twitter List
	 */
	suspend fun memberships(
		userId: String? = null,
		screenName: String? = null,
		count: Long? = null,
		cursor: Long? = null,
		filterToOwnedLists: Boolean? = null,
	): Response<PagingTwitterList>

	/**
	 * Returns the lists owned by the specified Twitter user. Private lists will only be shown if the authenticated user is also the owner of the lists.
	 *
	 * @param userId The ID of the user for whom to return results.
	 * Helpful for disambiguating when a valid user ID is also a valid [screenName].
	 * Required if there is no [screenName].
	 * @param screenName The screen name of the user for whom to return results.
	 * Helpful for disambiguating when a valid screen name is also a [userId].
	 * Required if there is no [userId].
	 * @param count The amount of results to return per page.
	 * Defaults to `20`. No more than `1,000` results will ever be returned in a single page.
	 * @param cursor Breaks the results into pages. Provide a value of `-1` to begin paging.
	 * Provide values as returned in the response body's [PagingTwitterList.nextCursor] and [PagingTwitterList.previousCursor] attributes to page back and forth in the list.
	 * It is recommended to always use cursors when the method supports them. See [Cursoring](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
	 * @return Paged Twitter List
	 */
	suspend fun ownerships(
		userId: String? = null,
		screenName: String? = null,
		count: Long? = null,
		cursor: Long? = null,
	): Response<PagingTwitterList>

	/**
	 * Returns the specified list.
	 * Private lists will only be shown if the authenticated user owns the specified list.
	 *
	 * @param listId Twitter list corresponding to list_id
	 * @return Twitter list corresponding to list_id
	 */
	suspend fun show(listId: Long): Response<TwitterList>

	/**
	 * Returns the specified list.
	 * Private lists will only be shown if the authenticated user owns the specified list.
	 *
	 * @param slug You can identify a list by its slug instead of its numerical id.
	 * If you decide to do so, note that you'll also have to specify the list owner using the [ownerId] or [ownerScreenName] parameters.
	 * @param ownerScreenName The screen name of the user who owns the list being requested by a [slug].
	 * @param ownerId The user ID of the user who owns the list being requested by a [slug].
	 * @return Specified list
	 */
	suspend fun show(
		slug: String,
		ownerScreenName: String? = null,
		ownerId: Long? = null,
	): Response<TwitterList>

	/**
	 * Returns a timeline of tweets authored by members of the specified list.
	 * Retweets are included by default. Use the [includeEntities]=`false` parameter to omit retweets.
	 * [Embedded Timelines](https://developer.twitter.com/web/embedded-timelines) is a great way to embed list timelines on your website.
	 *
	 * @param listId The numerical id of the list.
	 * @param sinceId Returns results with an ID greater than (that is, more recent than) the specified ID.
	 * There are limits to the number of Tweets which can be accessed through the API.
	 * If the limit of Tweets has occured since the [sinceId], the [sinceId] will be forced to the oldest ID available.
	 * @param maxId Returns results with an ID less than (that is, older than) or equal to the specified ID.
	 * @param count Specifies the number of results to retrieve per "page".
	 * @param includeEntities Entities are ON by default in API 1.1,
	 * each tweet includes a node called "entities".
	 * This node offers a variety of metadata about the tweet in a discreet structure,
	 * including: [com.sorrowblue.twitlin.objects.Entities.userMentions],
	 * [com.sorrowblue.twitlin.objects.Entities.urls],
	 * and [com.sorrowblue.twitlin.objects.Entities.hashtags].
	 * You can omit entities from the result by using [includeEntities]=`false`.
	 * @param includeRts When set to either `true` the list timeline will contain native retweets
	 * (if they exist) in addition to the standard stream of tweets.
	 * The output format of retweeted tweets is identical to the representation you see
	 * in [com.sorrowblue.twitlin.tweets.statuses.StatusesApi.homeTimeline].
	 * @return A Timeline of tweets authored by members of the specified list
	 */
	suspend fun statuses(
		listId: Long,
		sinceId: Long? = null,
		maxId: Long? = null,
		count: Long? = null,
		includeEntities: Boolean? = null,
		includeRts: Boolean? = null
	): Response<List<TwitterTweet>>

	/**
	 * Returns a timeline of tweets authored by members of the specified list.
	 * Retweets are included by default. Use the [includeEntities]=`false` parameter to omit retweets.
	 * [Embedded Timelines](https://developer.twitter.com/web/embedded-timelines) is a great way to embed list timelines on your website.
	 *
	 * @param slug You can identify a list by its slug instead of its numerical id.
	 * If you decide to do so, note that you'll also have to specify the list owner using the [ownerId] or [ownerScreenName] parameters.
	 * @param ownerScreenName The screen name of the user who owns the list being requested by a [slug].
	 * @param ownerId The user ID of the user who owns the list being requested by a [slug].
	 * @param sinceId Returns results with an ID greater than (that is, more recent than) the specified ID.
	 * There are limits to the number of Tweets which can be accessed through the API.
	 * If the limit of Tweets has occured since the [sinceId], the [sinceId] will be forced to the oldest ID available.
	 * @param maxId Returns results with an ID less than (that is, older than) or equal to the specified ID.
	 * @param count Specifies the number of results to retrieve per "page".
	 * @param includeEntities Entities are ON by default in API 1.1,
	 * each tweet includes a node called "entities".
	 * This node offers a variety of metadata about the tweet in a discreet structure,
	 * including: [com.sorrowblue.twitlin.objects.Entities.userMentions],
	 * [com.sorrowblue.twitlin.objects.Entities.urls],
	 * and [com.sorrowblue.twitlin.objects.Entities.hashtags].
	 * You can omit entities from the result by using [includeEntities]=`false`.
	 * @param includeRts When set to either `true` the list timeline will contain native retweets
	 * (if they exist) in addition to the standard stream of tweets.
	 * The output format of retweeted tweets is identical to the representation you see
	 * in [com.sorrowblue.twitlin.tweets.statuses.StatusesApi.homeTimeline].
	 * @return A Timeline of tweets authored by members of the specified list
	 */
	suspend fun statuses(
		slug: String,
		ownerScreenName: String? = null,
		ownerId: Long? = null,
		sinceId: Long? = null,
		maxId: Long? = null,
		count: Long? = null,
		includeEntities: Boolean? = null,
		includeRts: Boolean? = null
	): Response<List<TwitterTweet>>

	/**
	 * Returns the subscribers of the specified list.
	 * Private list subscribers will only be shown if the authenticated user owns the specified list.
	 *
	 * @param listId The numerical id of the list.
	 * @param count Specifies the number of results to return per page (see cursor below).
	 * The default is `20`, with a maximum of `5,000`.
	 * @param cursor Breaks the results into pages. A single page contains `20` lists.
	 * Provide a value of -1 to begin paging.
	 * Provide values as returned in the response body's [PagingTwitterUser.nextCursor]
	 * and [PagingTwitterUser.previousCursor] attributes to page back and forth in the list.
	 * See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
	 * @param includeEntities When set to either `true` each tweet will include a node called "entities".
	 * This node offers a variety of metadata about the tweet in a discreet structure,
	 * including: user_mentions, urls, and hashtags.
	 * While entities are opt-in on timelines at present,
	 * they will be made a default component of output in the future.
	 * See Tweet Entities for more details.
	 * @param skipStatus When set to either `true` statuses will not be included in the returned user objects.
	 * @return The subscribers of the specified list
	 */
	suspend fun subscribers(
		listId: Long,
		count: Long? = null,
		cursor: Long? = null,
		includeEntities: Boolean? = null,
		skipStatus: Boolean? = null
	): Response<PagingTwitterUser>

	/**
	 * Returns the subscribers of the specified list.
	 * Private list subscribers will only be shown if the authenticated user owns the specified list.
	 *
	 * @param slug You can identify a list by its slug instead of its numerical id.
	 * If you decide to do so, note that you'll also have to specify the list owner using the [ownerId] or [ownerScreenName] parameters.
	 * @param ownerScreenName The screen name of the user who owns the list being requested by a [slug].
	 * @param ownerId The user ID of the user who owns the list being requested by a [slug].
	 * @param count Specifies the number of results to return per page (see cursor below).
	 * The default is `20`, with a maximum of `5,000`.
	 * @param cursor Breaks the results into pages. A single page contains `20` lists.
	 * Provide a value of -1 to begin paging.
	 * Provide values as returned in the response body's [PagingTwitterUser.nextCursor]
	 * and [PagingTwitterUser.previousCursor] attributes to page back and forth in the list.
	 * See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
	 * @param includeEntities When set to either `true` each tweet will include a node called "entities".
	 * This node offers a variety of metadata about the tweet in a discreet structure,
	 * including: user_mentions, urls, and hashtags.
	 * While entities are opt-in on timelines at present,
	 * they will be made a default component of output in the future.
	 * See Tweet Entities for more details.
	 * @param skipStatus When set to either `true` statuses will not be included in the returned user objects.
	 * @return The subscribers of the specified list
	 */
	suspend fun subscribers(
		slug: String,
		ownerScreenName: String? = null,
		ownerId: Long? = null,
		count: Long? = null,
		cursor: Long? = null,
		includeEntities: Boolean? = null,
		skipStatus: Boolean? = null
	): Response<PagingTwitterUser>
}
