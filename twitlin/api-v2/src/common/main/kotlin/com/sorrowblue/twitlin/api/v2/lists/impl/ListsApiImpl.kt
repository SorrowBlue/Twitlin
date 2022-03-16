package com.sorrowblue.twitlin.api.v2.lists.impl

import com.sorrowblue.twitlin.api.v2.Endpoints
import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.ListField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.field.toParameter
import com.sorrowblue.twitlin.api.v2.lists.Expansion
import com.sorrowblue.twitlin.api.v2.lists.ListsApi
import com.sorrowblue.twitlin.api.v2.objects.OptionalData
import com.sorrowblue.twitlin.api.v2.objects.PagingData
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.objects.User
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.ListId
import com.sorrowblue.twitlin.core.objects.UserId
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlin.collections.List
import com.sorrowblue.twitlin.api.v2.objects.List as Lists
import com.sorrowblue.twitlin.api.v2.tweets.Expansion as TweetsExpansion

internal class ListsApiImpl(private val client: TwitterClient) : ListsApi {

    override suspend fun list(
        id: ListId,
        expansions: List<Expansion>?,
        listFields: List<ListField>?,
        userFields: List<UserField>?
    ): Response<Lists> {
        return client.get("${Endpoints.LISTS}/${id.id}", Response.serializer(com.sorrowblue.twitlin.api.v2.objects.List.serializer())) {
            parameter("expansions", expansions?.toParameter())
            parameter("list.fields", listFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun tweets(
        id: ListId,
        expansions: List<TweetsExpansion>?,
        tweetField: List<TweetField>?,
        userFields: List<UserField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<Tweet>> {
        return client.get(
            "${Endpoints.LISTS}/${id.id}/tweets",
            Response.serializer(PagingData.serializer(Tweet.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetField?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }
    }

    override suspend fun members(
        id: ListId,
        expansions: List<com.sorrowblue.twitlin.api.v2.users.Expansion>?,
        tweetField: List<TweetField>?,
        userFields: List<UserField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<User>> {
        return client.get(
            "${Endpoints.LISTS}/${id.id}/members",
            Response.serializer(PagingData.serializer(User.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetField?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }
    }

    override suspend fun followers(
        id: ListId,
        expansions: List<com.sorrowblue.twitlin.api.v2.users.Expansion>?,
        tweetField: List<TweetField>?,
        userFields: List<UserField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<User>> {
        return client.get(
            "${Endpoints.LISTS}/${id.id}/followers",
            Response.serializer(PagingData.serializer(User.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetField?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }
    }

    override suspend fun addMember(id: ListId, userId: UserId): Response<Boolean> {
        return client.post(
            "${Endpoints.LISTS}/${id.id}/members",
            Response.serializer(DataResponse.serializer(ListMemberResponse.serializer()))
        ) {
            contentType(ContentType.Application.Json)
            body = ListMemberRequest(userId)
        }.convertData { it.data.isMember }
    }

    override suspend fun removeMember(id: ListId, userId: UserId): Response<Boolean> {
        return client.delete(
            "${Endpoints.LISTS}/${id.id}/members/${userId.id}",
            Response.serializer(DataResponse.serializer(ListMemberResponse.serializer()))
        ).convertData { it.data.isMember }
    }

    override suspend fun create(name: String, description: String, isPrivate: Boolean): Response<OptionalData<Lists>> {
        return client.post(Endpoints.LISTS, Response.serializer(OptionalData.serializer(com.sorrowblue.twitlin.api.v2.objects.List.serializer()))) {
            contentType(ContentType.Application.Json)
            body = ListRequest(name, description, isPrivate)
        }
    }

    override suspend fun update(
        id: ListId,
        name: String,
        description: String,
        isPrivate: Boolean
    ): Response<Boolean> {
        return client.put(
            "${Endpoints.LISTS}/${id.id}",
            Response.serializer(ListUpdatedResponse.serializer())
        ) {
            contentType(ContentType.Application.Json)
            body = ListRequest(name, description, isPrivate)
        }.convertData { it.updated }
    }

    override suspend fun delete(id: ListId): Response<Boolean> {
        return client.delete(
            "${Endpoints.LISTS}/${id.id}",
            Response.serializer(DataResponse.serializer(ListDeletedResponse.serializer()))
        )
            .convertData { it.data.deleted }
    }
}
