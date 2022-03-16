package com.sorrowblue.twitlin.api.v2.oauth2

import com.sorrowblue.twitlin.api.v2.field.OptionalField

/**
 * Scopes allow you to set granular access for your App so that your App only has the permissions that it needs.
 * The scopes we have available to start with include the following:
 */
public enum class OAuth2Scopes(override val value: String) : OptionalField {

    /**
     * Allows your App to read Tweets.  You will also be able to see Tweets from protected Tweets.
     */
    TWEET_READ("tweet.read"),

    /**
     * Get information about any account.
     */
    USERS_READ("users.read"),

    /**
     * Allows your App to access information on which accounts follow you and accounts you follow.
     */
    ACCOUNT_FOLLOWS_READ("account.follows.read"),

    /**
     * Allows your App to follow and unfollow accounts.
     */
    ACCOUNT_FOLLOWS_WRITE("account.follows.write"),

    /**
     * Allows an OAuth 2.0 refresh token to be issued that can be used to obtain an access token. If this scope is not passed, we will not generate a refresh token.
     */
    OFFLINE_ACCESS("offline.access"),

    /**
     * Access all of the Spaces you can see.
     */
    SPACES_READ("spaces.read");

    public companion object {

        /**
         * Returns all available scopes.
         *
         * @return All available scopes
         */
        public val all: List<OAuth2Scopes>
            get() =
                listOf(TWEET_READ, USERS_READ, ACCOUNT_FOLLOWS_READ, ACCOUNT_FOLLOWS_WRITE, OFFLINE_ACCESS)
    }
}
