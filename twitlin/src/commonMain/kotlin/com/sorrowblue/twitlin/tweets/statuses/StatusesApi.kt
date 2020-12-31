/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.client.Response
import com.sorrowblue.twitlin.objects.TwitterTweet

public interface StatusesApi {

    public suspend fun update(
        status: String,
        inReplyToStatusId: String? = null,
        autoPopulateReplyMetadata: Boolean = false,
        excludeReplyUserIds: List<Long>? = null,
        attachmentUrl: String? = null,
        mediaIds: List<Long>? = null,
        possiblySensitive: Boolean = false,
        lat: Float? = null,
        long: Float? = null,
        placeId: String? = null,
        displayCoordinates: Boolean? = null,
        trimUser: Boolean = false,
        enableDmcommands: Boolean = false,
        failDmcommands: Boolean = true,
        cardUri: String? = null
    ): Response<TwitterTweet>

    public suspend fun homeTimeline(
        count: Int = 20,
        sinceId: Long? = null,
        maxId: Long? = null,
        trimUser: Boolean? = null,
        excludeReplies: Boolean? = null,
        includeEntities: Boolean? = null,
        includeCard: Boolean? = null
    ): Response<List<TwitterTweet>>

    public suspend fun userTimeline(
        userId: Long,
        sinceId: Long? = null,
        maxId: Long? = null,
        count: Int? = null,
        trimUser: Boolean? = null,
        excludeReplies: Boolean? = null,
        includeRetweet: Boolean? = null,
        includeCard: Boolean? = null
    ): Response<List<TwitterTweet>>

    public suspend fun userTimeline(
        screenName: String,
        sinceId: Long? = null,
        maxId: Long? = null,
        count: Int? = null,
        trimUser: Boolean? = null,
        excludeReplies: Boolean? = null,
        includeRetweet: Boolean? = null,
        includeCard: Boolean? = null
    ): Response<List<TwitterTweet>>

    public suspend fun lookup(id: List<Long>): Response<List<TwitterTweet>>
}
