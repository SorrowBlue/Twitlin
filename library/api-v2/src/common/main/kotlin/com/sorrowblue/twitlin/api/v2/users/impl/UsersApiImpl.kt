package com.sorrowblue.twitlin.api.v2.users.impl

import com.sorrowblue.twitlin.api.v2.Endpoints.USERS
import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.ListField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.field.toParameter
import com.sorrowblue.twitlin.api.v2.lists.impl.DataResponse
import com.sorrowblue.twitlin.api.v2.lists.impl.ListFollowOrPinRequest
import com.sorrowblue.twitlin.api.v2.lists.impl.ListFollowResponse
import com.sorrowblue.twitlin.api.v2.lists.impl.ListPinResponse
import com.sorrowblue.twitlin.api.v2.objects.OptionalData
import com.sorrowblue.twitlin.api.v2.objects.OptionalListData
import com.sorrowblue.twitlin.api.v2.objects.PagingData
import com.sorrowblue.twitlin.api.v2.objects.User
import com.sorrowblue.twitlin.api.v2.users.Expansion
import com.sorrowblue.twitlin.api.v2.users.Following
import com.sorrowblue.twitlin.api.v2.users.UsersApi
import com.sorrowblue.twitlin.api.v2.users.request.BlockingRequest
import com.sorrowblue.twitlin.api.v2.users.request.TargetUserIdRequest
import com.sorrowblue.twitlin.api.v2.users.response.BlockingResponse
import com.sorrowblue.twitlin.api.v2.users.response.FollowingResponse
import com.sorrowblue.twitlin.api.v2.users.response.UnFollowingResponse
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.ListId
import com.sorrowblue.twitlin.core.objects.UserId
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlin.collections.List
import kotlinx.serialization.builtins.ListSerializer
import com.sorrowblue.twitlin.api.v2.lists.Expansion as ListsExpansion
import com.sorrowblue.twitlin.api.v2.objects.List as Lists

internal class UsersApiImpl(private val client: TwitterClient) : UsersApi {

    override suspend fun unBlocking(sourceUserId: UserId, targetUserId: UserId): Response<Boolean> {
        return client.delete(
            "$USERS/${sourceUserId.id}/blocking/${targetUserId.id}",
            Response.serializer(BlockingResponse.serializer())
        ).convertData { it.data.blocking }
    }

    override suspend fun blocking(
        id: UserId,
        maxResults: Int,
        paginationToken: String?,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<PagingData<User>> {
        return client.get("$USERS/${id.id}/blocking", Response.serializer(PagingData.serializer(User.serializer()))) {
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun blocking(id: UserId, targetUserId: UserId): Response<Boolean> {
        return client.post("$USERS/${id.id}/blocking", Response.serializer(BlockingResponse.serializer())) {
            contentType(ContentType.Application.Json)
            setBody(BlockingRequest(targetUserId))
        }.convertData { it.data.blocking }
    }

    override suspend fun unFollowing(sourceUserId: UserId, targetUserId: UserId): Response<Boolean> {
        return client.delete(
            "$USERS/${sourceUserId.id}/following/${targetUserId.id}",
            Response.serializer(UnFollowingResponse.serializer())
        ).convertData { it.data.following }
    }

    override suspend fun followers(
        id: UserId,
        maxResults: Int,
        paginationToken: String?,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<User>> {
        return client.get("$USERS/${id.id}/followers", Response.serializer(PagingData.serializer(User.serializer()))) {
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun following(
        id: UserId,
        maxResults: Int,
        paginationToken: String?,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<User>> {
        return client.get("$USERS/${id.id}/following", Response.serializer(PagingData.serializer(User.serializer()))) {
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun following(id: UserId, targetUserId: UserId): Response<Following> {
        return client.post("$USERS/${id.id}/following", Response.serializer(FollowingResponse.serializer())) {
            contentType(ContentType.Application.Json)
            setBody(TargetUserIdRequest(targetUserId))
        }.convertData { it.data }
    }

    override suspend fun unMuting(sourceUserId: UserId, targetUserId: UserId): Response<Boolean> {
        return client.delete(
            "$USERS/${sourceUserId.id}/muting/${targetUserId.id}",
            Response.serializer(MutingResponse.serializer())
        ).convertData { it.data.muting }
    }

    override suspend fun muting(id: UserId, targetUserId: UserId): Response<Boolean> {
        return client.post("$USERS/${id.id}/muting", Response.serializer(MutingResponse.serializer())) {
            contentType(ContentType.Application.Json)
            setBody(TargetUserIdRequest(targetUserId))
        }.convertData { it.data.muting }
    }

    override suspend fun mutingUser(
        id: UserId,
        maxResults: Int,
        paginationToken: String?,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<User>> {
        return client.get("$USERS/${id.id}/muting", Response.serializer(PagingData.serializer(User.serializer()))) {
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun users(
        ids: List<UserId>,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<OptionalListData<User>> {
        return client.get(USERS, Response.serializer(OptionalListData.serializer(User.serializer()))) {
            parameter("ids", ids.joinToString(",", transform = UserId::id))
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun users(
        id: UserId,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?,
    ): Response<OptionalData<User>> {
        return client.get("$USERS/${id.id}", Response.serializer(OptionalData.serializer(User.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun byUsername(
        usernames: List<String>,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<List<User>>> {
        return client.get(
            "$USERS/by",
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        ) {
            parameter("usernames", usernames.joinToString(","))
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun byUsername(
        username: String,
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<User>> {
        return client.get(
            "$USERS/by/username/$username",
            Response.serializer(OptionalData.serializer(User.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun me(
        expansions: List<Expansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<User>> {
        return client.get("$USERS/me", Response.serializer(OptionalData.serializer(User.serializer()))) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun ownedLists(
        id: UserId,
        expansions: List<ListsExpansion>?,
        listField: List<ListField>?,
        userFields: List<UserField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<Lists>> {
        return client.get(
            "$USERS/${id.id}/owned_lists",
            Response.serializer(PagingData.serializer(Lists.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("list.fields", listField?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }
    }

    override suspend fun membershipsList(
        id: UserId,
        expansions: List<ListsExpansion>?,
        listField: List<ListField>?,
        userFields: List<UserField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<Lists>> {
        return client.get(
            "$USERS/${id.id}/list_memberships",
            Response.serializer(PagingData.serializer(Lists.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("list.fields", listField?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }
    }

    override suspend fun followedLists(
        id: UserId,
        expansions: List<com.sorrowblue.twitlin.api.v2.lists.Expansion>?,
        listField: List<ListField>?,
        userFields: List<UserField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<Lists>> {
        return client.get(
            "$USERS/${id.id}/followed_lists",
            Response.serializer(PagingData.serializer(Lists.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("list.fields", listField?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }
    }

    override suspend fun followList(id: UserId, listId: ListId): Response<Boolean> {
        return client.post(
            "$USERS/${id.id}/followed_lists",
            Response.serializer(DataResponse.serializer(ListFollowResponse.serializer()))
        ) {
            contentType(ContentType.Application.Json)
            setBody(ListFollowOrPinRequest(listId))
        }.convertData { it.data.following }
    }

    override suspend fun unFollowList(id: UserId, listId: ListId): Response<Boolean> {
        return client.delete(
            "$USERS/${id.id}/followed_lists/${listId.id}",
            Response.serializer(DataResponse.serializer(ListFollowResponse.serializer()))
        ).convertData { it.data.following }
    }

    override suspend fun pinnedLists(
        id: UserId,
        expansions: List<ListsExpansion>?,
        listField: List<ListField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Lists>> {
        return client.get(
            "$USERS/${id.id}/pinned_lists",
            Response.serializer(PagingData.serializer(Lists.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("list.fields", listField?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun pinList(id: UserId, listId: ListId): Response<Boolean> {
        return client.post(
            "$USERS/${id.id}/pinned_lists",
            Response.serializer(DataResponse.serializer(ListPinResponse.serializer()))
        ) {
            contentType(ContentType.Application.Json)
            setBody(ListFollowOrPinRequest(listId))
        }.convertData { it.data.pinned }
    }

    override suspend fun unPinList(id: UserId, listId: ListId): Response<Boolean> {
        return client.delete(
            "$USERS/${id.id}/pinned_lists/${listId.id}",
            Response.serializer(DataResponse.serializer(ListPinResponse.serializer()))
        ).convertData { it.data.pinned }
    }
}
