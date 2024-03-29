package com.sorrowblue.twitlin.api.tweets.statuses.impl

import com.sorrowblue.twitlin.api.API_STATUSES
import com.sorrowblue.twitlin.api.client.Response
import com.sorrowblue.twitlin.api.objects.PagingIds
import com.sorrowblue.twitlin.api.objects.Tweet
import com.sorrowblue.twitlin.api.tweets.statuses.Align
import com.sorrowblue.twitlin.api.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.api.tweets.statuses.StreamingMessage
import com.sorrowblue.twitlin.api.tweets.statuses.Theme
import com.sorrowblue.twitlin.api.tweets.statuses.TweetOembed
import com.sorrowblue.twitlin.api.tweets.statuses.WidgetType
import com.sorrowblue.twitlin.core.client.TwitterClient
import com.sorrowblue.twitlin.core.objects.MediaId
import com.sorrowblue.twitlin.core.objects.PlaceId
import com.sorrowblue.twitlin.core.objects.TweetId
import com.sorrowblue.twitlin.core.objects.UserId
import com.sorrowblue.twitlin.core.objects.toParameter
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

internal object StreamingMessageSerializer : KSerializer<StreamingMessage> {

    override fun deserialize(decoder: Decoder): StreamingMessage {
        require(decoder is JsonDecoder)
        val element = decoder.decodeJsonElement()
        require(element is JsonObject)
        return when {
            "limit" in element -> decoder.json.decodeFromJsonElement(StreamingMessage.Limit.serializer(), element["limit"]!!)
            "disconnect" in element -> decoder.json.decodeFromJsonElement(StreamingMessage.Disconnect.serializer(), element["disconnect"]!!)
            "warning" in element -> decoder.json.decodeFromJsonElement(StreamingMessage.Warning.serializer(), element["warning"]!!)
            "delete" in element && "status" in element["delete"]!!.jsonObject -> decoder.json.decodeFromJsonElement(
                StreamingMessage.Delete.serializer(), element["delete"]!!.jsonObject["status"]!!)
            "scrub_geo" in element -> decoder.json.decodeFromJsonElement(StreamingMessage.ScrubGeo.serializer(), element["scrub_geo"]!!)
            "status_withheld" in element -> decoder.json.decodeFromJsonElement(StreamingMessage.StatusWithheld.serializer(), element["status_withheld"]!!)
            "user_withheld" in element -> decoder.json.decodeFromJsonElement(StreamingMessage.UserWithheld.serializer(), element["user_withheld"]!!)
            element.isEmpty() -> StreamingMessage.Empty
            else -> StreamingMessage.Tweet(decoder.json.decodeFromJsonElement(Tweet.serializer(), element))
        }
    }

    override fun serialize(encoder: Encoder, value: StreamingMessage) {
    }

    override val descriptor = PrimitiveSerialDescriptor("StreamingMessage", PrimitiveKind.STRING)
}

internal class StatusesApiImp(private val client: TwitterClient) : StatusesApi {

    override suspend fun filter(
        follow: List<UserId>?,
        track: List<String>?,
        locations: List<Double>?,
        delimited: Int?,
        stallWarnings: Boolean
    ): Flow<Response<StreamingMessage>> {
        return client.streaming(
            "https://stream.twitter.com/1.1/statuses/filter.json",
            Response.serializer(StreamingMessage.serializer())
        ) {
            parameter("follow", follow?.toParameter())
            parameter("track", track?.joinToString(","))
            parameter("locations", locations?.joinToString(","))
            parameter("delimited", delimited)
            parameter("stall_warnings", stallWarnings)
        }
    }

    override suspend fun homeTimeline(
        count: Int,
        sinceId: TweetId?,
        maxId: TweetId?,
        trimUser: Boolean,
        excludeReplies: Boolean,
        includeEntities: Boolean
    ): Response<List<Tweet>> {
        return client.get("$API_STATUSES/home_timeline.json", Response.serializer(ListSerializer(Tweet.serializer()))) {
            parameter("count", count)
            parameter("since_id", sinceId?.id)
            parameter("max_id", maxId?.id)
            parameter("trim_user", trimUser)
            parameter("exclude_replies", excludeReplies)
            parameter("include_entities", includeEntities)
        }
    }

    override suspend fun mentionsTimeline(
        count: Int?,
        sinceId: TweetId?,
        maxId: TweetId?,
        trimUser: Boolean,
        includeEntities: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$API_STATUSES/mentions_timeline.json",
            Response.serializer(ListSerializer(Tweet.serializer()))
        ) {
            parameter("count", count)
            parameter("since_id", sinceId?.id)
            parameter("max_id", maxId?.id)
            parameter("trim_user", trimUser)
            parameter("include_entities", includeEntities)
        }
    }

    override suspend fun userTimeline(
        userId: UserId?,
        screenName: String?,
        sinceId: TweetId?,
        maxId: TweetId?,
        count: Int?,
        trimUser: Boolean,
        excludeReplies: Boolean,
        includeRts: Boolean
    ): Response<List<Tweet>> {
        return client.get("$API_STATUSES/user_timeline.json", Response.serializer(ListSerializer(Tweet.serializer()))) {
            parameter("user_id", userId?.id)
            parameter("screen_name", screenName)
            parameter("since_id", sinceId?.id)
            parameter("max_id", maxId?.id)
            parameter("count", count)
            parameter("trim_user", trimUser)
            parameter("exclude_replies", excludeReplies)
            parameter("include_rts", includeRts)
        }
    }

    override suspend fun lookup(
        id: List<TweetId>,
        includeEntities: Boolean,
        trimUser: Boolean,
        map: Boolean?,
        includeExtAltText: Boolean?,
        includeCardUri: Boolean
    ): Response<List<Tweet>> {
        return client.get("$API_STATUSES/lookup.json", Response.serializer(ListSerializer(Tweet.serializer()))) {
            parameter("id", id.joinToString(",", transform = TweetId::id))
            parameter("include_entities", includeEntities)
            parameter("trim_user", trimUser)
            parameter("map", map)
            parameter("include_ext_alt_text", includeExtAltText)
            parameter("include_card_uri", includeCardUri)
        }
    }

    override suspend fun oembed(
        url: String,
        maxWidth: Int,
        hideMedia: Boolean,
        hideThread: Boolean,
        omitScript: Boolean,
        align: Align,
        related: String?,
        lang: String,
        theme: Theme,
        linkColor: String?,
        widgetType: WidgetType?,
        dnt: Boolean
    ): Response<TweetOembed> {
        return client.get("https://publish.twitter.com/oembed", Response.serializer(TweetOembed.serializer())) {
            parameter("url", url)
            parameter("maxwidth", maxWidth)
            parameter("hide_media", hideMedia)
            parameter("hide_thread", hideThread)
            parameter("omit_script", omitScript)
            parameter("align", align.name.lowercase())
            parameter("related", related)
            parameter("lang", lang)
            parameter("theme", theme.name.lowercase())
            parameter("link_color", linkColor)
            parameter("widget_type", widgetType?.name?.lowercase())
            parameter("dnt", dnt)
        }
    }

    override suspend fun retweetersIds(
        id: TweetId,
        count: Int?,
        cursor: String
    ): Response<PagingIds<TweetId>> {
        return client.get(
            "$API_STATUSES/retweeters/ids.json",
            Response.serializer(PagingIds.serializer(TweetId.serializer()))
        ) {
            parameter("id", id.id)
            parameter("count", count)
            parameter("cursor", cursor)
        }
    }

    override suspend fun retweets(
        id: TweetId,
        count: Int?,
        trimUser: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$API_STATUSES/retweets/${id.id}.json",
            Response.serializer(ListSerializer(Tweet.serializer()))
        ) {
            parameter("count", count)
            parameter("trim_user", trimUser)
        }
    }

    override suspend fun retweetsOfMe(
        count: Int,
        sinceId: TweetId?,
        maxId: TweetId?,
        trimUser: Boolean,
        includeEntities: Boolean,
        includeUserEntities: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$API_STATUSES/retweets_of_me.json",
            Response.serializer(ListSerializer(Tweet.serializer()))
        ) {
            parameter("count", count)
            parameter("since_id", sinceId?.id)
            parameter("max_id", maxId?.id)
            parameter("trim_user", trimUser)
            parameter("include_entities", includeEntities)
            parameter("include_user_entities", includeUserEntities)
        }
    }

    override suspend fun show(
        id: TweetId,
        trimUser: Boolean,
        includeMyRetweet: Boolean,
        includeEntities: Boolean,
        includeExtAltText: Boolean?,
        includeCardUri: Boolean
    ): Response<Tweet> {
        return client.get("$API_STATUSES/show.json", Response.serializer(Tweet.serializer())) {
            parameter("id", id.id)
            parameter("trim_user", trimUser)
            parameter("include_my_retweet", includeMyRetweet)
            parameter("include_entities", includeEntities)
            parameter("include_ext_alt_text", includeExtAltText)
            parameter("include_card_uri", includeCardUri)
        }
    }

    override suspend fun destroy(id: TweetId, trimUser: Boolean): Response<Tweet> {
        return client.post("$API_STATUSES/destroy/${id.id}.json", Response.serializer(Tweet.serializer())) {
            parameter("trim_user", trimUser)
        }
    }

    override suspend fun retweet(id: TweetId, trimUser: Boolean): Response<Tweet> {
        return client.post("$API_STATUSES/retweet/${id.id}.json", Response.serializer(Tweet.serializer())) {
            parameter("trim_user", trimUser)
        }
    }

    override suspend fun unretweet(id: TweetId, trimUser: Boolean): Response<Tweet> {
        return client.post("$API_STATUSES/unretweet/${id.id}.json", Response.serializer(Tweet.serializer())) {
            parameter("trim_user", trimUser)
        }
    }

    override suspend fun update(
        status: String,
        inReplyToStatusId: TweetId?,
        autoPopulateReplyMetadata: Boolean,
        excludeReplyUserIds: List<UserId>?,
        attachmentUrl: String?,
        mediaIds: List<MediaId>?,
        possiblySensitive: Boolean,
        lat: Float?,
        long: Float?,
        placeId: PlaceId?,
        displayCoordinates: Boolean?,
        trimUser: Boolean,
        enableDmcommands: Boolean,
        failDmcommands: Boolean,
        cardUri: String?
    ): Response<Tweet> {
        return client.post("$API_STATUSES/update.json", Response.serializer(Tweet.serializer())) {
            parameter("status", status)
            parameter("in_reply_to_status_id", inReplyToStatusId?.id)
            parameter("auto_populate_reply_metadata", autoPopulateReplyMetadata)
            parameter("exclude_reply_user_ids", excludeReplyUserIds?.joinToString(",") { it.id })
            parameter("attachment_url", attachmentUrl)
            parameter("media_ids", mediaIds?.joinToString(",") { it.id })
            parameter("possibly_sensitive", possiblySensitive)
            parameter("lat", lat)
            parameter("long", long)
            parameter("place_id", placeId?.id)
            parameter("display_coordinates", displayCoordinates)
            parameter("trim_user", trimUser)
            parameter("enable_dmcommands", enableDmcommands)
            parameter("fail_dmcommands", failDmcommands)
            parameter("card_uri", cardUri)
        }
    }

    override fun sample(delimited: Boolean, stallWarnings: Boolean): Flow<Response<StreamingMessage>> {
        return client.streaming(
            "https://stream.twitter.com/1.1/statuses/sample.json",
            Response.serializer(StreamingMessage.serializer())
        ) {
            parameter("delimited", delimited)
            parameter("stall_warnings", stallWarnings)
        }
    }
}
