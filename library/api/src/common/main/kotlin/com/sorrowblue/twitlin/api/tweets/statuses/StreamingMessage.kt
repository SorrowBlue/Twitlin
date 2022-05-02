package com.sorrowblue.twitlin.api.tweets.statuses

import com.sorrowblue.twitlin.api.tweets.statuses.impl.StreamingMessageSerializer
import com.sorrowblue.twitlin.core.objects.TweetId
import com.sorrowblue.twitlin.core.objects.UserId
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeStringEpochSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(StreamingMessageSerializer::class)
public sealed class StreamingMessage {

    @Serializable
    public data class Tweet(
        val data: com.sorrowblue.twitlin.api.objects.Tweet
    ) : StreamingMessage()

    @Serializable
    public object Empty : StreamingMessage()

    @Serializable
    public data class Limit(
        val track: Int,
        @SerialName("timestamp_ms")
        @Serializable(LocalDateTimeStringEpochSerializer::class)
        val timestampMs: LocalDateTime
    ) : StreamingMessage()

    @Serializable
    public data class Disconnect(
        val code: Int,
        @SerialName("stream_name")
        val streamName: String,
        val reason: String
    ) : StreamingMessage()

    @Serializable
    public data class Warning(
        val code: String,
        val message: String,
        @SerialName("percent_full")
        val percentFull: Int
    ) : StreamingMessage()

    @Serializable
    public data class Delete(
        @SerialName("id_str")
        val id: TweetId,
        @SerialName("user_id_str")
        val userId: UserId
    ) : StreamingMessage()

    @Serializable
    public data class ScrubGeo(
        @SerialName("user_id_str")
        val userId: UserId,
        @SerialName("up_to_status_id_str")
        val upToStatusIdStr: TweetId,
    ) : StreamingMessage()

    @Serializable
    public data class StatusWithheld(
        val id: TweetId,
        val user_id: UserId,
        @SerialName("withheld_in_countries")
        val withheldInCountries: List<String>
    ) : StreamingMessage()

    @Serializable
    public data class UserWithheld(
        val id: UserId,
        @SerialName("withheld_in_countries")
        val withheldInCountries: List<String>
    ) : StreamingMessage()
}
