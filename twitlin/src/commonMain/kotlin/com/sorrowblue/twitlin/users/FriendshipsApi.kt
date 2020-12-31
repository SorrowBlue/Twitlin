/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.TwitterUser

/**
 * The following API endpoints can be used to programmatically follow users, search for users,
 * and get user information:
 *
 * * [FriendshipsApi.incoming]
 * * [FriendshipsApi.lookup]
 * * [FriendshipsApi.noRetweetsIds]
 * * [FriendshipsApi.outgoing]
 * * [FriendshipsApi.show]
 * * [FriendshipsApi.create]
 * * [FriendshipsApi.destroy]
 * * [FriendshipsApi.update]
 *
 * For more details, please see the individual endpoint information within the API reference section.
 */
public interface FriendshipsApi {

    /**
     * Returns a collection of numeric IDs for every user who has a pending request to follow the
     * authenticating user.
     *
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs
     * at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are
     * filtered out after connections are queried. If no cursor is provided, a value of `-1` will be
     * assumed, which is the first "page." The response from the API will include a
     * [PagingIds.previousCursor] and [PagingIds.nextCursor] to allow paging back and forth.
     * @return TODO
     */
    public suspend fun incoming(cursor: String = "-1"): Response<PagingIds>

    /**
     * Returns the relationships of the authenticating user to the comma-separated list of up to
     * `100` [screenName] or [userId] provided. Values for [Relationship.connections] can be:
     * [Relationship.Connection.FOLLOWING], [Relationship.Connection.FOLLOWING_REQUESTED],
     * [Relationship.Connection.FOLLOWED_BY], [Relationship.Connection.NONE],
     * [Relationship.Connection.BLOCKING],[Relationship.Connection.MUTING].
     *
     * @param screenName A comma separated list of screen names,
     * up to `100` are allowed in a single request.
     * @param userId A comma separated list of user IDs,
     * up to `100` are allowed in a single request.
     * @return TODO
     */
    public suspend fun lookup(
        screenName: List<String>? = null,
        userId: List<String>? = null
    ): Response<List<Relationship>>

    /**
     * Returns a collection of user_ids that the currently authenticated user does not want to
     * receive retweets from.
     *
     * Use [FriendshipsApi.update] to set the "no retweets" status for a
     * given user account on behalf of the current user.
     *
     * @return TODO
     */
    public suspend fun noRetweetsIds(): Response<List<String>>

    /**
     * Returns a collection of numeric IDs for every protected user for whom the authenticating user
     * has a pending follow request.
     *
     * @param cursor Causes the list of connections to be broken into pages of no more than `5000`
     * IDs at a time. The number of IDs returned is not guaranteed to be `5000` as suspended users
     * are filtered out after connections are queried. If no cursor is provided, a value of `-1`
     * will be assumed, which is the first "page." The response from the API will include a
     * [PagingIds.previousCursor] and [PagingIds.nextCursor] to allow paging back and forth. See
     * [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring)
     * for more information.
     * @return TODO
     */
    public suspend fun outgoing(cursor: String = "-1"): Response<PagingIds>

    /**
     * Returns detailed information about the relationship between two arbitrary users.
     *
     * @param sourceId The user_id of the subject user.
     * @param sourceScreenName The screen_name of the subject user.
     * @param targetId The user_id of the target user.
     * @param targetScreenName The screen_name of the target user.
     * @return TODO
     */
    public suspend fun show(
        sourceId: String? = null,
        sourceScreenName: String? = null,
        targetId: String? = null,
        targetScreenName: String? = null
    ): Response<RelationshipDetail>

    /**
     * Allows the authenticating user to follow (friend) the user specified in the ID parameter.
     *
     * Returns the followed user when successful. Returns a string describing the failure condition
     * when unsuccessful. If the user is already friends with the user a HTTP 403 may be returned,
     * though for performance reasons this method may also return a HTTP 200 OK message even if the
     * follow relationship already exists.
     *
     * Actions taken in this method are asynchronous. Changes will be eventually consistent.
     *
     * @param screenName
     * @param userId
     * @param follow
     * @return TODO
     */
    public suspend fun create(
        screenName: String? = null,
        userId: String? = null,
        follow: Boolean? = null
    ): Response<TwitterUser>

    /**
     * Allows the authenticating user to unfollow the user specified in the ID parameter.
     *
     * Returns the unfollowed user when successful. Returns a string describing the failure
     * condition when unsuccessful.
     *
     * Actions taken in this method are asynchronous. Changes will be eventually consistent.
     *
     * @param screenName The screen name of the user to unfollow.
     * @param userId The ID of the user to unfollow.
     * @return TODO
     */
    public suspend fun destroy(
        screenName: String? = null,
        userId: String? = null
    ): Response<TwitterUser>

    /**
     * Turn on/off Retweets and device notifications from the specified user.
     *
     * @param screenName The screen name of the user being followed.
     * @param userId The ID of the user being followed.
     * @param device Turn on/off device notifications from the target user.
     * @param retweets Turn on/off Retweets from the target user.
     */
    public suspend fun update(
        screenName: String? = null,
        userId: String? = null,
        device: Boolean? = null,
        retweets: Boolean? = null,
    ): Response<RelationshipDetail>

}
