package com.sorrowblue.twitlin.api.v2.users

import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.ListField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.objects.OptionalData
import com.sorrowblue.twitlin.api.v2.objects.OptionalListData
import com.sorrowblue.twitlin.api.v2.objects.PagingData
import com.sorrowblue.twitlin.api.v2.objects.User
import com.sorrowblue.twitlin.core.objects.ListId
import com.sorrowblue.twitlin.core.objects.UserId
import kotlin.collections.List
import com.sorrowblue.twitlin.api.v2.lists.Expansion as ListsExpansion
import com.sorrowblue.twitlin.api.v2.objects.List as Lists

public interface UsersApi {

    // Blocks

    /**
     * Un blocking
     *
     * @param sourceUserId
     * @param targetUserId
     * @return
     */
    public suspend fun unBlocking(sourceUserId: UserId, targetUserId: UserId): Response<Boolean>

    /**
     * Blocking
     *
     * @param id
     * @param expansions
     * @param tweetFields
     * @param userFields
     * @param maxResults
     * @param paginationToken
     * @return
     */
    public suspend fun blocking(
        id: UserId,
        maxResults: Int = 100,
        paginationToken: String? = null,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<User>>

    /**
     * Blocking
     *
     * @param id
     * @param targetUserId
     * @return
     */
    public suspend fun blocking(id: UserId, targetUserId: UserId): Response<Boolean>

    // Follows

    /**
     * Un following
     *
     * @param sourceUserId
     * @param targetUserId
     * @return
     */
    public suspend fun unFollowing(sourceUserId: UserId, targetUserId: UserId): Response<Boolean>

    /**
     * Followers
     *
     * @param id
     * @param expansions
     * @param maxResults
     * @param paginationToken
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun followers(
        id: UserId,
        maxResults: Int = 100,
        paginationToken: String? = null,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<User>>

    /**
     * Following
     *
     * @param id
     * @param expansions
     * @param maxResults
     * @param paginationToken
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun following(
        id: UserId,
        maxResults: Int = 100,
        paginationToken: String? = null,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<User>>

    /**
     * Following
     *
     * @param id
     * @param targetUserId
     * @return
     */
    public suspend fun following(id: UserId, targetUserId: UserId): Response<Following>

    // Mutes

    /**
     * Un muting
     *
     * @param sourceUserId
     * @param targetUserId
     * @return
     */
    public suspend fun unMuting(sourceUserId: UserId, targetUserId: UserId): Response<Boolean>

    /**
     * Muting
     *
     * @param id
     * @return
     */
    public suspend fun muting(id: UserId, targetUserId: UserId): Response<Boolean>

    /**
     * Muting
     *
     * @param id
     * @return
     */
    public suspend fun mutingUser(
        id: UserId,
        maxResults: Int = 100,
        paginationToken: String? = null,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<User>>

    // User lookup

    /**
     * Users
     *
     * @param ids
     * @param expansions
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun users(
        ids: List<UserId>,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<User>>

    /**
     * Users
     *
     * @param id
     * @param expansions
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun users(
        id: UserId,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<User>>

    /**
     * By username
     *
     * @param usernames
     * @param expansions
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun byUsername(
        usernames: List<String>,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<List<User>>>

    /**
     * By username
     *
     * @param username
     * @param expansions
     * @param tweetFields
     * @param userFields
     * @return
     */
    public suspend fun byUsername(
        username: String,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<User>>

    // Lists

    /**
     * Returns all Lists owned by the specified user.
     *
     * @param id
     * @param expansions
     * @param listField
     * @param userFields
     * @param maxResults
     * @param paginationToken
     * @return
     */
    public suspend fun ownedLists(
        id: UserId,
        expansions: List<ListsExpansion>? = null,
        listField: List<ListField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<Lists>>

    /**
     * Returns all Lists a specified user is a member of.
     *
     * @param id
     * @param expansions
     * @param listField
     * @param userFields
     * @param maxResults
     * @param paginationToken
     * @return
     */
    public suspend fun membershipsList(
        id: UserId,
        expansions: List<ListsExpansion>? = null,
        listField: List<ListField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<Lists>>

    /**
     * Returns all Lists a specified user follows.
     *
     * @param id The user ID whose followed Lists you would like to retrieve.
     * @param expansions
     * @param listField
     * @param userFields
     * @param maxResults The maximum number of results to be returned per page. This can be a number between 1 and 100.
     * By default, each page will return 100 results.
     * @param paginationToken Used to request the next page of results if all results weren't returned with the latest
     * request, or to go back to the previous page of results. To return the next page, pass the next_token returned in
     * your previous response. To go back one page, pass the previous_token returned in your previous response.
     * @return
     */
    public suspend fun followedLists(
        id: UserId,
        expansions: List<ListsExpansion>? = null,
        listField: List<ListField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<Lists>>

    /**
     * Enables the authenticated user to follow a List.
     *
     * @param id The user ID who you are following a List on behalf of.
     * @param listId The ID of the List that you would like the user [id] to follow.
     * @return Indicates whether the user followed the specified List as a result of the request.
     */
    public suspend fun followList(id: UserId, listId: ListId): Response<Boolean>

    /**
     * Enables the authenticated user to unfollow a List.
     *
     * @param id The user ID who you are unfollowing a List on behalf of.
     * @param listId The ID of the List that you would like the user [id] to unfollow.
     * @return Indicates whether the user unfollowed the specified List as a result of the request.
     */
    public suspend fun unFollowList(id: UserId, listId: ListId): Response<Boolean>

    /**
     * Returns the Lists pinned by a specified user.
     *
     * @param id The user ID whose pinned Lists you would like to retrieve.
     * @param expansions
     * @param listField
     * @param userFields
     * @return
     */
    public suspend fun pinnedLists(
        id: UserId,
        expansions: List<ListsExpansion>? = null,
        listField: List<ListField>? = null,
        userFields: List<UserField>? = null
    ): Response<PagingData<Lists>>

    /**
     * Enables the authenticated user to pin a List.
     *
     * @param id The user ID who you are pinning a List on behalf of.
     * @param listId The ID of the List that you would like the user [id] to pin.
     * @return Indicates whether the user pinned the specified List as a result of the request.
     */
    public suspend fun pinList(id: UserId, listId: ListId): Response<Boolean>

    /**
     * Enables the authenticated user to unpin a List.
     *
     * @param id The user ID who you are unpin a List on behalf of.
     * @param listId The ID of the List that you would like the user [id] to unpin.
     * @return Indicates whether the user unpinned the specified List as a result of the request.
     */
    public suspend fun unPinList(id: UserId, listId: ListId): Response<Boolean>
}
