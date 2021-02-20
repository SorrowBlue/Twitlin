/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.User

/**
 * TODO
 */
public interface UsersApi {

    /**
     * Returns a map of the available size variations of the specified user's profile banner. If the
     * user has not uploaded a profile banner, a HTTP 404 will be served instead. This method can be
     * used instead of string manipulation on the `profile_banner_url` returned in user objects as
     * described in
     * [Profile Images and Banners](https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners).

    The profile banner data available at each size variant's URL is in PNG format.
     *
     * @param userId The ID of the user for whom to return results.
     * Helpful for disambiguating when a valid user ID is also a valid screen name.
     * @param screenName The screen name of the user for whom to return results.
     * Helpful for disambiguating when a valid screen name is also a user ID.
     * @return TODO
     */
    public suspend fun profileBanner(
        userId: String? = null,
        screenName: String? = null
    ): Response<ProfileBanner>

    /**
     * Report the specified user as a spam account to Twitter. Additionally, optionally performs the
     * equivalent of [BlocksApi.create] on behalf of the authenticated user.
     *
     * @param userId The ID of the user to report as a spammer. Helpful for disambiguating when
     * a valid user ID is also a valid screen name.
     * @param screenName The screen_name of the user to report as a spammer. Helpful for
     * disambiguating when a valid screen name is also a user ID.
     * @param performBlock Whether the account should be blocked by the authenticated user,
     * as well as being reported for spam.
     *
     * @return TODO
     */
    public suspend fun reportSpam(
        userId: String? = null,
        screenName: String? = null,
        performBlock: Boolean = true
    ): Response<User>

    /**
     * Returns fully-hydrated [User] for up to 100 users per request, as specified by
     * comma-separated values passed to the [userId] and/or [screenName] parameters.
     *
     * This method is especially useful when used in conjunction with collections of user IDs
     * returned from [FriendsApi.ids] and [FollowersApi.ids].
     *
     * [UsersApi.show] is used to retrieve a single user object.
     *
     * There are a few things to note when using this method.
     *
     * * You must be following a protected user to be able to see their most recent status update.
     * If you don't follow a protected user their status will be removed.
     * * The order of user IDs or screen names may not match the order of users in the returned
     * array.
     * * If a requested user is unknown, suspended, or deleted, then that user will not be returned
     * in the results list.
     * * If none of your lookup criteria can be satisfied by returning a user object, a HTTP 404
     * will be thrown.
     * * You are strongly encouraged to use a POST for larger requests.
     *
     * @param screenName A comma separated list of screen names, up to `100` are allowed in a single
     * request. You are strongly encouraged to use a POST for larger (up to `100` screen names)
     * requests.
     * @param userId A comma separated list of user IDs, up to `100` are allowed in a single
     * request. You are strongly encouraged to use a POST for larger requests.
     * @param includeEntities The [User.entities] node that may appear within embedded
     * statuses will not be included when set to `false`.
     * @param tweetMode Valid request values are compat and extended, which give compatibility mode
     * and extended mode, respectively for Tweets that contain over `140` characters.
     * @return TODO
     */
    public suspend fun lookup(
        screenName: List<String>? = null,
        userId: List<String>? = null,
        includeEntities: Boolean? = null,
        tweetMode: Boolean? = null
    ): Response<List<User>>

    /**
     * Provides a simple, relevance-based search interface to public user accounts on Twitter.
     * Try querying by topical interest, full name, company name, location, or other criteria.
     * Exact match searches are not supported.
     * Only the first `1,000` matching results are available.
     *
     * @param q The search query to run against people search.
     * @param page Specifies the page of results to retrieve.
     * @param count The number of potential user results to retrieve per page.
     * This value has a maximum of `20`.
     * @param includeEntities The entities node will not be included in embedded Tweet objects when
     * set to `false`.
     * @return TODO
     */
    public suspend fun search(
        q: String,
        page: Int? = null,
        count: Int? = null,
        includeEntities: Boolean? = null
    ): Response<List<User>>

    /**
     * Returns a [variety of information](https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/user-object)
     * about the user specified by the required [userId] or [screenName] parameter.
     * The author's most recent Tweet will be returned inline when possible.
     *
     * [UsersApi.lookup] is used to retrieve a bulk collection of user objects.
     *
     * You must be following a protected user to be able to see their most recent Tweet.
     * If you don't follow a protected user, the user's Tweet will be removed.
     * A Tweet will not always be returned in the current_status field.
     *
     * @param userId The ID of the user for whom to return results. Either an [userId] o
     * r [screenName] is required for this method.
     * @param screenName The screen name of the user for whom to return results.
     * Either a [userId] or [screenName] is required for this method.
     * @param includeEntities The entities node will not be included when set to `false`.
     * @return TODO
     */
    public suspend fun show(
        userId: String? = null,
        screenName: String? = null,
        includeEntities: Boolean = true
    ): Response<User>

}
