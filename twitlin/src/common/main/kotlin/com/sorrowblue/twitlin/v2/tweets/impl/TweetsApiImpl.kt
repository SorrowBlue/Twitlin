package com.sorrowblue.twitlin.v2.tweets.impl

import com.sorrowblue.twitlin.client.TwitterClient
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.v2.Endpoints
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.field.toParameter
import com.sorrowblue.twitlin.v2.objects.OptionalData
import com.sorrowblue.twitlin.v2.objects.OptionalListData
import com.sorrowblue.twitlin.v2.objects.PagingData
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.Expansion
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.tweets.counts.TweetsCountsApi
import com.sorrowblue.twitlin.v2.tweets.counts.impl.TweetsCountsApiImpl
import com.sorrowblue.twitlin.v2.tweets.search.TweetsSearchApi
import com.sorrowblue.twitlin.v2.tweets.search.impl.TweetsSearchApiImpl
import com.sorrowblue.twitlin.v2.users.Exclude
import com.sorrowblue.twitlin.v2.users.request.LikesRequest
import com.sorrowblue.twitlin.v2.users.request.RetweetRequest
import com.sorrowblue.twitlin.v2.users.response.LikesResponse
import com.sorrowblue.twitlin.v2.users.response.RetweetResponse
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.encodeToISOString
import com.sorrowblue.twitlin.v2.users.Expansion as UsersExpansion

internal class TweetsApiImpl(private val client: TwitterClient) : TweetsApi {

    override val search: TweetsSearchApi
        get() = TweetsSearchApiImpl(client)

    override suspend fun hidden(id: TweetId, isHidden: Boolean): Response<Boolean> {
        return client.put("${Endpoints.TWEETS}/${id.id}/hidden", Response.serializer(HiddenResponse.serializer())) {
            contentType(ContentType.Application.Json)
            body = HiddenRequest(isHidden)
        }.convertData { it.data.hidden }
    }

    override suspend fun unLikes(userId: UserId, tweetId: TweetId): Response<Boolean> {
        return client.delete(
            "${Endpoints.USERS}/${userId.id}/likes/${tweetId.id}",
            Response.serializer(LikesResponse.serializer())
        ).convertData { it.data.liked }
    }

    override suspend fun likingUsers(
        id: TweetId,
        expansions: List<UsersExpansion>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<User>> {
        return client.get(
            "${Endpoints.TWEETS}/${id.id}/liking_users",
            Response.serializer(OptionalListData.serializer(User.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun likedTweets(
        id: UserId,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        userFields: List<UserField>?,
        tweetFields: List<TweetField>?,
        pollFields: List<PollField>?,
        maxResults: Int,
        paginationToken: String?
    ): Response<PagingData<Tweet>> {
        return client.get(
            "${Endpoints.USERS}/${id.id}/liked_tweets",
            Response.serializer(PagingData.serializer(Tweet.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
        }
    }

    override suspend fun likes(userId: UserId, tweetId: TweetId): Response<Boolean> {
        return client.post("${Endpoints.USERS}/${userId.id}/likes", Response.serializer(LikesResponse.serializer())) {
            contentType(ContentType.Application.Json)
            body = LikesRequest(tweetId)
        }.convertData { it.data.liked }
    }

    override suspend fun unRetweet(userId: UserId, sourceTweetId: TweetId): Response<Boolean> {
        return client.delete(
            "${Endpoints.USERS}/${userId.id}/retweets/${sourceTweetId.id}",
            Response.serializer(RetweetResponse.serializer())
        ).convertData { it.data.retweeted }
    }

    override suspend fun retweetedBy(
        tweetId: TweetId,
        expansions: List<com.sorrowblue.twitlin.v2.users.Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<User>> {
        return client.get(
            "${Endpoints.TWEETS}/${tweetId.id}/retweeted_by",
            Response.serializer(OptionalListData.serializer(User.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun retweet(userId: UserId, tweetId: TweetId): Response<Boolean> {
        return client.post(
            "${Endpoints.USERS}/${userId.id}/retweets",
            Response.serializer(RetweetResponse.serializer())
        ) {
            contentType(ContentType.Application.Json)
            body = RetweetRequest(tweetId)
        }.convertData { it.data.retweeted }
    }

    override fun sampleStream(
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Flow<Response<OptionalData<Tweet>>> {
        return client.streaming(
            "${Endpoints.TWEETS}/sample/stream",
            Response.serializer(OptionalData.serializer(Tweet.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun mentions(
        id: UserId,
        endTime: LocalDateTime?,
        startTime: LocalDateTime?,
        maxResults: Int,
        paginationToken: String?,
        sinceId: String?,
        untilId: String?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> {
        return client.get(
            "${Endpoints.USERS}/${id.id}/mentions",
            Response.serializer(PagingData.serializer(Tweet.serializer()))
        ) {
            parameter("end_time", endTime?.encodeToISOString())
            parameter("start_time", startTime?.encodeToISOString())
            parameter("max_results", maxResults)
            parameter("expansions", expansions?.toParameter())
            parameter("pagination_token", paginationToken)
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("since_id", sinceId)
            parameter("until_id", untilId)
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun tweets(
        id: UserId,
        endTime: LocalDateTime?,
        startTime: LocalDateTime?,
        exclude: Exclude?,
        maxResults: Int,
        paginationToken: String?,
        sinceId: String?,
        untilId: String?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> {
        return client.get(
            "${Endpoints.USERS}/${id.id}/tweets",
            Response.serializer(PagingData.serializer(Tweet.serializer()))
        ) {
            parameter("end_time", endTime?.encodeToISOString())
            parameter("exclude", exclude?.value)
            parameter("expansions", expansions?.toParameter())
            parameter("max_results", maxResults)
            parameter("media.fields", mediaFields?.toParameter())
            parameter("pagination_token", paginationToken)
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("since_id", sinceId)
            parameter("start_time", startTime?.encodeToISOString())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("until_id", untilId)
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun mentionsByUsername(
        username: String,
        endTime: LocalDateTime?,
        startTime: LocalDateTime?,
        maxResults: Int,
        paginationToken: String?,
        sinceId: String?,
        untilId: String?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> {
        return client.get(
            "${Endpoints.USERS}/by/username/$username/mentions",
            Response.serializer(PagingData.serializer(Tweet.serializer()))
        ) {
            parameter("end_time", endTime?.encodeToISOString())
            parameter("start_time", startTime?.encodeToISOString())
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
            parameter("since_id", sinceId)
            parameter("until_id", untilId)
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun tweetsByUsername(
        username: String,
        endTime: LocalDateTime?,
        startTime: LocalDateTime?,
        exclude: Exclude?,
        maxResults: Int,
        paginationToken: String?,
        sinceId: String?,
        untilId: String?,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<PagingData<Tweet>> {
        return client.get(
            "${Endpoints.USERS}/by/username/$username/tweets",
            Response.serializer(PagingData.serializer(Tweet.serializer()))
        ) {
            parameter("end_time", endTime?.encodeToISOString())
            parameter("start_time", startTime?.encodeToISOString())
            parameter("exclude", exclude?.value)
            parameter("max_results", maxResults)
            parameter("pagination_token", paginationToken)
            parameter("since_id", sinceId)
            parameter("until_id", untilId)
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override val tweetsCountsApi: TweetsCountsApi
        get() = TweetsCountsApiImpl(client)

    override suspend fun tweet(
        id: TweetId,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<Tweet>> {
        return client.get(
            "${Endpoints.TWEETS}/${id.id}",
            Response.serializer(OptionalData.serializer(Tweet.serializer()))
        ) {
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun tweet(
        ids: List<TweetId>,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalListData<Tweet>> {
        return client.get(Endpoints.TWEETS, Response.serializer(OptionalListData.serializer(Tweet.serializer()))) {
            parameter("ids", ids.joinToString(",", transform = TweetId::id))
            parameter("expansions", expansions?.toParameter())
            parameter("media.fields", mediaFields?.toParameter())
            parameter("place.fields", placeFields?.toParameter())
            parameter("poll.fields", pollFields?.toParameter())
            parameter("tweet.fields", tweetFields?.toParameter())
            parameter("user.fields", userFields?.toParameter())
        }
    }

    override suspend fun tweet(
        text: String?,
        forSuperFollowersOnly: Boolean,
        replySettings: Tweet.ReplySettings,
        reply: TweetOption.Reply?,
        quoteTweetId: TweetId?,
        media: TweetOption.Media?,
        poll: TweetOption.Poll?,
        geo: TweetOption.Geo?,
        directMessageUserId: UserId?
    ): Response<OptionalData<Tweet>> {
        return client.post(Endpoints.TWEETS, Response.serializer(OptionalData.serializer(Tweet.serializer()))) {
            contentType(ContentType.Application.Json)
            body = TweetRequest(
                directMessageUserId?.let { "https://twitter.com/messages/compose?recipient_id=${it.id}" },
                forSuperFollowersOnly,
                geo, media, poll,
                quoteTweetId, reply, if (replySettings == Tweet.ReplySettings.EVERYONE) null else replySettings,
                text
            )
        }
    }

    override suspend fun delete(id: TweetId): Response<Boolean> {
        return client.delete(Endpoints.TWEETS + "/${id.id}", Response.serializer(DeleteResponse.serializer()))
            .convertData { it.data.deleted }
    }
}
