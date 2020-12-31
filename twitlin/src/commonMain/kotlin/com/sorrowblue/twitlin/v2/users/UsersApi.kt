/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.Response
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.Field
import com.sorrowblue.twitlin.v2.tweets.TweetField
import com.sorrowblue.twitlin.v2.tweets.UserField

public interface UsersApi {

    public enum class Expansion(override val value: String) : Field {
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
}
