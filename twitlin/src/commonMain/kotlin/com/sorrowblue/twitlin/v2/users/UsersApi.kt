/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.OptionalField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import kotlinx.datetime.LocalDateTime

public interface UsersApi {

    public enum class Expansion(override val value: String) : OptionalField {
        PINNED_TWEET_ID("pinned_tweet_id");

        public companion object {
            public fun all(): List<Expansion> = listOf(PINNED_TWEET_ID)
        }
    }

    public suspend fun users(
        id: String,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
    ): Response<User>

    public suspend fun users(
        ids: List<String>,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
    ): Response<List<User>>

    public suspend fun byUsername(
        username: String,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
    ): Response<User>

    public suspend fun byUsername(
        usernames: List<String>,
        expansions: List<Expansion>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
    ): Response<List<User>>

    public suspend fun tweets(
        id: String,
        endTime: LocalDateTime? = null,
        start_time: LocalDateTime? = null,
        exclude: String? = null,
        max_results: Int = 10,
        pagination_token: String? = null,
        since_id: String? = null,
        until_id: String? = null,
        expansions: List<Expansion>? = null,
        mediaFields: List<MediaField>? = null,
        placeFields: List<PlaceField>? = null,
        pollFields: List<PollField>? = null,
        tweetFields: List<TweetField>? = null,
        userFields: List<UserField>? = null,
    ): Response<List<Tweet>>
}
