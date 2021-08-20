package com.sorrowblue.twitlin.v2.spaces.impl

import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.v2.client.AppClient
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.SpaceField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.field.toParameter
import com.sorrowblue.twitlin.v2.objects.OptionalData
import com.sorrowblue.twitlin.v2.objects.OptionalListData
import com.sorrowblue.twitlin.v2.objects.Space
import com.sorrowblue.twitlin.v2.spaces.Expansion
import com.sorrowblue.twitlin.v2.spaces.SpacesAppApi

private const val SPACES = "${Urls.V2}/spaces"

internal class SpacesAppApiImpl(private val client: AppClient) : SpacesAppApi {

    override suspend fun space(
        id: String,
        expansions: List<Expansion>?,
        spaceFields: List<SpaceField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<Space>> {
        return client.get(
            "$SPACES/$id",
            Response.serializer(OptionalData.serializer(Space.serializer())),
            "expansions" to expansions?.toParameter(),
            "space.fields" to spaceFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun spaces(
        ids: List<String>,
        expansions: List<Expansion>?,
        spaceFields: List<SpaceField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<Space>> {
        return client.get(
            SPACES,
            Response.serializer(OptionalListData.serializer(Space.serializer())),
            "ids" to ids.joinToString(","),
            "expansions" to expansions?.toParameter(),
            "space.fields" to spaceFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun creatorIds(
        userIds: List<String>,
        maxResults: Int,
        expansions: List<Expansion>?,
        spaceFields: List<SpaceField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<Space>> {
        return client.get(
            "$SPACES/by/creator_ids",
            Response.serializer(OptionalListData.serializer(Space.serializer())),
            "user_ids" to userIds.joinToString(","),
            "expansions" to expansions?.toParameter(),
            "space.fields" to spaceFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }

    override suspend fun search(
        query: String,
        state: Space.State,
        maxResults: Int,
        expansions: List<Expansion>?,
        spaceFields: List<SpaceField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<Space>> {
        return client.get(
            "$SPACES/search",
            Response.serializer(OptionalListData.serializer(Space.serializer())),
            "query" to query,
            "state" to state.value,
            "expansions" to expansions?.toParameter(),
            "space.fields" to spaceFields?.toParameter(),
            "user.fields" to userFields?.toParameter()
        )
    }
}
