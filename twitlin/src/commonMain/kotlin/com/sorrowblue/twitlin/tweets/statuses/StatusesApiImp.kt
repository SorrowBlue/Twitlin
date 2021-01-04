/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.client.UserClient
import com.sorrowblue.twitlin.objects.Tweet

private const val ROOT = "${Urls.V1}/statuses"

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
    ) = client.post<Tweet>(
        "$ROOT/update.json",
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

    override suspend fun homeTimeline(
        count: Int,
        sinceId: Long?,
        maxId: Long?,
        trimUser: Boolean?,
        excludeReplies: Boolean?,
        includeEntities: Boolean?,
        includeCard: Boolean?
    ): Response<List<Tweet>> = client.get<List<Tweet>>(
        "$ROOT/home_timeline.json",
        "count" to count,
        "since_id" to sinceId,
        "max_id" to maxId,
        "trim_user" to trimUser,
        "exclude_replies" to excludeReplies,
        "include_entities" to includeEntities
    )

    override suspend fun userTimeline(
        userId: Long,
        sinceId: Long?,
        maxId: Long?,
        count: Int?,
        trimUser: Boolean?,
        excludeReplies: Boolean?,
        includeRetweet: Boolean?,
        includeCard: Boolean?
    ): Response<List<Tweet>> = client.get<List<Tweet>>(
        "$ROOT/user_timeline.json",
        "user_id" to userId,
        "since_id" to sinceId,
        "max_id" to maxId,
        "count" to count,
        "trim_user" to trimUser,
        "exclude_replies" to excludeReplies,
        "include_rts" to includeRetweet
    )

    override suspend fun userTimeline(
        screenName: String,
        sinceId: Long?,
        maxId: Long?,
        count: Int?,
        trimUser: Boolean?,
        excludeReplies: Boolean?,
        includeRetweet: Boolean?,
        includeCard: Boolean?
    ): Response<List<Tweet>> = client.get<List<Tweet>>(
        "$ROOT/user_timeline.json",
        "screenName" to screenName,
        "since_id" to sinceId,
        "max_id" to maxId,
        "count" to count,
        "trim_user" to trimUser,
        "exclude_replies" to excludeReplies,
        "include_rts" to includeRetweet
    )

    override suspend fun lookup(id: List<Long>): Response<List<Tweet>> =
        client.get("$ROOT/lookup.json", "id" to id.joinToString(","))
}
