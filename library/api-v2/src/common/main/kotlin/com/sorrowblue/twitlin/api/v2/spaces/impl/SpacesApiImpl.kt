package com.sorrowblue.twitlin.api.v2.spaces.impl

import com.sorrowblue.twitlin.api.v2.Endpoints.SPACES
import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.MediaField
import com.sorrowblue.twitlin.api.v2.field.PlaceField
import com.sorrowblue.twitlin.api.v2.field.PollField
import com.sorrowblue.twitlin.api.v2.field.SpaceField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.field.toParameter
import com.sorrowblue.twitlin.api.v2.objects.OptionalData
import com.sorrowblue.twitlin.api.v2.objects.OptionalListData
import com.sorrowblue.twitlin.api.v2.objects.User
import com.sorrowblue.twitlin.api.v2.spaces.Expansion
import com.sorrowblue.twitlin.api.v2.spaces.Space
import com.sorrowblue.twitlin.api.v2.spaces.SpaceId
import com.sorrowblue.twitlin.api.v2.spaces.SpacesApi
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.UserId
import com.sorrowblue.twitlin.core.objects.toParameter
import io.ktor.client.request.parameter
import com.sorrowblue.twitlin.api.v2.users.Expansion as UsersExpansion

internal class SpacesApiImpl(private val client: TwitterClient) : SpacesApi {

    override suspend fun space(
        id: SpaceId,
        expansions: List<Expansion>?,
        spaceFields: List<SpaceField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<Space>> {
        return client.get("$SPACES/${id.id}", Response.serializer(OptionalData.serializer(Space.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("space.fields", spaceFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun spaces(
        ids: List<SpaceId>,
        expansions: List<Expansion>?,
        spaceFields: List<SpaceField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<Space>> {
        return client.get(SPACES, Response.serializer(OptionalListData.serializer(Space.serializer()))) {
            parameter("ids", ids.toParameter())
            parameter("expansions", expansions?.toParameter())
            parameter("space.fields", spaceFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun creatorIds(
        userIds: List<UserId>,
        maxResults: Int,
        expansions: List<Expansion>?,
        spaceFields: List<SpaceField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<Space>> {
        return client.get(
            "$SPACES/by/creator_ids",
            Response.serializer(OptionalListData.serializer(Space.serializer()))
        ) {
            parameter("user_ids", userIds.toParameter())
            parameter("expansions", expansions?.toParameter())
            parameter("space.fields", spaceFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun buyers(
        id: SpaceId,
        expansions: List<UsersExpansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetField: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<User>> {
        return client.get(
            "$SPACES/${id.id}/buyers",
            Response.serializer(OptionalListData.serializer(User.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetField?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun search(
        query: String,
        state: Space.State,
        maxResults: Int,
        expansions: List<Expansion>?,
        spaceFields: List<SpaceField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<Space>> {
        return client.get("$SPACES/search", Response.serializer(OptionalListData.serializer(Space.serializer()))) {
            parameter("query", query)
            parameter("state", state.value)
            parameter("expansions", expansions?.toParameter())
            parameter("space.fields", spaceFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }
}
