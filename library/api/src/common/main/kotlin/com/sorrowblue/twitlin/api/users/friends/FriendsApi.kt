package com.sorrowblue.twitlin.api.users.friends

import com.sorrowblue.twitlin.api.TwitterAPI
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.objects.PagingIds
import com.sorrowblue.twitlin.api.objects.User
import com.sorrowblue.twitlin.api.users.PagingUser
import com.sorrowblue.twitlin.api.users.UsersApi
import com.sorrowblue.twitlin.api.users.followers.FollowersApi
import com.sorrowblue.twitlin.core.objects.UserId

/**
 * The following API endpoints can be used to programmatically follow users, search for users,
 * and get user information:
 *
 * * [FriendsApi.ids]
 * * [FriendsApi.list]
 *
 * For more details, please see the individual endpoint information within the API reference section.
 *
 * ### Terminology
 * To avoid confusion around the term "friends" and "followers" with respect to the API endpoints,
 * below is a definition of each:
 *
 * *Friends* - we refer to "friends" as the Twitter users that a specific user follows
 * (e.g., following). Therefore, the [FriendsApi.ids] endpoint returns a collection of user IDs that
 * the specified user follows.
 *
 * *Followers* - refers to the Twitter users that follow a specific user. Therefore, making a
 * request to the [FollowersApi.ids] endpoint returns a collection of user IDs for every user
 * following the specified user.
 */
public interface FriendsApi : TwitterAPI {

    /**
     * Returns a cursored collection of user IDs for every user the specified user is following
     * (otherwise known as their "friends").
     *
     * At this time, results are ordered with the most recent following first — however, this
     * ordering is subject to unannounced change and eventual consistency issues. Results are given
     * in groups of 5,000 user IDs and multiple "pages" of results can be navigated through using
     * the [PagingIds.nextCursor] value in subsequent requests. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     *
     * This method is especially powerful when used in conjunction with [UsersApi.lookup],
     * a method that allows you to convert user IDs into full [User] in bulk.
     *
     * @param userId The ID of the user for whom to return results.
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs
     * at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are
     * filtered out after connections are queried. If no cursor is provided, a value of `-1` will be
     * assumed, which is the first "page." The response from the API will include a
     * [PagingIds.previousCursor] and [PagingIds.nextCursor] to allow paging back and forth. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     * @param count Specifies the number of IDs attempt retrieval of, up to a maximum of 5,000 per
     * distinct request. The value of [count] is best thought of as a limit to the number of results
     * to return. When using the count parameter with this method, it is wise to use a consistent
     * count value across all requests to the same user's collection. Usage of this parameter is
     * encouraged in environments where all 5,000 IDs constitutes too large of a response.
     * @return
     */
    public suspend fun ids(userId: UserId, cursor: String = "-1", count: Int = 20): Response<PagingIds<UserId>>

    /**
     * Returns a cursored collection of user IDs for every user the specified user is following
     * (otherwise known as their "friends").
     *
     * At this time, results are ordered with the most recent following first — however, this
     * ordering is subject to unannounced change and eventual consistency issues. Results are given
     * in groups of 5,000 user IDs and multiple "pages" of results can be navigated through using
     * the [PagingIds.nextCursor] value in subsequent requests. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     *
     * This method is especially powerful when used in conjunction with [UsersApi.lookup],
     * a method that allows you to convert user IDs into full [User] in bulk.
     *
     * @param screenName The screen name of the user for whom to return results.
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs
     * at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are
     * filtered out after connections are queried. If no cursor is provided, a value of `-1` will be
     * assumed, which is the first "page." The response from the API will include a
     * [PagingIds.previousCursor] and [PagingIds.nextCursor] to allow paging back and forth. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     * @param count Specifies the number of IDs attempt retrieval of, up to a maximum of 5,000 per
     * distinct request. The value of [count] is best thought of as a limit to the number of results
     * to return. When using the count parameter with this method, it is wise to use a consistent
     * count value across all requests to the same user's collection. Usage of this parameter is
     * encouraged in environments where all 5,000 IDs constitutes too large of a response.
     * @return
     */
    public suspend fun ids(screenName: String, cursor: String = "-1", count: Int = 20): Response<PagingIds<UserId>>

    /**
     * Returns a cursored collection of user objects for every user the specified user is following
     * (otherwise known as their "friends").
     *
     * At this time, results are ordered with the most recent following first — however, this
     * ordering is subject to unannounced change and eventual consistency issues. Results are given
     * in groups of 20 users and multiple "pages" of results can be navigated through using the
     * [PagingUser.nextCursor] value in subsequent requests. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     *
     * @param userId The ID of the user for whom to return results.
     * @param cursor Causes the results to be broken into pages. If no cursor is provided, a value
     * of `-1` will be assumed, which is the first "page." The response from the API will include a
     * [PagingUser.previousCursor] and [PagingUser.nextCursor] to allow paging back and forth. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     * @param count The number of users to return per page, up to a maximum of `200`.
     * @param skipStatus When set to either `true` statuses will not be included in the returned
     * user objects.
     * @param includeUserEntities The user object [User.entities] node will not be included
     * when set to `false`.
     * @return
     */
    public suspend fun list(
        userId: UserId,
        cursor: String = "-1",
        count: Int = 20,
        skipStatus: Boolean = false,
        includeUserEntities: Boolean = true
    ): Response<PagingUser>
    /**
     * Returns a cursored collection of user objects for every user the specified user is following
     * (otherwise known as their "friends").
     *
     * At this time, results are ordered with the most recent following first — however, this
     * ordering is subject to unannounced change and eventual consistency issues. Results are given
     * in groups of 20 users and multiple "pages" of results can be navigated through using the
     * [PagingUser.nextCursor] value in subsequent requests. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     *
     * @param screenName The screen name of the user for whom to return results.
     * @param cursor Causes the results to be broken into pages. If no cursor is provided, a value
     * of `-1` will be assumed, which is the first "page." The response from the API will include a
     * [PagingUser.previousCursor] and [PagingUser.nextCursor] to allow paging back and forth. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     * @param count The number of users to return per page, up to a maximum of `200`.
     * @param skipStatus When set to either `true` statuses will not be included in the returned
     * user objects.
     * @param includeUserEntities The user object [User.entities] node will not be included
     * when set to `false`.
     * @return
     */
    public suspend fun list(
        screenName: String,
        cursor: String = "-1",
        count: Int = 20,
        skipStatus: Boolean = false,
        includeUserEntities: Boolean = true
    ): Response<PagingUser>
}
