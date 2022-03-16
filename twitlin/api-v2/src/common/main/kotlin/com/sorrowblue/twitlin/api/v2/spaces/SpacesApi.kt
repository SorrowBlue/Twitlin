package com.sorrowblue.twitlin.api.v2.spaces

import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.MediaField
import com.sorrowblue.twitlin.api.v2.field.PlaceField
import com.sorrowblue.twitlin.api.v2.field.PollField
import com.sorrowblue.twitlin.api.v2.field.SpaceField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.objects.OptionalData
import com.sorrowblue.twitlin.api.v2.objects.OptionalListData
import com.sorrowblue.twitlin.api.v2.objects.User
import com.sorrowblue.twitlin.core.objects.UserId
import com.sorrowblue.twitlin.api.v2.users.Expansion as UsersExpansion

/**
 * Spaces api
 *
 * @constructor Create empty Spaces api
 */
public interface SpacesApi {

    /**
     * Space
     *
     * @param id
     * @param expansions
     * @param spaceFields
     * @param userFields
     * @return
     */
    public suspend fun space(
        id: SpaceId,
        expansions: List<Expansion>? = null,
        spaceFields: List<SpaceField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<Space>>

    /**
     * Spaces
     *
     * @param ids
     * @param expansions
     * @param spaceFields
     * @param userFields
     * @return
     */
    public suspend fun spaces(
        ids: List<SpaceId>,
        expansions: List<Expansion>? = null,
        spaceFields: List<SpaceField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<Space>>

    /**
     * Creator ids
     *
     * @param userIds
     * @param maxResults
     * @param expansions
     * @param spaceFields
     * @param userFields
     * @return
     */
    public suspend fun creatorIds(
        userIds: List<UserId>,
        maxResults: Int = 100,
        expansions: List<Expansion>? = null,
        spaceFields: List<SpaceField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<Space>>

    /**
     * Returns a list of user who purchased a ticket to the requested Space.
     *
     * @param id
     * @param expansions
     * @param mediaFields
     * @param placeFields
     * @param pollFields
     * @param tweetField
     * @param userFields
     * @return
     */
    public suspend fun buyers(
        id: SpaceId,
        expansions: List<UsersExpansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetField: List<TweetField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<User>>

    /**
     * Search
     *
     * @param query
     * @param state
     * @param maxResults
     * @param expansions
     * @param spaceFields
     * @param userFields
     * @return
     */
    public suspend fun search(
        query: String,
        state: Space.State,
        maxResults: Int = 100,
        expansions: List<Expansion>? = null,
        spaceFields: List<SpaceField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<Space>>
}
