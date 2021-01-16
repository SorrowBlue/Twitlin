/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.objects.User
import com.sorrowblue.twitlin.users.FriendshipsApi
import com.sorrowblue.twitlin.users.Relationship
import com.sorrowblue.twitlin.users.RelationshipDetail

private const val FRIENDSHIPS = "${Urls.V1}/friendships"

internal class FriendshipsApiImpl(private val client: UserClient) : FriendshipsApi {

    override suspend fun incoming(cursor: String): Response<PagingIds> = client.get(
        "$FRIENDSHIPS/incoming.json",
        "cursor" to cursor,
        "stringify_ids" to true
    )

    override suspend fun lookup(
        screenName: List<String>?,
        userId: List<String>?
    ): Response<List<Relationship>> = client.get(
        "$FRIENDSHIPS/lookup.json",
        "screen_name" to screenName?.joinToString(","),
        "user_id" to userId?.joinToString(",")
    )

    override suspend fun noRetweetsIds(): Response<List<String>> = client.get(
        "$FRIENDSHIPS/no_retweets/ids.json",
        "stringify_ids" to true
    )

    override suspend fun outgoing(cursor: String): Response<PagingIds> =
        client.get("$FRIENDSHIPS/outgoing.json", "cursor" to cursor, "stringify_ids" to true)

    override suspend fun show(
        sourceId: String?,
        sourceScreenName: String?,
        targetId: String?,
        targetScreenName: String?
    ): Response<RelationshipDetail> = client.get<RelationshipDetailResult>(
        "$FRIENDSHIPS/show.json",
        "source_id" to sourceId,
        "source_screen_name" to sourceScreenName,
        "target_id" to targetId,
        "target_screen_name" to targetScreenName
    ).convertData(RelationshipDetailResult::relationship)

    override suspend fun create(
        screenName: String?,
        userId: String?,
        follow: Boolean?
    ): Response<User> = client.post(
        "$FRIENDSHIPS/create.json",
        "screen_name" to screenName,
        "user_id" to userId,
        "follow" to follow
    )

    override suspend fun destroy(screenName: String?, userId: String?): Response<User> =
        client.post("$FRIENDSHIPS/destroy.json", "screen_name" to screenName, "user_id" to userId)

    override suspend fun update(
        screenName: String?,
        userId: String?,
        device: Boolean?,
        retweets: Boolean?
    ): Response<RelationshipDetail> = client.post<RelationshipDetailResult>(
        "$FRIENDSHIPS/update.json",
        "screen_name" to screenName,
        "user_id" to userId,
        "device" to device,
        "retweets" to retweets
    ).convertData(RelationshipDetailResult::relationship)
}
