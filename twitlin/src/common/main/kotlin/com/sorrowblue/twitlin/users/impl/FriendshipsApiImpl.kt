/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.users.FriendshipsApi
import com.sorrowblue.twitlin.users.Relationship
import com.sorrowblue.twitlin.users.RelationshipDetail
import com.sorrowblue.twitlin.users.response.RelationshipDetailResponse
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

private const val FRIENDSHIPS = "${Urls.V1}/friendships"

internal class FriendshipsApiImpl(private val client: UserClient) : FriendshipsApi {
    override suspend fun incoming(cursor: String): Response<PagingIds> {
        return client.get(
            "$FRIENDSHIPS/incoming.json",
            Response.serializer(PagingIds.serializer()),
            "cursor" to cursor,
            "stringify_ids" to true
        )
    }

    override suspend fun lookup(
        screenName: List<String>?,
        userId: List<String>?
    ): Response<List<Relationship>> {
        return client.get(
            "$FRIENDSHIPS/lookup.json",
            Response.serializer(ListSerializer(Relationship.serializer())),
            "screen_name" to screenName?.joinToString(","),
            "user_id" to userId?.joinToString(",")
        )
    }

    override suspend fun noRetweetsIds(): Response<List<String>> {
        return client.get(
            "$FRIENDSHIPS/no_retweets/ids.json",
            Response.serializer(ListSerializer(String.serializer())),
            "stringify_ids" to true
        )
    }

    override suspend fun outgoing(cursor: String): Response<PagingIds> {
        return client.get(
            "$FRIENDSHIPS/outgoing.json",
            Response.serializer(PagingIds.serializer()),
            "cursor" to cursor,
            "stringify_ids" to true
        )
    }

    override suspend fun show(
        sourceId: String?,
        sourceScreenName: String?,
        targetId: String?,
        targetScreenName: String?
    ): Response<RelationshipDetail> {
        return client.get(
            "$FRIENDSHIPS/show.json",
            Response.serializer(RelationshipDetailResponse.serializer()),
            "source_id" to sourceId,
            "source_screen_name" to sourceScreenName,
            "target_id" to targetId,
            "target_screen_name" to targetScreenName
        ).convertData(RelationshipDetailResponse::relationship)
    }

    override suspend fun create(
        screenName: String?,
        userId: String?,
        follow: Boolean?
    ): Response<User> {
        return client.post(
            "$FRIENDSHIPS/create.json",
            Response.serializer(User.serializer()),
            "screen_name" to screenName,
            "user_id" to userId,
            "follow" to follow
        )
    }

    override suspend fun destroy(screenName: String?, userId: String?): Response<User> {
        return client.post(
            "$FRIENDSHIPS/destroy.json",
            Response.serializer(User.serializer()),
            "screen_name" to screenName,
            "user_id" to userId
        )
    }

    override suspend fun update(
        screenName: String?,
        userId: String?,
        device: Boolean?,
        retweets: Boolean?
    ): Response<RelationshipDetail> {
        return client.post(
            "$FRIENDSHIPS/update.json",
            Response.serializer(RelationshipDetailResponse.serializer()),
            "screen_name" to screenName,
            "user_id" to userId,
            "device" to device,
            "retweets" to retweets
        ).convertData(RelationshipDetailResponse::relationship)
    }
}
