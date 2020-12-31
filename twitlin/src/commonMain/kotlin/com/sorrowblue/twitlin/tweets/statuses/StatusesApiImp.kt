/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.client.TwitlinClient
import com.sorrowblue.twitlin.client.Urls
import com.sorrowblue.twitlin.objects.Tweet
import com.sorrowblue.twitlin.objects.TwitterTweet
import com.sorrowblue.twitlin.utils.TweetUtil

private const val ROOT = "${Urls.V1}/statuses"

internal class StatusesApiImp(private val client: TwitlinClient) : StatusesApi {

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
    ) = client.post<TwitterTweet>(
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
    ): Response<List<TwitterTweet>> = client.get<List<TwitterTweet>>(
        "$ROOT/home_timeline.json",
        "count" to count,
        "since_id" to sinceId,
        "max_id" to maxId,
        "trim_user" to trimUser,
        "exclude_replies" to excludeReplies,
        "include_entities" to includeEntities
    ).resolveCard(includeCard == true)

    override suspend fun userTimeline(
        userId: Long,
        sinceId: Long?,
        maxId: Long?,
        count: Int?,
        trimUser: Boolean?,
        excludeReplies: Boolean?,
        includeRetweet: Boolean?,
        includeCard: Boolean?
    ): Response<List<TwitterTweet>> = client.get<List<TwitterTweet>>(
        "$ROOT/user_timeline.json",
        "user_id" to userId,
        "since_id" to sinceId,
        "max_id" to maxId,
        "count" to count,
        "trim_user" to trimUser,
        "exclude_replies" to excludeReplies,
        "include_rts" to includeRetweet
    ).resolveCard(includeCard == true)

    override suspend fun userTimeline(
        screenName: String,
        sinceId: Long?,
        maxId: Long?,
        count: Int?,
        trimUser: Boolean?,
        excludeReplies: Boolean?,
        includeRetweet: Boolean?,
        includeCard: Boolean?
    ): Response<List<TwitterTweet>> = client.get<List<TwitterTweet>>(
        "$ROOT/user_timeline.json",
        "screenName" to screenName,
        "since_id" to sinceId,
        "max_id" to maxId,
        "count" to count,
        "trim_user" to trimUser,
        "exclude_replies" to excludeReplies,
        "include_rts" to includeRetweet
    ).resolveCard(includeCard == true)

    override suspend fun lookup(id: List<Long>): Response<List<TwitterTweet>> =
        client.get("$ROOT/lookup.json", "id" to id.joinToString(","))

    private suspend fun Response<List<TwitterTweet>>.resolveCard(includeCard: Boolean): Response<List<TwitterTweet>> {
        return getOrNull()?.map { value ->
            val tweet = Tweet.valueOf(value)
            if (tweet is Tweet.Normal && includeCard) {
                tweet.source.retweetedStatus?.let { twitterTweet ->
                    twitterTweet.entities?.urls?.firstOrNull()?.expandedUrl
                        ?.let { TweetUtil.twitterCard(it) }
                        ?.let { value.copy(retweetedStatus = value.retweetedStatus?.copy(card = it)) }
                        ?: value
                } ?: kotlin.run {
                    tweet.source.entities?.urls?.firstOrNull()?.expandedUrl
                        ?.let { TweetUtil.twitterCard(it) }
                        ?.let { value.copy(card = it) } ?: value
                }
            } else value
        }?.let {
            Response.Success(it)
        } ?: this
    }
}
