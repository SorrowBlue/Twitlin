/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.Entities
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.tweets.StatusesApi

/**
 * A list is a curated group of Twitter accounts. You can create your own lists or subscribe to
 * lists created by others for the authenticated user. Viewing a list timeline will show you a
 * stream of Tweets from only the accounts on that list.
 *
 * For general information on lists, see
 * [Using Twitter lists](https://support.twitter.com/articles/76460) in the help center.
 */
public interface ListsApi {

    /**
     * Returns all lists the authenticating or specified user subscribes to, including their own.
     * The user is specified using the [userId] or [screenName] parameters. If no user is given,
     * the authenticating user is used.
     *
     * A maximum of `100` results will be returned by this call. Subscribed lists are returned
     * first, followed by owned lists. This means that if a user subscribes to `90` lists and owns
     * `20` lists, this method returns `90` subscriptions and `10` owned lists. The [reverse] method
     * returns owned lists first, so with [reverse]=`true`, `20` owned lists and `80` subscriptions
     * would be returned. If your goal is to obtain every list a user owns or subscribes to, use
     * [ListsApi.ownerships] and/or [ListsApi.subscriptions] instead.
     *
     * @param userId The ID of the user for whom to return results. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     *
     * *Note:* Specifies the ID of the user to get lists from. Helpful for disambiguating when a
     * valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to return results. Helpful for
     * disambiguating when a valid screen name is also a user ID.
     * @param reverse Set this to true if you would like owned lists to be returned first.
     * See description above for information on how this parameter works.
     * @return TODO
     */
    public suspend fun list(
        userId: String? = null,
        screenName: String? = null,
        reverse: Boolean = false
    ): Response<List<UserList>>

    /**
     * Returns the members of the specified list. Private list members will only be shown if the
     * authenticated user owns the specified list.
     *
     * @param listId The numerical [UserList.id] of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide to
     * do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId he user ID of the user who owns the list being requested by a [slug].
     * @param count Specifies the number of results to return per page (see [cursor] below).
     * The default is `20`, with a maximum of `5,000`.
     * @param cursor Causes the collection of list members to be broken into "pages" of consistent
     * sizes (specified by the count parameter). If no cursor is provided, a value of `-1` will be
     * assumed, which is the first "page."
     * @param includeEntities The [User.entities] node will not be included when set
     * to `false`.
     * @param skipStatus When set to either `true` statuses will not be included in the returned
     * [User].
     * @return TODO
     */
    public suspend fun members(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        count: Int = 20,
        cursor: String = "-1",
        includeEntities: Boolean = true,
        skipStatus: Boolean = false
    ): Response<PagingUser>

    /**
     * Check if the specified user is a member of the specified list.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide
     * to do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a slug.
     * @param ownerId The user ID of the user who owns the list being requested by a slug.
     * @param userId The ID of the user for whom to return results. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to return results. Helpful for
     * disambiguating when a valid screen name is also a user ID.
     * @param includeEntities When set to either `true` each tweet will include a node called
     * "entities". This node offers a variety of metadata about the tweet in a discreet structure,
     * including: [Entities.userMentions], [Entities.urls], and [Entities.hashtags]. While entities
     * are opt-in on timelines at present, they will be made a default component of output in the
     * future. See [Entities] for more details.
     * @param skipStatus When set to either `true` statuses will not be included in the returned
     * [User].
     * @return TODO
     */
    public suspend fun showMembers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userId: String? = null,
        screenName: String? = null,
        includeEntities: Boolean = true,
        skipStatus: Boolean = false
    ): Response<User>

    /**
     * Returns the lists the specified user has been added to. If [userId] or [screenName] are not
     * provided, the memberships for the authenticating user are returned.
     *
     * @param userId The ID of the user for whom to return results. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to return results. Helpful for
     * disambiguating when a valid screen name is also a user ID.
     * @param count The amount of results to return per page. Defaults to `20`. No more than `1000`
     * results will ever be returned in a single page.
     * @param cursor Breaks the results into pages. Provide a value of `-1` to begin paging. Provide
     * values as returned in the response body's [PagingUserList.nextCursor] and
     * [PagingUserList.previousCursor] attributes to page back and forth in the list. It is
     * recommended to always use cursors when the method supports them. See
     * [Cursoring](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
     * @param filterToOwnedLists When set to `true` will return just lists the authenticating
     * user owns, and the user represented by user_id or screen_name is a member of.
     * @return TODO
     */
    public suspend fun memberships(
        userId: String? = null,
        screenName: String? = null,
        count: Int = 20,
        cursor: String = "-1",
        filterToOwnedLists: Boolean = false
    ): Response<PagingUserList>

    /**
     * Returns the lists owned by the specified Twitter user. Private lists will only be shown if
     * the authenticated user is also the owner of the lists.
     *
     * @param userId The ID of the user for whom to return results. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to return results. Helpful for
     * disambiguating when a valid screen name is also a user ID.
     * @param count The amount of results to return per page. Defaults to `20`. No more than `1000`
     * results will ever be returned in a single page.
     * @param cursor Breaks the results into pages. Provide a value of -1 to begin paging. Provide
     * values as returned in the response body's [PagingUserList.nextCursor] and
     * [PagingUserList.previousCursor] attributes to page back and forth in the list. It is
     * recommended to always use cursors when the method supports them. See
     * [Cursoring](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
     * @return TODO
     */
    public suspend fun ownerships(
        userId: String? = null,
        screenName: String? = null,
        count: Int = 20,
        cursor: String = "-1"
    ): Response<PagingUserList>

    /**
     * Returns the specified list. Private lists will only be shown if the authenticated user owns
     * the specified list.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide
     * to do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @return TODO
     */
    public suspend fun show(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null
    ): Response<UserList>

    /**
     * Returns a timeline of tweets authored by members of the specified list. Retweets are included
     * by default. Use the [includeRts]=`false` parameter to omit retweets.
     *
     * Embedded Timelines is a great way to embed list timelines on your website.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide
     * to do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @param sinceId Returns results with an ID greater than (that is, more recent than) the
     * specified ID. There are limits to the number of Tweets which can be accessed through the API.
     * If the limit of Tweets has occured since the [sinceId], the [sinceId] will be forced to the
     * oldest ID available.
     * @param maxId Returns results with an ID less than (that is, older than) or equal to the
     * specified ID.
     * @param count Specifies the number of results to retrieve per "page."
     * @param includeEntities Entities are ON by default in API 1.1, each tweet includes a node
     * called "entities". This node offers a variety of metadata about the tweet in a discreet
     * structure, including: [Entities.userMentions], [Entities.urls], and [Entities.hashtags].
     * You can omit entities from the result by using [includeEntities]=`false`
     * @param includeRts When set to either `true` the list timeline will contain native retweets
     * (if they exist) in addition to the standard stream of tweets. The output format of retweeted
     * tweets is identical to the representation you see in [StatusesApi.homeTimeline].
     * @return TODO
     */
    public suspend fun statuses(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        sinceId: String? = null,
        maxId: String? = null,
        count: Int = 20,
        includeEntities: Boolean = true,
        includeRts: Boolean = false
    ): Response<List<Tweet>>

    /**
     * Returns the subscribers of the specified list. Private list subscribers will only be shown
     * if the authenticated user owns the specified list.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide to
     * do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @param count Specifies the number of results to return per page (see cursor below).
     * The default is `20`, with a maximum of `5,000`.
     * @param cursor Breaks the results into pages. A single page contains `20` lists. Provide a
     * value of `-1` to begin paging. Provide values as returned in the response body's
     * [PagingUser.nextCursor] and [PagingUser.previousCursor] attributes to page back and forth in
     * the list. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     * @param includeEntities When set to either `true` each tweet will include a node called
     * "entities". This node offers a variety of metadata about the tweet in a discreet structure,
     * including: [Entities.userMentions], [Entities.urls], and [Entities.hashtags]. While entities
     * are opt-in on timelines at present, they will be made a default component of output in the
     * future.
     * See [Tweet Entities](https://developer.twitter.com/overview/api/tweets) for more details.
     * @param skipStatus When set to either `true` statuses will not be included in the returned
     * [User].
     * @return TODO
     */
    public suspend fun subscribers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        count: Int = 20,
        cursor: String = "-1",
        includeEntities: Boolean = true,
        skipStatus: Boolean = false
    ): Response<PagingUser>

    /**
     * Check if the specified user is a subscriber of the specified list. Returns the user if they
     * are a subscriber.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide to
     * do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @param userId The ID of the user for whom to return results. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to return results. Helpful for
     * disambiguating when a valid screen name is also a user ID.
     * @param includeEntities When set to either `true` each Tweet will include a node called
     * "entities". This node offers a variety of metadata about the tweet in a discreet structure,
     * including: [Entities.userMentions], [Entities.urls], and [Entities.hashtags]. While entities
     * are opt-in on timelines at present, they will be made a default component of output in the
     * future.
     * See [Tweet Entities](https://developer.twitter.com/overview/api/tweets) for more details.
     * @param skipStatus When set to either `true` statuses will not be included in the returned
     * [User].
     * @return TODO
     */
    public suspend fun showSubscribers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userId: String? = null,
        screenName: String? = null,
        includeEntities: Boolean = true,
        skipStatus: Boolean = false,
    ): Response<User>

    /**
     * Obtain a collection of the lists the specified user is subscribed to, `20` lists per page
     * by default. Does not include the user's own lists.
     *
     * @param userId The ID of the user for whom to return results. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to return results. Helpful for
     * disambiguating when a valid screen name is also a user ID.
     * @param count The amount of results to return per page. Defaults to `20`. No more than `1000`
     * results will ever be returned in a single page.
     * @param cursor Breaks the results into pages. Provide a value of `-1` to begin paging.
     * Provide values as returned in the response body's [PagingUserList.nextCursor] and
     * [PagingUserList.previousCursor] attributes to page back and forth in the list. It is
     * recommended to always use cursors when the method supports them.
     * See [Cursoring](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
     * @return TODO
     */
    public suspend fun subscriptions(
        userId: String? = null,
        screenName: String? = null,
        count: Int = 20,
        cursor: String = "-1"
    ): Response<PagingUserList>

    /**
     * Creates a new list for the authenticated user.
     * Note that you can create up to `1000` lists per account.
     *
     * @param name The name for the list. A list's name must start with a letter and can consist
     * only of `25` or fewer letters, numbers, "-", or "_" characters.
     * @param mode Whether your list is  public or private. Values can be [UserList.Mode.PUBLIC] or
     * [UserList.Mode.PRIVATE] . If no mode is specified the list will be [UserList.Mode.PUBLIC].
     * @param description The description to give the list.
     * @return TODO
     */
    public suspend fun create(
        name: String,
        mode: UserList.Mode = UserList.Mode.PUBLIC,
        description: String? = null
    ): Response<UserList>

    /**
     * Deletes the specified list.
     * The authenticated user must own the list to be able to destroy it.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id.
     * If you decide to do so, note that you'll also have to specify the list owner using
     * the [ownerId] or [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @return TODO
     */
    public suspend fun destroy(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null
    ): Response<UserList>

    /**
     * Add a member to a list.
     * The authenticated user must own the list to be able to add members to it.
     * Note that lists cannot have more than 5,000 members.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id.
     * If you decide to do so, note that you'll also have to specify the list owner using
     * the [ownerId] or [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @param userId The ID of the user for whom to return results. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to return results. Helpful for
     * disambiguating when a valid screen name is also a user ID.
     * @return TODO
     */
    public suspend fun createMembers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userId: String? = null,
        screenName: String? = null
    ): Response<UserList>

    /**
     * Adds multiple members to a list, by specifying a comma-separated list of member ids or
     * screen names. The authenticated user must own the list to be able to add members to it. Note
     * that lists can't have more than 5,000 members, and you are limited to adding up to 100
     * members to a list at a time with this method.
     *
     * Please note that there can be issues with lists that rapidly remove and add memberships.
     * Take care when using these methods such that you are not too rapidly switching between
     * removals and adds on the same list.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id.
     * If you decide to do so, note that you'll also have to specify the list owner using
     * the [ownerId] or [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @param userIds A comma separated list of user IDs, up to 100 are allowed in a single request.
     * @param screenNames A comma separated list of screen names, up to 100 are allowed in
     * a single request.
     * @return TODO
     */
    public suspend fun createAllMembers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userIds: List<String>? = null,
        screenNames: List<String>? = null
    ): Response<UserList>

    /**
     * Removes the specified member from the list. The authenticated user must be the list's owner
     * to remove members from the list.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide
     * to do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @param userId The ID of the user to remove from the list. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to remove from the list.
     * Helpful for disambiguating when a valid screen name is also a user ID.
     * @return TODO
     */
    public suspend fun destroyMembers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userId: String? = null,
        screenName: String? = null
    ): Response<UserList>


    /**
     * Removes multiple members from a list, by specifying a comma-separated list of member ids or
     * screen names. The authenticated user must own the list to be able to remove members from it.
     * Note that lists can't have more than 500 members, and you are limited to removing up to 100
     * members to a list at a time with this method.
     *
     * Please note that there can be issues with lists that rapidly remove and add memberships.
     * Take care when using these methods such that you are not too rapidly switching between
     * removals and adds on the same list.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id.
     * If you decide to do so, note that you'll also have to specify the list owner using
     * the [ownerId] or [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @param userIds A comma separated list of user IDs, up to 100 are allowed in a single request.
     * @param screenNames A comma separated list of screen names, up to 100 are allowed in
     * a single request.
     * @return TODO
     */
    public suspend fun destroyAllMembers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userIds: List<String>? = null,
        screenNames: List<String>? = null
    ): Response<UserList>

    /**
     * Subscribes the authenticated user to the specified list.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide
     * to do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @return TODO
     */
    public suspend fun createSubscribers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null
    ): Response<UserList>

    /**
     * Unsubscribes the authenticated user from the specified list.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide
     * to do so, note that you'll also have to specify the list owner using the [ownerId] or
     * [ownerScreenName] parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by
     * a [slug].
     * @param ownerId The user ID of the user who owns the list being requested by a [slug].
     * @return TODO
     */
    public suspend fun destroySubscribers(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null
    ): Response<UserList>

    /**
     * Updates the specified list. The authenticated user must own the list to be able to update it.
     *
     * @param listId The numerical id of the list.
     * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
     * @param ownerScreenName The screen name of the user who owns the list being requested by a slug .
     * @param ownerId The user ID of the user who owns the list being requested by a slug .
     * @param name The name for the list.
     * @param mode Whether your list is public or private. Values can be public or private . If no mode is specified the list will be public.
     * @param description The description to give the list.
     * @return TODO
     */
    public suspend fun update(
        listId: String? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        name: String? = null,
        mode: UserList.Mode? = null,
        description: String? = null
    ): Response<UserList>
}

