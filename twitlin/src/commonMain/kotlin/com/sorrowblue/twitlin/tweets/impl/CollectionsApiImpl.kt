/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.tweets.CollectionChange
import com.sorrowblue.twitlin.tweets.CollectionDestroy
import com.sorrowblue.twitlin.tweets.CollectionObjects
import com.sorrowblue.twitlin.tweets.CollectionResponse
import com.sorrowblue.twitlin.tweets.Collections
import com.sorrowblue.twitlin.tweets.CollectionsApi
import com.sorrowblue.twitlin.tweets.TimelineOrder
import com.sorrowblue.twitlin.tweets.request.CurateEntriesRequest

private const val COLLECTIONS = "${Urls.V1}/collections"

internal class CollectionsApiImpl(private val client: UserClient) : CollectionsApi {

    override suspend fun entries(
        id: String,
        count: Int?,
        maxPosition: String?,
        minPosition: String?
    ): Response<Collections<CollectionObjects.HasTweets, CollectionResponse.TimelinePosition>> =
        client.get(
            "$COLLECTIONS/entries.json",
            "id" to id,
            "count" to count,
            "max_position" to maxPosition,
            "min_position" to minPosition
        )

    override suspend fun list(
        userId: String?,
        screenName: String?,
        tweetId: String?,
        count: Int?,
        cursor: String?
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.List>> = client.get(
        "$COLLECTIONS/list.json",
        "user_id" to userId,
        "screen_name" to screenName,
        "tweet_id" to tweetId,
        "count" to count,
        "cursor" to cursor
    )

    override suspend fun show(id: String): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>> =
        client.get("$COLLECTIONS/show.json", "id" to id)

    override suspend fun create(
        name: String,
        description: String?,
        url: String?,
        timelineOrder: TimelineOrder?
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>> =
        client.post(
            "$COLLECTIONS/create.json",
            "name" to name,
            "description" to description,
            "url" to url,
            "timeline_order" to timelineOrder?.name?.toLowerCase()
        )

    override suspend fun destroy(id: String): Response<CollectionDestroy> =
        client.post("$COLLECTIONS/destroy.json", "id" to id)

    override suspend fun update(
        id: String,
        name: String?,
        description: String?,
        url: String?
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>> =
        client.post(
            "$COLLECTIONS/update.json",
            "id" to id,
            "name" to name,
            "description" to description,
            "url" to url
        )

    override suspend fun addEntries(
        id: String,
        tweetId: String,
        relativeTo: String?,
        above: Boolean
    ): Response<Collections<Unit, CollectionResponse.Errors>> = client.post(
        "$COLLECTIONS/entries/add.json",
        "id" to id,
        "tweet_id" to tweetId,
        "relative_to" to relativeTo,
        "above" to above
    )

    override suspend fun curateEntries(
        id: String,
        changes: List<CollectionChange>
    ): Response<Collections<Unit, CollectionResponse.Errors>> = client.postJson(
        "$COLLECTIONS/entries/curate.json",
        clazz = CurateEntriesRequest(id, changes)
    )

    override suspend fun moveEntries(
        id: String,
        tweetId: String,
        relativeTo: String,
        above: Boolean
    ): Response<Collections<Unit, CollectionResponse.Errors>> = client.post(
        "$COLLECTIONS/entries/move.json",
        "id" to id,
        "tweet_id" to tweetId,
        "relative_to" to relativeTo,
        "above" to above
    )

    override suspend fun removeEntries(
        id: String,
        tweetId: String
    ): Response<Collections<Unit, CollectionResponse.Errors>> = client.post(
        "$COLLECTIONS/entries/remove.json",
        "id" to id,
        "tweet_id" to tweetId
    )
}
