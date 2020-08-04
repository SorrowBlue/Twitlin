package com.sorrowblue.twitlin.accounts_users.lists

import com.sorrowblue.twitlin.net.Response
import com.sorrowblue.twitlin.objects.PagingUser

interface ListsApi {

	/**
	 * Returns all lists the authenticating or specified user subscribes to, including their own.
	 * The user is specified using the user_id or screen_name parameters. If no user is given,
	 * the authenticating user is used. A maximum of 100 results will be returned by this call.
	 * Subscribed lists are returned first, followed by owned lists. This means that
	 * if a user subscribes to 90 lists and owns 20 lists, this method returns 90 subscriptions and 10 owned lists.
	 * The [reverse] method returns owned lists first, so with `[reverse]=true`, 20 owned lists and 80 subscriptions would be returned.
	 * If your goal is to obtain every list a user owns or subscribes to, use [GET lists / ownerships][ListsApi.ownerships]
	 * and/or [GET lists/subscriptions][ListsApi.subscriptions] instead.
	 *
	 * @param userId The ID of the user for whom to return results. Helpful for disambiguating
	 * when a valid user ID is also a valid screen name.
	 *
	 * **Note**:: Specifies the ID of the user to get lists from. Helpful for disambiguating
	 * when a valid user ID is also a valid screen name.
	 * @param screenName The screen name of the user for whom to return results.
	 * Helpful for disambiguating when a valid screen name is also a user ID.
	 * @param reverse Set this to `true` if you would like owned lists to be returned first.
	 * See description above for information on how this parameter works.
	 */
	suspend fun list(
		userId: Int? = null,
		screenName: String? = null,
		reverse: Boolean = false
	): Response<List<TwitterList>>

	/**
	 * Returns the members of the specified list.
	 * Private list members will only be shown if the authenticated user owns the specified list.
	 *
	 * @param listId The numerical [TwitterList.id] of the list.
	 * @param count Specifies the number of results to return per page (see [cursor] below).
	 * The default is *20*, with a maximum of *5,000*.
	 * @param cursor Causes the collection of list members to be broken into "pages" of consistent sizes
	 * (specified by the [count] parameter). If no cursor is provided, a value of -1 will be assumed,
	 * which is the first "page".
	 * @param includeEntities The [com.sorrowblue.twitlin.objects.TwitterUser] node will not be included when set to `false`.
	 * @param skipStatus
	 * @return
	 */
	suspend fun members(
		listId: Long,
		count: Int = 20,
		cursor: Long = -1,
		includeEntities: Boolean = true,
		skipStatus: Boolean = false
	): Response<PagingUser>

	suspend fun members(
		slug: String,
		ownerScreenName: String,
		ownerId: Long,
		count: Int = 20,
		cursor: Long = -1,
		includeEntities: Boolean = true,
		skipStatus: Boolean = false
	): Response<PagingUser>


	suspend fun ownerships()
	suspend fun subscriptions()
}
