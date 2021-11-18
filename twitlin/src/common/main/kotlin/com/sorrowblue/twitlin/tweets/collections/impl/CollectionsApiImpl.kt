package com.sorrowblue.twitlin.tweets.collections.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.objects.CollectionId
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.tweets.collections.CollectionChange
import com.sorrowblue.twitlin.tweets.collections.CollectionDestroy
import com.sorrowblue.twitlin.tweets.collections.CollectionObjects
import com.sorrowblue.twitlin.tweets.collections.CollectionResponse
import com.sorrowblue.twitlin.tweets.collections.Collections
import com.sorrowblue.twitlin.tweets.collections.CollectionsApi
import com.sorrowblue.twitlin.tweets.collections.TimelineOrder
import com.sorrowblue.twitlin.utils.API_COLLECTIONS
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.builtins.serializer

internal class CollectionsApiImpl(private val client: TwitterClient) : CollectionsApi {

    override suspend fun entries(
        id: CollectionId,
        count: Int?,
        maxPosition: String?,
        minPosition: String?
    ): Response<Collections<CollectionObjects.HasTweets, CollectionResponse.TimelinePosition>> {
        val collectionSerializer = Collections.serializer(
            CollectionObjects.HasTweets.serializer(),
            CollectionResponse.TimelinePosition.serializer()
        )
        return client.get("$API_COLLECTIONS/entries.json", Response.serializer(collectionSerializer)) {
            parameter("id", id.id)
            parameter("count", count)
            parameter("max_position", maxPosition)
            parameter("min_position", minPosition)
        }
    }

    override suspend fun list(
        userId: UserId,
        tweetId: TweetId?,
        count: Int,
        cursor: String?
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.List>> =
        listImpl(userId, null, tweetId, count, cursor)

    override suspend fun list(
        screenName: String,
        tweetId: TweetId?,
        count: Int,
        cursor: String?
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.List>> =
        listImpl(null, screenName, tweetId, count, cursor)

    private suspend fun listImpl(
        userId: UserId?,
        screenName: String?,
        tweetId: TweetId?,
        count: Int,
        cursor: String?
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.List>> {
        val collectionSerializer = Collections.serializer(
            CollectionObjects.Default.serializer(),
            CollectionResponse.List.serializer()
        )
        return client.get("$API_COLLECTIONS/list.json", Response.serializer(collectionSerializer)) {
            parameter("user_id", userId)
            parameter("screen_name", screenName)
            parameter("tweet_id", tweetId)
            parameter("count", count)
            parameter("cursor", cursor)
        }
    }

    override suspend fun show(id: CollectionId): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>> {
        return client.get("$API_COLLECTIONS/show.json", Response.serializer(defaultTimelineIdSerializer)) {
            parameter("id", id.id)
        }
    }

    override suspend fun create(
        name: String,
        description: String?,
        url: String?,
        timelineOrder: TimelineOrder?
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>> {
        return client.post("$API_COLLECTIONS/create.json", Response.serializer(defaultTimelineIdSerializer)) {
            parameter("name", name)
            parameter("description", description)
            parameter("url", url)
            parameter("timeline_order", timelineOrder?.name?.lowercase())
        }
    }

    override suspend fun destroy(id: CollectionId): Response<CollectionDestroy> {
        return client.post("$API_COLLECTIONS/destroy.json", Response.serializer(CollectionDestroy.serializer())) {
            parameter("id", id.id)
        }
    }

    override suspend fun addEntries(
        id: CollectionId,
        tweetId: TweetId,
        relativeTo: TweetId?,
        above: Boolean
    ): Response<Collections<Unit, CollectionResponse.Errors>> {
        return client.post("$API_COLLECTIONS/entries/add.json", Response.serializer(unitErrorsSerializer)) {
            parameter("id", id.id)
            parameter("tweet_id", tweetId.id)
            parameter("relative_to", relativeTo?.id)
            parameter("above", above)
        }
    }

    override suspend fun curateEntries(
        id: CollectionId,
        changes: List<CollectionChange>
    ): Response<Collections<Unit, CollectionResponse.Errors>> {
        return client.post("$API_COLLECTIONS/entries/curate.json", Response.serializer(unitErrorsSerializer)) {
            contentType(ContentType.Application.Json)
            body = CreateEntriesRequest(id, changes)
        }
    }

    override suspend fun moveEntries(
        id: CollectionId,
        tweetId: TweetId,
        relativeTo: TweetId,
        above: Boolean
    ): Response<Collections<Unit, CollectionResponse.Errors>> {
        return client.post("$API_COLLECTIONS/entries/move.json", Response.serializer(unitErrorsSerializer)) {
            parameter("id", id.id)
            parameter("tweet_id", tweetId.id)
            parameter("relative_to", relativeTo.id)
            parameter("above", above)
        }
    }

    override suspend fun removeEntries(
        id: CollectionId,
        tweetId: TweetId
    ): Response<Collections<Unit, CollectionResponse.Errors>> {
        return client.post("$API_COLLECTIONS/entries/remove.json", Response.serializer(unitErrorsSerializer)) {
            parameter("id", id.id)
            parameter("tweet_id", tweetId.id)
        }
    }

    override suspend fun update(
        id: CollectionId,
        name: String?,
        description: String?,
        url: String?
    ): Response<Collections<CollectionObjects.Default, CollectionResponse.TimelineId>> {
        return client.post("$API_COLLECTIONS/update.json", Response.serializer(defaultTimelineIdSerializer)) {
            parameter("id", id.id)
            parameter("name", name)
            parameter("description", description)
            parameter("url", url)
        }
    }

    private val unitErrorsSerializer
        get() = Collections.serializer(Unit.serializer(), CollectionResponse.Errors.serializer())

    private val defaultTimelineIdSerializer
        get() = Collections.serializer(
            CollectionObjects.Default.serializer(),
            CollectionResponse.TimelineId.serializer()
        )
}
