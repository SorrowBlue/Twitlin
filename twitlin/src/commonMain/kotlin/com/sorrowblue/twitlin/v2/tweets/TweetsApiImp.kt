package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.client.UserClient
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.tweets.request.HiddenRequest
import com.sorrowblue.twitlin.v2.tweets.response.HiddenResponse
import kotlinx.datetime.LocalDateTime

private const val TWEETS = "${Urls.V2}/tweets"
private const val USERS = "${Urls.V2}/users"

internal class TweetsApiImp(private val userClient: UserClient) : TweetsApi {

    override suspend fun tweet(
        id: String,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<Tweet>> {
        return userClient.testGet(
            "$TWEETS/$id",
            "expansions" to expansions?.toParameter(),
            "media.fields" to mediaFields?.toParameter(),
            "place.fields" to placeFields?.toParameter(),
            "poll.fields" to pollFields?.toParameter(),
            "tweet.fields" to tweetFields?.toParameter(),
            "user.fields" to userFields?.toParameter(),
            serializer = Response.serializer(OptionalData.serializer(Tweet.serializer()))
        )
    }

    override suspend fun tweet(
        ids: List<String>,
        expansions: List<Expansion>?,
        mediaFields: List<MediaField>?,
        placeFields: List<PlaceField>?,
        pollFields: List<PollField>?,
        tweetFields: List<TweetField>?,
        userFields: List<UserField>?
    ): Response<OptionalData<List<Tweet>>> = userClient.get(
        TWEETS,
        "ids" to ids.joinToString(","),
        "expansions" to expansions?.toParameter(),
        "media.fields" to mediaFields?.toParameter(),
        "place.fields" to placeFields?.toParameter(),
        "poll.fields" to pollFields?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter(),
    )

    override suspend fun hidden(id: String, isHidden: Boolean): Response<Boolean> =
        userClient.putJson<HiddenResponse, HiddenRequest>(
            "$TWEETS/$id/hidden",
            HiddenRequest(isHidden)
        ).convertData { it.data.hidden }

    override suspend fun mentions(
        id: String,
        endTime: LocalDateTime,
        startTime: LocalDateTime,
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
    ): Response<PagingTweet> = TODO()
    /*userClient.get(
        "$USERS/$id/mentions",
        "endTime" to endTime.toInstant(TimeZone.UTC),
        startTime: LocalDateTime,
        maxResults: Int,
        paginationToken: String?,
    sinceId: String?,
    untilId: String?,
        "expansions" to expansions?.toParameter(),
        "media.fields" to mediaFields?.toParameter(),
        "place.fields" to placeFields?.toParameter(),
        "poll.fields" to pollFields?.toParameter(),
        "tweet.fields" to tweetFields?.toParameter(),
        "user.fields" to userFields?.toParameter(),
    )*/
}

public fun List<Field>.toParameter(): String = joinToString(",") { it.value }

public interface Field {
    public val value: String
}

public enum class Expansion(override val value: String) : Field {
    AUTHOR_ID("author_id"),
    REFERENCED_TWEETS_ID("referenced_tweets.id"),
    IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
    ATTACHMENTS_MEDIA_KEYS("attachments.media_keys"),
    ATTACHMENTS_POLL_IDS("attachments.poll_ids"),
    GEO_PLACE_ID("geo.place_id"),
    ENTITIES_MENTIONS_USERNAME("entities.mentions.username"),
    REFERENCED_TWEETS_ID_AUTHOR_ID("referenced_tweets.id.author_id");

    public companion object {
        public fun all(): List<Expansion> = listOf(
            AUTHOR_ID,
            REFERENCED_TWEETS_ID,
            IN_REPLY_TO_USER_ID,
            ATTACHMENTS_MEDIA_KEYS,
            ATTACHMENTS_POLL_IDS,
            GEO_PLACE_ID,
            ENTITIES_MENTIONS_USERNAME,
            REFERENCED_TWEETS_ID_AUTHOR_ID,
        )
    }
}

public enum class MediaField(override val value: String) : Field {
    DURATION_MS("duration_ms"),
    HEIGHT("height"),
    MEDIA_KEY("media_key"),
    PREVIEW_IMAGE_URL("preview_image_url"),
    TYPE("type"),
    URL("url"),
    WIDTH("width"),
    PUBLIC_METRICS("public_metrics"),
    NON_PUBLIC_METRICS("non_public_metrics"),
    ORGANIC_METRICS("organic_metrics"),
    PROMOTED_METRICS("promoted_metrics");

    public companion object {
        public fun all(): List<MediaField> = listOf(
            DURATION_MS, HEIGHT,
            MEDIA_KEY,
            PREVIEW_IMAGE_URL,
            TYPE,
            URL,
            WIDTH,
            PUBLIC_METRICS,
            NON_PUBLIC_METRICS,
            ORGANIC_METRICS,
            PROMOTED_METRICS
        )
    }
}

public enum class PlaceField(override val value: String) : Field {
    CONTAINED_WITHIN("contained_within"),
    COOUNTRY("country"),
    COUNTRY_CODE("country_code"),
    FULL_NAME("full_name"),
    GEO("geo"),
    ID("id"),
    NAME("name"),
    PLACE_TYPE("place_type");

    public companion object {
        public fun all(): List<PlaceField> = listOf(
            CONTAINED_WITHIN,
            COOUNTRY,
            COUNTRY_CODE,
            FULL_NAME,
            GEO,
            ID,
            NAME,
            PLACE_TYPE
        )
    }
}

public enum class PollField(override val value: String) : Field {
    DURATION_MINUTES("duration_minutes"),
    END_DATETIME("end_datetime"),
    ID("id"),
    OPTIONS("options"),
    VOTING_STATUS("voting_status");

    public companion object {
        public fun all(): List<PollField> = listOf(
            DURATION_MINUTES,
            END_DATETIME,
            ID,
            OPTIONS,
            VOTING_STATUS
        )
    }
}

public enum class TweetField(override val value: String) : Field {
    ATTACHMENTS("attachments"),
    AUTHOR_ID("author_id"),
    CONTEXT_ANNOTATIONS("context_annotations"),
    CONVERSATION_ID("conversation_id"),
    CREATED_AT("created_at"),
    ENTITIES("entities"),
    GEO("geo"),
    ID("id"),
    IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
    LANG("lang"),
    NON_PUBLIC_METRICS("non_public_metrics"),
    PUBLIC_METRICS("public_metrics"),
    ORGANIC_METRICS("organic_metrics"),
    PROMOTED_METRICS("promoted_metrics"),
    POSSIBLY_SENSITIVE("possibly_sensitive"),
    REFERENCED_TWEETS("referenced_tweets"),
    SOURCE("source"),
    TEXT("text"),
    WITHHELD("withheld");

    public companion object {
        public fun all(): List<TweetField> = listOf(
            ATTACHMENTS,
            AUTHOR_ID,
            CONTEXT_ANNOTATIONS,
            CONVERSATION_ID,
            CREATED_AT,
            ENTITIES,
            GEO,
            ID,
            IN_REPLY_TO_USER_ID,
            LANG,
            NON_PUBLIC_METRICS,
            PUBLIC_METRICS,
            ORGANIC_METRICS,
            PROMOTED_METRICS,
            POSSIBLY_SENSITIVE,
            REFERENCED_TWEETS,
            SOURCE,
            TEXT,
            WITHHELD
        )
    }
}

public enum class UserField(override val value: String) : Field {
    CREATED_AT("created_at"),
    DESCRIPTION("description"),
    ENTITIES("entities"),
    ID("id"),
    LOCATION("location"),
    NAME("name"),
    PINNED_TWEET_ID("pinned_tweet_id"),
    PROFILE_IMAGE_URL("profile_image_url"),
    PROTECTED("protected"),
    PUBLIC_METRICS("public_metrics"),
    URL("url"),
    USERNAME("username"),
    VERIFIED("verified"),
    WITHHELD("withheld"),
    ;

    public companion object {
        public fun all(): List<UserField> = listOf(
            CREATED_AT,
            DESCRIPTION,
            ENTITIES,
            ID,
            LOCATION,
            NAME,
            PINNED_TWEET_ID,
            PROFILE_IMAGE_URL,
            PROTECTED,
            PUBLIC_METRICS,
            URL,
            USERNAME,
            VERIFIED,
            WITHHELD
        )
    }
}
