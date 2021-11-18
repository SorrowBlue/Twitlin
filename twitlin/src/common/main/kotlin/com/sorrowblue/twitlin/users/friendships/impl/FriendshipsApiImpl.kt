package com.sorrowblue.twitlin.users.friendships.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.objects.toParameter
import com.sorrowblue.twitlin.users.friendships.Friendships
import com.sorrowblue.twitlin.users.friendships.FriendshipsApi
import com.sorrowblue.twitlin.users.friendships.Relationship
import com.sorrowblue.twitlin.utils.API_FRIENDSHIPS
import io.ktor.client.request.parameter
import io.ktor.http.Parameters
import kotlinx.serialization.builtins.ListSerializer

@Suppress("EXPERIMENTAL_API_USAGE_FUTURE_ERROR")
internal class FriendshipsApiImpl(private val client: TwitterClient) : FriendshipsApi {

    override suspend fun incoming(cursor: String): Response<PagingIds<UserId>> {
        return client.get(
            "$API_FRIENDSHIPS/incoming.json",
            Response.serializer(PagingIds.serializer(UserId.serializer()))
        ) {
            parameter("cursor", cursor)
            parameter("stringify_ids", true)
        }
    }

    override suspend fun lookup(userId: List<UserId>): Response<List<Friendships>> = lookupImpl(userId, null)

    override suspend fun lookupByScreenName(screenName: List<String>): Response<List<Friendships>> =
        lookupImpl(null, screenName)

    private suspend fun lookupImpl(userId: List<UserId>?, screenName: List<String>?): Response<List<Friendships>> {
        return client.get("$API_FRIENDSHIPS/lookup.json", Response.serializer(ListSerializer(Friendships.serializer()))) {
            parameter("user_id", userId?.toParameter())
            parameter("screen_name", screenName?.joinToString(","))
        }
    }

    override suspend fun noRetweetsIds(): Response<List<UserId>> {
        return client.get(
            "$API_FRIENDSHIPS/no_retweets/ids.json",
            Response.serializer(ListSerializer(UserId.serializer()))
        ) {
            parameter("stringify_ids", true)
        }
    }

    override suspend fun outgoing(cursor: String): Response<PagingIds<UserId>> {
        return client.get(
            "$API_FRIENDSHIPS/outgoing.json",
            Response.serializer(PagingIds.serializer(UserId.serializer()))
        ) {
            parameter("cursor", cursor)
            parameter("stringify_ids", true)
        }
    }

    override suspend fun show(sourceId: UserId, targetId: UserId): Response<Relationship> =
        showImpl(sourceId, null, targetId, null)

    override suspend fun show(sourceScreenName: String, targetScreenName: String): Response<Relationship> =
        showImpl(null, sourceScreenName, null, targetScreenName)

    private suspend fun showImpl(
        sourceId: UserId?,
        sourceScreenName: String?,
        targetId: UserId?,
        targetScreenName: String?
    ): Response<Relationship> {
        return client.get("$API_FRIENDSHIPS/show.json", Response.serializer(RelationshipDetailResponse.serializer())) {
            parameter("source_id", sourceId?.id)
            parameter("source_screen_name", sourceScreenName)
            parameter("target_id", targetId?.id)
            parameter("target_screen_name", targetScreenName)
        }.convertData(RelationshipDetailResponse::relationship)
    }

    override suspend fun create(userId: UserId, follow: Boolean?): Response<User> = createImpl(userId, null, follow)

    override suspend fun create(screenName: String, follow: Boolean?): Response<User> =
        createImpl(null, screenName, follow)

    private suspend fun createImpl(userId: UserId?, screenName: String?, follow: Boolean?): Response<User> {
        return client.submitForm("$API_FRIENDSHIPS/create.json", Response.serializer(User.serializer()), Parameters.build {
            screenName?.let { append("screen_name", it) }
            userId?.let { append("user_id", it.id) }
            follow?.let { append("follow", it.toString()) }
        })
    }

    override suspend fun destroy(userId: UserId): Response<User> = destroyImpl(userId, null)

    override suspend fun destroy(screenName: String): Response<User> = destroyImpl(null, screenName)

    private suspend fun destroyImpl(userId: UserId?, screenName: String?): Response<User> {
        return client.submitForm(
            "$API_FRIENDSHIPS/destroy.json",
            Response.serializer(User.serializer()), Parameters.build {
                userId?.let { append("user_id", it.id) }
                screenName?.let { append("screen_name", it) }
            })
    }

    override suspend fun update(userId: UserId, device: Boolean?, retweets: Boolean?): Response<Relationship> =
        updateImpl(userId, null, device, retweets)

    override suspend fun update(screenName: String, device: Boolean?, retweets: Boolean?): Response<Relationship> =
        updateImpl(null, screenName, device, retweets)

    private suspend fun updateImpl(
        userId: UserId?,
        screenName: String?,
        device: Boolean?,
        retweets: Boolean?
    ): Response<Relationship> {
        return client.submitForm(
            "$API_FRIENDSHIPS/update.json",
            Response.serializer(RelationshipDetailResponse.serializer()),
            Parameters.build {
                userId?.let { append("user_id", it.id) }
                screenName?.let { append("screen_name", it) }
                device?.let { append("device", it.toString()) }
                retweets?.let { append("retweets", it.toString()) }
            }).convertData(RelationshipDetailResponse::relationship)
    }
}
