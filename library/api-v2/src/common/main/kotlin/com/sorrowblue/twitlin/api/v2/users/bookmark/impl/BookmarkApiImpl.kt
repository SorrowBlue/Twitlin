package com.sorrowblue.twitlin.api.v2.users.bookmark.impl

import com.sorrowblue.twitlin.api.v2.Endpoints.USERS
import com.sorrowblue.twitlin.api.v2.client.Response
import com.sorrowblue.twitlin.api.v2.field.MediaField
import com.sorrowblue.twitlin.api.v2.field.PlaceField
import com.sorrowblue.twitlin.api.v2.field.PollField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.field.toParameter
import com.sorrowblue.twitlin.api.v2.objects.PagingData
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.users.Expansion
import com.sorrowblue.twitlin.api.v2.users.bookmark.BookmarkApi
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.TweetId
import com.sorrowblue.twitlin.core.objects.UserId
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

@Serializable
internal class BookmarkResponse(val data: Data) {

    @Serializable
    class Data(val bookmarked: Boolean)
}

@Serializable
internal class BookmarkRequest(val tweet_id: TweetId)

internal class BookmarkApiImpl(private val twitterClient: TwitterClient) : BookmarkApi {
    override suspend fun remove(id: UserId, tweetId: TweetId): Response<Boolean> {
        return twitterClient.delete(
            "$USERS/${id.id}/bookmarks/${tweetId.id}",
            Response.serializer(BookmarkResponse.serializer())
        ).convertData { it.data.bookmarked }
    }

    override suspend fun add(id: UserId, tweetId: TweetId): Response<Boolean> {
        return twitterClient.post(
            "$USERS/${id.id}/bookmarks",
            Response.serializer(BookmarkResponse.serializer())
        ) {
            contentType(ContentType.Application.Json)
            setBody(BookmarkRequest(tweetId))
        }.convertData { it.data.bookmarked }
    }

    override suspend fun get(
        id: UserId,
        maxResult: Int,
        paginationToken: String?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> {
        return twitterClient.get(
            USERS + "/${id.id}/bookmarks",
            Response.serializer(PagingData.serializer(Tweet.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResult)
            parameter("pagination_token", paginationToken)
        }
    }
}
