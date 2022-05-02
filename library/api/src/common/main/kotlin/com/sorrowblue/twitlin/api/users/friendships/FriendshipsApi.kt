package com.sorrowblue.twitlin.api.users.friendships

import com.sorrowblue.twitlin.api.TwitterAPI
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.objects.PagingIds
import com.sorrowblue.twitlin.api.objects.User
import com.sorrowblue.twitlin.core.objects.UserId

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
public interface FriendshipsApi : TwitterAPI {

    /**
     * Returns a collection of numeric IDs for every user who has a pending request to follow the
     * authenticating user.
     *
     * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs
     * at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are
     * filtered out after connections are queried. If no cursor is provided, a value of `-1` will be
     * assumed, which is the first "page." The response from the API will include a
     * [PagingIds.previousCursor] and [PagingIds.nextCursor] to allow paging back and forth.
     * @return
     */
    public suspend fun incoming(cursor: String = "-1"): Response<PagingIds<UserId>>

    /**
     * Returns the relationships of the authenticating user to the comma-separated list of up to
     * `100` [userId] provided. Values for [Friendships.connections] can be:
     * [Friendships.Connection.FOLLOWING], [Friendships.Connection.FOLLOWING_REQUESTED],
     * [Friendships.Connection.FOLLOWED_BY], [Friendships.Connection.NONE],
     * [Friendships.Connection.BLOCKING], [Friendships.Connection.MUTING].
     *
     * @param userId A comma separated list of user IDs,
     * up to `100` are allowed in a single request.
     * @return
     */
    public suspend fun lookup(userId: List<UserId>): Response<List<Friendships>>

    /**
     * Returns the relationships of the authenticating user to the comma-separated list of up to
     * `100` [screenName] provided. Values for [Friendships.connections] can be:
     * [Friendships.Connection.FOLLOWING], [Friendships.Connection.FOLLOWING_REQUESTED],
     * [Friendships.Connection.FOLLOWED_BY], [Friendships.Connection.NONE],
     * [Friendships.Connection.BLOCKING], [Friendships.Connection.MUTING].
     *
     * @param screenName A comma separated list of screen names,
     * up to `100` are allowed in a single request.
     * @return
     */
    public suspend fun lookupByScreenName(screenName: List<String>): Response<List<Friendships>>

    /**
     * Returns a collection of user_ids that the currently authenticated user does not want to
     * receive retweets from.
     *
     * Use [FriendshipsApi.update] to set the "no retweets" status for a
     * given user account on behalf of the current user.
     *
     * @return
     */
    public suspend fun noRetweetsIds(): Response<List<UserId>>

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
     * @return
     */
    public suspend fun outgoing(cursor: String = "-1"): Response<PagingIds<UserId>>

    /**
     * Returns detailed information about the relationship between two arbitrary users.
     *
     * @param sourceId The user_id of the subject user.
     * @param targetId The user_id of the target user.
     * @return
     */
    public suspend fun show(sourceId: UserId, targetId: UserId): Response<Relationship>

    /**
     * Returns detailed information about the relationship between two arbitrary users.
     *
     * @param sourceScreenName The screen_name of the subject user.
     * @param targetScreenName The screen_name of the target user.
     * @return
     */
    public suspend fun show(sourceScreenName: String, targetScreenName: String): Response<Relationship>

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
     * @param userId
     * @param follow
     * @return
     */
    public suspend fun create(userId: UserId, follow: Boolean? = null): Response<User>

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
     * @param follow
     * @return
     */
    public suspend fun create(screenName: String, follow: Boolean? = null): Response<User>

    /**
     * Allows the authenticating user to unfollow the user specified in the ID parameter.
     *
     * Returns the unfollowed user when successful. Returns a string describing the failure
     * condition when unsuccessful.
     *
     * Actions taken in this method are asynchronous. Changes will be eventually consistent.
     *
     * @param userId The ID of the user to unfollow.
     * @return
     */
    public suspend fun destroy(userId: UserId): Response<User>

    /**
     * Allows the authenticating user to unfollow the user specified in the ID parameter.
     *
     * Returns the unfollowed user when successful. Returns a string describing the failure
     * condition when unsuccessful.
     *
     * Actions taken in this method are asynchronous. Changes will be eventually consistent.
     *
     * @param screenName The screen name of the user to unfollow.
     * @return
     */
    public suspend fun destroy(screenName: String): Response<User>

    /**
     * Turn on/off Retweets and device notifications from the specified user.
     *
     * @param userId The ID of the user being followed.
     * @param device Turn on/off device notifications from the target user.
     * @param retweets Turn on/off Retweets from the target user.
     */
    public suspend fun update(
        userId: UserId,
        device: Boolean? = null,
        retweets: Boolean? = null
    ): Response<Relationship>

    /**
     * Turn on/off Retweets and device notifications from the specified user.
     *
     * @param screenName The screen name of the user being followed.
     * @param device Turn on/off device notifications from the target user.
     * @param retweets Turn on/off Retweets from the target user.
     */
    public suspend fun update(
        screenName: String,
        device: Boolean? = null,
        retweets: Boolean? = null
    ): Response<Relationship>
}
