package com.sorrowblue.twitlin.v2.spaces

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.SpaceField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.objects.OptionalData
import com.sorrowblue.twitlin.v2.objects.OptionalListData
import com.sorrowblue.twitlin.v2.objects.Space


public interface SpacesAppApi {

    public suspend fun space(
        id: String,
        expansions: List<Expansion>? = null,
        spaceFields: List<SpaceField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalData<Space>>

    public suspend fun spaces(
        ids: List<String>,
        expansions: List<Expansion>? = null,
        spaceFields: List<SpaceField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<Space>>

    public suspend fun creatorIds(
        userIds: List<String>,
        maxResults: Int = 100,
        expansions: List<Expansion>? = null,
        spaceFields: List<SpaceField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<Space>>

    public suspend fun search(
        query: String,
        state: Space.State,
        maxResults: Int = 100,
        expansions: List<Expansion>? = null,
        spaceFields: List<SpaceField>? = null,
        userFields: List<UserField>? = null
    ): Response<OptionalListData<Space>>
}
