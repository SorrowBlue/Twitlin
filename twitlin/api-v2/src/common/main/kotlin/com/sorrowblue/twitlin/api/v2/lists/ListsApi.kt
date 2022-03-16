package com.sorrowblue.twitlin.api.v2.lists

import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.ListField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.objects.OptionalData
import com.sorrowblue.twitlin.api.v2.objects.PagingData
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.objects.User
import com.sorrowblue.twitlin.core.objects.ListId
import com.sorrowblue.twitlin.core.objects.UserId
import kotlin.collections.List
import kotlinx.serialization.Serializable
import com.sorrowblue.twitlin.api.v2.objects.List as Lists
import com.sorrowblue.twitlin.api.v2.tweets.Expansion as TweetsExpansion
import com.sorrowblue.twitlin.api.v2.users.Expansion as UsersExpansion

@Serializable
public class AddMemberResponse(public val is_member: Boolean)

public interface ListsApi {

    /**
     * Returns the details of a specified List.
     *
     * @param id The ID of the List to lookup.
     * @param expansions
     * @param listFields
     * @param userFields
     * @return
     */
    public suspend fun list(
        id: ListId,
        expansions: List<Expansion>? = null,
        listFields: List<ListField>? = null,
        userFields: List<UserField>? = null
    ): Response<Lists>

    /**
     * Returns a list of Tweets from the specified List.
     *
     * @param id The ID of the List whose Tweets you would like to retrieve.
     * @param expansions
     * @param tweetField
     * @param userFields
     * @param maxResults
     * @param paginationToken
     * @return
     */
    public suspend fun tweets(
        id: ListId,
        expansions: List<TweetsExpansion>? = null,
        tweetField: List<TweetField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<Tweet>>

    /**
     * Returns a list of users who are members of the specified List.
     *
     * @param id The ID of the List whose members you would like to retrieve.
     * @param expansions
     * @param tweetField
     * @param userFields
     * @param maxResults The maximum number of results to be returned per page. This can be a number between 1 and 100.
     * By default, each page will return 100 results.
     * @param paginationToken Used to request the next page of results if all results weren't returned with the latest
     * request, or to go back to the previous page of results. To return the next page, pass the next_token returned in
     * your previous response. To go back one page, pass the previous_token returned in your previous response.
     * @return
     */
    public suspend fun members(
        id: ListId,
        expansions: List<UsersExpansion>? = null,
        tweetField: List<TweetField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<User>>

    /**
     * Returns a list of users who are followers of the specified List.
     *
     * @param id The ID of the List whose followers you would like to retrieve.
     * @param expansions
     * @param tweetField
     * @param userFields
     * @param maxResults The maximum number of results to be returned per page. This can be a number between 1 and 100.
     * By default, each page will return 100 results.
     * @param paginationToken Used to request the next page of results if all results weren't returned with the latest
     * request, or to go back to the previous page of results. To return the next page, pass the next_token returned in
     * your previous response. To go back one page, pass the previous_token returned in your previous response.
     * @return
     */
    public suspend fun followers(
        id: ListId,
        expansions: List<UsersExpansion>? = null,
        tweetField: List<TweetField>? = null,
        userFields: List<UserField>? = null,
        maxResults: Int = 100,
        paginationToken: String? = null
    ): Response<PagingData<User>>

    /**
     * Enables the authenticated user to add a member to a List they own.
     *
     * @param id The ID of the List you are adding a member to.
     * @param userId The ID of the user you wish to add as a member of the List.
     * @return Indicates whether the member specified in the request has been added to the List.
     */
    public suspend fun addMember(id: ListId, userId: UserId): Response<Boolean>

    /**
     * Enables the authenticated user to remove a member from a List they own.
     *
     * @param id The ID of the List you are removing a member from.
     * @param userId The ID of the user you wish to remove as a member of the List.
     * @return Indicates whether the member specified in the request has been removed from the List.
     */
    public suspend fun removeMember(id: ListId, userId: UserId): Response<Boolean>

    /**
     * Enables the authenticated user to create a List.
     *
     * @param name The name of the List you wish to create.
     * @param description Description of the List.
     * @param isPrivate Determine whether the List should be private.
     * @return
     */
    public suspend fun create(
        name: String,
        description: String = "",
        isPrivate: Boolean = false
    ): Response<OptionalData<Lists>>

    /**
     * Enables the authenticated user to update the meta data of a specified List that they own.
     *
     * @param id The ID of the List to be updated.
     * @param name Updates the name of the List.
     * @param description Updates the description of the List.
     * @param isPrivate Determines whether the List should be private.
     * @return Indicates whether the List specified in the request has been updated.
     */
    public suspend fun update(
        id: ListId,
        name: String,
        description: String,
        isPrivate: Boolean
    ): Response<Boolean>

    /**
     * Enables the authenticated user to delete a List that they own.
     *
     * @param id The ID of the List to be deleted.
     * @return Indicates whether the List specified in the request has been deleted.
     */
    public suspend fun delete(id: ListId): Response<Boolean>
}
