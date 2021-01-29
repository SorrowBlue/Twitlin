/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.impl

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.core.Urls
import com.sorrowblue.twitlin.objects.PagingIds
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.tweets.Align
import com.sorrowblue.twitlin.tweets.StatusesApi
import com.sorrowblue.twitlin.tweets.Theme
import com.sorrowblue.twitlin.tweets.TweetOembed
import com.sorrowblue.twitlin.tweets.WidgetType
import com.sorrowblue.twitlin.utilities.LanguageCode
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

private const val STATUSES = "${Urls.V1}/statuses"

internal class StatusesApiImp(private val client: UserClient) : StatusesApi {
    override suspend fun update(
        status: String,
        inReplyToStatusId: String?,
        autoPopulateReplyMetadata: Boolean,
        excludeReplyUserIds: List<Long>?,
        attachmentUrl: String?,
        mediaIds: List<Long>?,
        possiblySensitive: Boolean,
        lat: Float?,
        long: Float?,
        placeId: String?,
        displayCoordinates: Boolean?,
        trimUser: Boolean,
        enableDmcommands: Boolean,
        failDmcommands: Boolean,
        cardUri: String?
    ): Response<Tweet> {
        return client.post(
            "$STATUSES/update.json",
            Response.serializer(Tweet.serializer()),
            "status" to status,
            "in_reply_to_status_id" to inReplyToStatusId,
            "auto_populate_reply_metadata" to autoPopulateReplyMetadata,
            "exclude_reply_user_ids" to excludeReplyUserIds,
            "attachment_url" to attachmentUrl,
            "media_ids" to mediaIds,
            "possibly_sensitive" to possiblySensitive,
            "lat" to lat,
            "long" to long,
            "place_id" to placeId,
            "display_coordinates" to displayCoordinates,
            "trim_user" to trimUser,
            "enable_dmcommands" to enableDmcommands,
            "fail_dmcommands" to failDmcommands,
            "card_uri" to cardUri
        )
    }

    override suspend fun destroy(id: String, trimUser: Boolean): Response<Tweet> {
        return client.post(
            "$STATUSES/destroy/$id.json",
            Response.serializer(Tweet.serializer()),
            "trim_user" to trimUser
        )
    }

    override suspend fun show(
        id: String,
        trimUser: Boolean,
        includeMyRetweet: Boolean,
        includeEntities: Boolean,
        includeExtAltText: Boolean?,
        includeCardUri: Boolean
    ): Response<Tweet> {
        return client.get(
            "$STATUSES/show.json",
            Response.serializer(Tweet.serializer()),
            "id" to id,
            "trim_user" to trimUser,
            "include_my_retweet" to includeMyRetweet,
            "include_entities" to includeEntities,
            "include_ext_alt_text" to includeExtAltText,
            "include_card_uri" to includeCardUri
        )
    }

    override suspend fun oembed(
        url: String,
        maxWidth: Int,
        hideMedia: Boolean,
        hideThread: Boolean,
        omitScript: Boolean,
        align: Align,
        related: String?,
        lang: LanguageCode,
        theme: Theme,
        linkColor: String?,
        widgetType: WidgetType?,
        dnt: Boolean
    ): Response<TweetOembed> {
        return client.get(
            "https://publish.twitter.com/oembed",
            Response.serializer(TweetOembed.serializer()),
            "url" to url,
            "maxwidth" to maxWidth,
            "hide_media" to hideMedia,
            "hide_thread" to hideThread,
            "omit_script" to omitScript,
            "align" to align.name.toLowerCase(),
            "related" to related,
            "lang" to lang.value,
            "theme" to theme.name.toLowerCase(),
            "link_color" to linkColor,
            "widget_type" to widgetType?.name?.toLowerCase(),
            "dnt" to dnt
        )
    }

    override suspend fun lookup(
        id: List<String>,
        includeEntities: Boolean,
        trimUser: Boolean,
        map: Boolean?,
        includeExtAltText: Boolean?,
        includeCardUri: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$STATUSES/lookup.json",
            Response.serializer(ListSerializer(Tweet.serializer())),
            "id" to id.joinToString(","),
            "include_entities" to includeEntities,
            "trim_user" to trimUser,
            "map" to map,
            "include_ext_alt_text" to includeExtAltText,
            "include_card_uri" to includeCardUri
        )
    }

    override suspend fun retweet(id: String, trimUser: Boolean): Response<Tweet> {
        return client.post(
            "$STATUSES/retweet/$id.json",
            Response.serializer(Tweet.serializer()),
            "trim_user" to trimUser
        )
    }

    override suspend fun unretweet(id: String, trimUser: Boolean): Response<Tweet> {
        return client.post(
            "$STATUSES/unretweet/$id.json",
            Response.serializer(Tweet.serializer()),
            "trim_user" to trimUser
        )
    }

    override suspend fun retweets(
        id: String,
        count: Int?,
        trimUser: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$STATUSES/retweets/$id.json",
            Response.serializer(ListSerializer(Tweet.serializer())),
            "count" to count, "trim_user" to trimUser
        )
    }

    override suspend fun retweetsOfMe(
        count: Int,
        sinceId: Long?,
        maxId: Long?,
        trimUser: Boolean,
        includeEntities: Boolean,
        includeUserEntities: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$STATUSES/retweets_of_me.json",
            Response.serializer(ListSerializer(Tweet.serializer())),
            "count" to count,
            "since_id" to sinceId,
            "max_id" to maxId,
            "trim_user" to trimUser,
            "include_entities" to includeEntities,
            "include_user_entities" to includeUserEntities
        )
    }

    override suspend fun retweetersIds(
        id: String,
        count: Int?,
        cursor: String
    ): Response<PagingIds> {
        return client.get(
            "$STATUSES/retweeters/ids.json",
            Response.serializer(PagingIds.serializer()),
            "id" to id,
            "count" to count,
            "cursor" to cursor
        )
    }

    override suspend fun homeTimeline(
        count: Int,
        sinceId: String?,
        maxId: String?,
        trimUser: Boolean,
        excludeReplies: Boolean,
        includeEntities: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$STATUSES/home_timeline.json",
            Response.serializer(ListSerializer(Tweet.serializer())),
            "count" to count,
            "since_id" to sinceId,
            "max_id" to maxId,
            "trim_user" to trimUser,
            "exclude_replies" to excludeReplies,
            "include_entities" to includeEntities
        )
    }

    override suspend fun userTimeline(
        userId: String?,
        screenName: String?,
        sinceId: String?,
        maxId: String?,
        count: Int?,
        trimUser: Boolean,
        excludeReplies: Boolean,
        includeRts: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$STATUSES/user_timeline.json",
            Response.serializer(ListSerializer(Tweet.serializer())),
            "user_id" to userId,
            "screen_name" to screenName,
            "since_id" to sinceId,
            "max_id" to maxId,
            "count" to count,
            "trim_user" to trimUser,
            "exclude_replies" to excludeReplies,
            "include_rts" to includeRts
        )
    }

    override suspend fun mentionsTimeline(
        count: Int?,
        sinceId: String?,
        maxId: String?,
        trimUser: Boolean,
        includeEntities: Boolean
    ): Response<List<Tweet>> {
        return client.get(
            "$STATUSES/mentions_timeline.json",
            Response.serializer(ListSerializer(Tweet.serializer())),
            "count" to count,
            "since_id" to sinceId,
            "max_id" to maxId,
            "trim_user" to trimUser,
            "include_entities" to includeEntities
        )
    }

    override suspend fun filter(
        follow: List<String>?,
        track: List<String>?,
        locations: List<Double>?,
        delimited: Int?,
        stallWarnings: Boolean?
    ): Flow<Response<Unit>> {
        return client.streaming(
            "https://stream.twitter.com/1.1/statuses/filter.json",
            Response.serializer(Unit.serializer()),
            "follow" to follow?.joinToString(","),
            "track" to track?.joinToString(","),
            "locations" to locations?.joinToString(","),
            "delimited" to delimited,
            "stall_warnings" to stallWarnings,
        )
    }
}
