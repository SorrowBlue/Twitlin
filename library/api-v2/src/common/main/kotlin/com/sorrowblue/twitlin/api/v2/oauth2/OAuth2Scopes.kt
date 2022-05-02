package com.sorrowblue.twitlin.api.v2.oauth2

import com.sorrowblue.twitlin.api.v2.field.OptionalField

/**
 * Scopes allow you to set granular access for your App so that your App only has the permissions that it needs.
 * The scopes we have available to start with include the following:
 */
public enum class OAuth2Scopes(override val value: String) : OptionalField {

    /**
     * All the Tweets you can view, including Tweets from protected accounts.
     */
    TWEET_READ("tweet.read"),

    /**
     * Tweet and Retweet for you.
     */
    TWEET_WRITE("tweet.write"),

    /**
     * Hide and unhide replies to your Tweets.
     */
    TWEET_MODERATE_WRITE("tweet.moderate.write"),

    /**
     * Any account you can view, including protected accounts.
     */
    USERS_READ("users.read"),

    /**
     * People who follow you and people who you follow.
     */
    FOLLOWS_READ("follows.read"),

    /**
     * Follow and unfollow people for you.
     */
    FOLLOWS_WRITE("follows.write"),

    /**
     * Stay connected to your account until you revoke access.
     */
    OFFLINE_ACCESS("offline.access"),

    /**
     * All the Spaces you can view.
     */
    SPACE_READ("space.read"),

    /**
     * Accounts you’ve muted.
     */
    MUTE_READ("mute.read"),

    /**
     * Mute and unmute accounts for you.
     */
    MUTE_WRITE("mute.write"),

    /**
     * Tweets you’ve liked and likes you can view.
     */
    LIKE_READ("like.read"),

    /**
     * Like and un-like Tweets for you.
     */
    LIKE_WRITE("like.write"),

    /**
     * Lists, list members, and list followers of lists you’ve created or are a member of, including private lists.
     */
    LIST_READ("list.read"),

    /**
     * Create and manage Lists for you.
     */
    LIST_WRITE("list.write"),

    /**
     * Accounts you’ve blocked.
     */
    BLOCK_READ("block.read"),

    /**
     * Block and unblock accounts for you.
     */
    BLOCK_WRITE("block.write"),

    /**
     * Get Bookmarked Tweets from an authenticated user.
     */
    BOOKMARK_READ("bookmark.read"),

    /**
     * Bookmark and remove Bookmarks from Tweets
     */
    BOOKMARK_WRITE("bookmark.write"),
}
