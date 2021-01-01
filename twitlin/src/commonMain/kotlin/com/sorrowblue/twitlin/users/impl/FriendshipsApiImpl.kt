/*
 * (c) 2020.
 */

/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.users.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.objects.TwitterUser
import com.sorrowblue.twitlin.users.FriendshipsApi
import com.sorrowblue.twitlin.users.PagingIds
import com.sorrowblue.twitlin.users.Relationship
import com.sorrowblue.twitlin.users.RelationshipDetail

private const val FRIENDSHIPS = "${Urls.V1}/friendships"

internal class FriendshipsApiImpl(private val client: TwitlinClient) : FriendshipsApi {

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
    ): Response<RelationshipDetail> = client.getCustom(
        "$FRIENDSHIPS/show.json",
        "source_id" to sourceId,
        "source_screen_name" to sourceScreenName,
        "target_id" to targetId,
        "target_screen_name" to targetScreenName,
        converter = RelationshipDetailResult.Companion::onSuccess
    )

    override suspend fun create(
        screenName: String?,
        userId: String?,
        follow: Boolean?
    ): Response<TwitterUser> = client.post(
        "$FRIENDSHIPS/create.json",
        "screen_name" to screenName,
        "user_id" to userId,
        "follow" to follow
    )

    override suspend fun destroy(screenName: String?, userId: String?): Response<TwitterUser> =
        client.post("$FRIENDSHIPS/destroy.json", "screen_name" to screenName, "user_id" to userId)

    override suspend fun update(
        screenName: String?,
        userId: String?,
        device: Boolean?,
        retweets: Boolean?
    ): Response<RelationshipDetail> = client.postCustom(
        "$FRIENDSHIPS/update.json",
        "screen_name" to screenName,
        "user_id" to userId,
        "device" to device,
        "retweets" to retweets,
        converter = RelationshipDetailResult.Companion::onSuccess
    )
}
