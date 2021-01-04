/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.User

/**
 * Your app can mute, block and report users for the authenicated user.
 * For general information on reporting viloations on Twitter see
 * [How to report violations](https://support.twitter.com/articles/15789) in the help center.
 */
public interface MutesApi {

    /**
     * Returns an array of numeric user ids the authenticating user has muted.
     *
     * @param cursor Causes the list of IDs to be broken into pages of no more than 5000 IDs at a
     * time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered
     * out after connections are queried. If no cursor is provided, a value of -1 will be assumed,
     * which is the first "page." The response from the API will include a [PagingIds.previousCursor]
     * and [PagingIds.nextCursor] to allow paging back and forth. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     * @return TODO
     */
    public suspend fun ids(cursor: String = "-1"): Response<PagingIds>

    /**
     * Returns an array of [User] the authenticating user has muted.
     *
     * @param cursor  Causes the list of IDs to be broken into pages of no more than 5000 IDs at a
     * time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered
     * out after connections are queried. If no cursor is provided, a value of -1 will be assumed,
     * which is the first "page." The response from the API will include a [PagingUser.previousCursor]
     * and [PagingUser.nextCursor] to allow paging back and forth. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     * @param includeEntities The [User.entities] node will not be included when set to
     * `false`.
     * @param skipStatus When set to either `true` statuses will not be included in the returned
     * user objects.
     * @return TODO
     */
    public suspend fun list(
        cursor: String = "-1",
        includeEntities: Boolean? = null,
        skipStatus: Boolean? = null
    ): Response<PagingUser>

    /**
     * Mutes the user specified in the ID parameter for the authenticating user.
     *
     * Returns the muted user when successful. Returns a string describing the failure condition
     * when unsuccessful.
     *
     * Actions taken in this method are asynchronous. Changes will be eventually consistent.
     *
     * The URL pattern `/version/block/create/:screen_name_or_user_id.format` is still accepted but
     * not recommended. As a sequence of numbers is a valid screen name we recommend using the
     * [screenName] or [userId] parameter instead.
     *
     * @param screenName The screen name of the potentially muted user. Helpful for disambiguating
     * when a valid screen name is also a user ID.
     * @param userId The ID of the potentially muted user. Helpful for disambiguating when a valid
     * user ID is also a valid screen name.
     * @return TODO
     */
    public suspend fun create(
        screenName: String? = null,
        userId: String? = null
    ): Response<User>

    /**
     * Un-mutes the user specified in the ID parameter for the authenticating user.
     *
     * Returns the unmuted user when successful. Returns a string describing the failure condition
     * when unsuccessful.
     *
     * Actions taken in this method are asynchronous. Changes will be eventually consistent.
     *
     * @param screenName The screen name of the potentially muted user. Helpful for disambiguating
     * when a valid screen name is also a user ID.
     * @param userId The ID of the potentially muted user. Helpful for disambiguating when a valid
     * user ID is also a valid screen name.
     * @return TODO
     */
    public suspend fun destroy(
        screenName: String? = null,
        userId: String? = null
    ): Response<User>

}
