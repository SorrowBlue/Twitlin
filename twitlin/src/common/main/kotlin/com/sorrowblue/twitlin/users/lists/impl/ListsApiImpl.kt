package com.sorrowblue.twitlin.users.lists.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.objects.ListId
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.users.PagingUser
import com.sorrowblue.twitlin.users.lists.ListsApi
import com.sorrowblue.twitlin.users.lists.PagingUserList
import com.sorrowblue.twitlin.users.lists.UserList
import com.sorrowblue.twitlin.utils.API_LISTS
import io.ktor.client.request.parameter
import io.ktor.http.Parameters
import kotlinx.serialization.builtins.ListSerializer

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class ListsApiImpl(private val client: TwitterClient) : ListsApi {

    override suspend fun list(userId: UserId, reverse: Boolean): Response<List<UserList>> =
        listImpl(userId = userId, reverse = reverse)

    override suspend fun list(screenName: String, reverse: Boolean): Response<List<UserList>> =
        listImpl(screenName = screenName, reverse = reverse)

    private suspend fun listImpl(
        userId: UserId? = null,
        screenName: String? = null,
        reverse: Boolean
    ): Response<List<UserList>> {
        return client.get("$API_LISTS/list.json", Response.serializer(ListSerializer(UserList.serializer()))) {
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
            parameter("reverse", reverse)
        }
    }

    override suspend fun members(
        listId: ListId,
        count: Int,
        cursor: String,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<PagingUser> {
        return membersImpl(
            listId = listId,
            count = count,
            cursor = cursor,
            includeEntities = includeEntities,
            skipStatus = skipStatus
        )
    }

    override suspend fun members(
        slug: String,
        ownerId: UserId,
        count: Int,
        cursor: String,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<PagingUser> {
        return membersImpl(
            slug = slug,
            ownerId = ownerId,
            count = count,
            cursor = cursor,
            includeEntities = includeEntities,
            skipStatus = skipStatus
        )
    }

    override suspend fun members(
        slug: String,
        ownerScreenName: String,
        count: Int,
        cursor: String,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<PagingUser> {
        return members(
            slug = slug,
            ownerScreenName = ownerScreenName,
            count = count,
            cursor = cursor,
            includeEntities = includeEntities,
            skipStatus = skipStatus
        )
    }

    private suspend fun membersImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerId: UserId? = null,
        ownerScreenName: String? = null,
        count: Int,
        cursor: String,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<PagingUser> {
        return client.get("$API_LISTS/members.json", Response.serializer(PagingUser.serializer())) {
            parameter("list_id", listId?.id)
            parameter("slug", slug)
            parameter("owner_screen_name", ownerScreenName)
            parameter("owner_id", ownerId)
            parameter("count", count)
            parameter("cursor", cursor)
            parameter("include_entities", includeEntities)
            parameter("skip_status", skipStatus)
        }
    }

    override suspend fun showMember(
        listId: ListId,
        userId: UserId?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        return showMemberImpl(
            listId = listId,
            userId = userId,
            screenName = screenName,
            includeEntities = includeEntities,
            skipStatus = skipStatus
        )
    }

    override suspend fun showMember(
        slug: String,
        ownerId: UserId,
        userId: UserId?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        return showMemberImpl(
            slug = slug,
            ownerId = ownerId,
            userId = userId,
            screenName = screenName,
            includeEntities = includeEntities,
            skipStatus = skipStatus
        )
    }

    override suspend fun showMember(
        slug: String,
        ownerScreenName: String,
        userId: UserId?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        return showMemberImpl(
            slug = slug,
            ownerScreenName = ownerScreenName,
            userId = userId,
            screenName = screenName,
            includeEntities = includeEntities,
            skipStatus = skipStatus
        )
    }

    private suspend fun showMemberImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: UserId? = null,
        userId: UserId?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        require(userId != null || screenName != null) { "Both userId and screenName cannot be null." }
        return client.get("$API_LISTS/members/show.json", Response.serializer(User.serializer())) {
            parameter("list_id", listId?.id)
            parameter("slug", slug)
            parameter("owner_screen_name", ownerScreenName)
            parameter("owner_id", ownerId?.id)
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
            parameter("include_entities", includeEntities)
            parameter("skip_status", skipStatus)
        }
    }

    override suspend fun memberships(
        userId: String?,
        screenName: String?,
        count: Int,
        cursor: String,
        filterToOwnedLists: Boolean
    ): Response<PagingUserList> {
        return client.get("$API_LISTS/memberships.json", Response.serializer(PagingUserList.serializer())) {
            parameter("user_id", userId)
            parameter("screen_name", screenName)
            parameter("count", count)
            parameter("cursor", cursor)
            parameter("filter_to_owned_lists", filterToOwnedLists)
        }
    }

    override suspend fun ownerships(
        userId: String?,
        screenName: String?,
        count: Int,
        cursor: String
    ): Response<PagingUserList> {
        return client.get("$API_LISTS/ownerships.json", Response.serializer(PagingUserList.serializer())) {
            parameter("user_id", userId)
            parameter("screen_name", screenName)
            parameter("count", count)
            parameter("cursor", cursor)
        }
    }

    override suspend fun show(
        listId: ListId?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?
    ): Response<UserList> {
        return client.get("$API_LISTS/show.json", Response.serializer(UserList.serializer())) {
            parameter("list_id", listId?.id)
            parameter("slug", slug)
            parameter("owner_screen_name", ownerScreenName)
            parameter("owner_id", ownerId)
        }
    }

    override suspend fun statuses(
        listId: ListId?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        sinceId: String?,
        maxId: String?,
        count: Int,
        includeEntities: Boolean,
        includeRts: Boolean
    ): Response<List<Tweet>> {
        return client.get("$API_LISTS/statuses.json", Response.serializer(ListSerializer(Tweet.serializer()))) {
            parameter("list_id", listId?.id)
            parameter("slug", slug)
            parameter("owner_screen_name", ownerScreenName)
            parameter("owner_id", ownerId)
            parameter("since_id", sinceId)
            parameter("max_id", maxId)
            parameter("count", count)
            parameter("include_entities", includeEntities)
            parameter("include_rts", includeRts)
        }
    }

    override suspend fun subscribers(
        listId: ListId?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        count: Int,
        cursor: String,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<PagingUser> {
        return client.get("$API_LISTS/subscribers.json", Response.serializer(PagingUser.serializer())) {
            parameter("list_id", listId?.id)
            parameter("slug", slug)
            parameter("owner_screen_name", ownerScreenName)
            parameter("owner_id", ownerId)
            parameter("count", count)
            parameter("cursor", cursor)
            parameter("include_entities", includeEntities)
            parameter("skip_status", skipStatus)
        }
    }

    override suspend fun showSubscribers(
        listId: ListId?,
        slug: String?,
        ownerScreenName: String?,
        ownerId: String?,
        userId: String?,
        screenName: String?,
        includeEntities: Boolean,
        skipStatus: Boolean
    ): Response<User> {
        return client.get("$API_LISTS/subscribers/show.json", Response.serializer(User.serializer())) {
            parameter("list_id", listId?.id)
            parameter("slug", slug)
            parameter("owner_screen_name", ownerScreenName)
            parameter("owner_id", ownerId)
            parameter("user_id", userId)
            parameter("screen_name", screenName)
            parameter("include_entities", includeEntities)
            parameter("skip_status", skipStatus)
        }
    }

    override suspend fun subscriptions(
        userId: String?,
        screenName: String?,
        count: Int,
        cursor: String
    ): Response<PagingUserList> {
        return client.get("$API_LISTS/subscriptions.json", Response.serializer(PagingUserList.serializer())) {
            parameter("user_id", userId)
            parameter("screen_name", screenName)
            parameter("count", count)
            parameter("cursor", cursor)
        }
    }

    override suspend fun create(
        name: String,
        mode: UserList.Mode,
        description: String?
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/create.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                append("name", name)
                append("mode", mode.name.lowercase())
                description?.let { append("description", it) }
            })
    }

    override suspend fun destroy(listId: ListId): Response<UserList> {
        return destroyImpl(listId = listId)
    }

    override suspend fun destroy(slug: String, ownerScreenName: String?, ownerId: String?): Response<UserList> {
        return destroyImpl(slug = slug, ownerScreenName = ownerScreenName, ownerId = ownerId)
    }

    private suspend fun destroyImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/destroy.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                listId?.let { append("list_id", it.id) }
                slug?.let { append("slug", it) }
                ownerScreenName?.let { append("owner_screen_name", it) }
                ownerId?.let { append("owner_id", it) }
            })
    }

    override suspend fun createMembers(
        listId: ListId,
        userId: String?,
        screenName: String?
    ): Response<UserList> {
        return createMembersImpl(listId = listId, userId = userId, screenName = screenName)
    }

    override suspend fun createMembers(
        slug: String,
        ownerScreenName: String?,
        ownerId: String?,
        userId: String?,
        screenName: String?
    ): Response<UserList> {
        return createMembersImpl(
            slug = slug,
            ownerScreenName = ownerScreenName,
            ownerId = ownerId,
            userId = userId,
            screenName = screenName
        )
    }

    private suspend fun createMembersImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userId: String?,
        screenName: String?
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/members/create.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                listId?.let { append("list_id", it.id) }
                slug?.let { append("slug", it) }
                ownerScreenName?.let { append("owner_screen_name", it) }
                ownerId?.let { append("owner_id", it) }
                userId?.let { append("user_id", it) }
                screenName?.let { append("screen_name", it) }
            })
    }

    override suspend fun createAllMembers(
        listId: ListId,
        userIds: List<String>?,
        screenNames: List<String>?
    ): Response<UserList> = createAllMembersImpl(listId = listId, userIds = userIds, screenNames = screenNames)

    override suspend fun createAllMembers(
        slug: String,
        ownerScreenName: String?,
        ownerId: String?,
        userIds: List<String>?,
        screenNames: List<String>?
    ): Response<UserList> {
        return createAllMembersImpl(
            slug = slug,
            ownerScreenName = ownerScreenName,
            ownerId = ownerId,
            userIds = userIds,
            screenNames = screenNames
        )
    }

    private suspend fun createAllMembersImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userIds: List<String>?,
        screenNames: List<String>?
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/members/create_all.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                listId?.let { append("list_id", it.id) }
                slug?.let { append("slug", it) }
                ownerScreenName?.let { append("owner_screen_name", it) }
                ownerId?.let { append("owner_id", it) }
                userIds?.joinToString(",")?.let { append("user_id", it) }
                screenNames?.joinToString(",")?.let { append("screen_name", it) }
            })
    }

    override suspend fun destroyMembers(listId: ListId, userId: String?, screenName: String?): Response<UserList> {
        return destroyMembersImpl(listId = listId, userId = userId, screenName = screenName)
    }

    override suspend fun destroyMembers(
        slug: String,
        ownerScreenName: String?,
        ownerId: String?,
        userId: String?,
        screenName: String?
    ): Response<UserList> {
        return destroyMembersImpl(
            slug = slug,
            ownerScreenName = ownerScreenName,
            ownerId = ownerId,
            userId = userId,
            screenName = screenName
        )
    }

    private suspend fun destroyMembersImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userId: String?,
        screenName: String?
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/members/destroy.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                listId?.let { append("list_id", it.id) }
                slug?.let { append("slug", it) }
                ownerScreenName?.let { append("owner_screen_name", it) }
                ownerId?.let { append("owner_id", it) }
                userId?.let { append("user_id", it) }
                screenName?.let { append("screen_name", it) }
            })
    }

    override suspend fun destroyAllMembers(
        listId: ListId,
        userIds: List<String>?,
        screenNames: List<String>?
    ): Response<UserList> {
        return destroyAllMembersImpl(listId = listId, userIds = userIds, screenNames = screenNames)
    }

    override suspend fun destroyAllMembers(
        slug: String,
        ownerScreenName: String?,
        ownerId: String?,
        userIds: List<String>?,
        screenNames: List<String>?
    ): Response<UserList> {
        return destroyAllMembersImpl(
            slug = slug,
            ownerScreenName = ownerScreenName,
            ownerId = ownerId,
            userIds = userIds,
            screenNames = screenNames
        )
    }

    private suspend fun destroyAllMembersImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        userIds: List<String>?,
        screenNames: List<String>?
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/members/destroy_all.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                listId?.let { append("list_id", it.id) }
                slug?.let { append("slug", it) }
                ownerScreenName?.let { append("owner_screen_name", it) }
                ownerId?.let { append("owner_id", it) }
                userIds?.joinToString(",")?.let { append("user_id", it) }
                screenNames?.joinToString(",")?.let { append("screen_name", it) }
            })
    }

    override suspend fun createSubscribers(listId: ListId): Response<UserList> {
        return createSubscribersImpl(listId = listId)
    }

    override suspend fun createSubscribers(
        slug: String,
        ownerScreenName: String?,
        ownerId: String?
    ): Response<UserList> {
        return createSubscribersImpl(slug = slug, ownerScreenName = ownerScreenName, ownerId = ownerId)
    }

    private suspend fun createSubscribersImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/subscribers/create.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                listId?.let { append("list_id", it.id) }
                slug?.let { append("slug", it) }
                ownerScreenName?.let { append("owner_screen_name", it) }
                ownerId?.let { append("owner_id", it) }
            })
    }

    override suspend fun destroySubscribers(listId: ListId): Response<UserList> {
        return destroySubscribersImpl(listId = listId)
    }

    override suspend fun destroySubscribers(
        slug: String,
        ownerScreenName: String?,
        ownerId: String?
    ): Response<UserList> {
        return destroySubscribersImpl(slug = slug, ownerScreenName = ownerScreenName, ownerId = ownerId)
    }

    private suspend fun destroySubscribersImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/subscribers/destroy.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                listId?.let { append("list_id", it.id) }
                slug?.let { append("slug", it) }
                ownerScreenName?.let { append("owner_screen_name", it) }
                ownerId?.let { append("owner_id", it) }
            })
    }

    override suspend fun update(
        listId: ListId,
        name: String?,
        mode: UserList.Mode?,
        description: String?
    ): Response<UserList> {
        return updateImpl(listId = listId, name = name, mode = mode, description = description)
    }

    override suspend fun update(
        slug: String,
        ownerScreenName: String?,
        ownerId: String?,
        name: String?,
        mode: UserList.Mode?,
        description: String?
    ): Response<UserList> {
        return updateImpl(
            slug = slug,
            ownerScreenName = ownerScreenName,
            ownerId = ownerId,
            name = name,
            mode = mode,
            description = description
        )
    }

    private suspend fun updateImpl(
        listId: ListId? = null,
        slug: String? = null,
        ownerScreenName: String? = null,
        ownerId: String? = null,
        name: String?,
        mode: UserList.Mode?,
        description: String?
    ): Response<UserList> {
        return client.submitForm(
            "$API_LISTS/update.json",
            Response.serializer(UserList.serializer()),
            Parameters.build {
                listId?.let { append("list_id", it.id) }
                slug?.let { append("slug", it) }
                ownerScreenName?.let { append("owner_screen_name", it) }
                ownerId?.let { append("owner_id", it) }
                name?.let { append("name", it) }
                mode?.let { append("mode", it.name.lowercase()) }
                description?.let { append("description", it) }
            })
    }
}
