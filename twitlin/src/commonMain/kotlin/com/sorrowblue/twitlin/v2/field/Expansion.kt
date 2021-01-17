/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.field

public enum class Expansion(override val value: String) : OptionalField {
    AUTHOR_ID("author_id"),
    REFERENCED_TWEETS_ID("referenced_tweets.id"),
    IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
    ATTACHMENTS_MEDIA_KEYS("attachments.media_keys"),
    ATTACHMENTS_POLL_IDS("attachments.poll_ids"),
    GEO_PLACE_ID("geo.place_id"),
    ENTITIES_MENTIONS_USERNAME("entities.mentions.username"),
    REFERENCED_TWEETS_ID_AUTHOR_ID("referenced_tweets.id.author_id");

    public companion object {
        public fun all(): List<Expansion> = listOf(
            AUTHOR_ID,
            REFERENCED_TWEETS_ID,
            IN_REPLY_TO_USER_ID,
            ATTACHMENTS_MEDIA_KEYS,
            ATTACHMENTS_POLL_IDS,
            GEO_PLACE_ID,
            ENTITIES_MENTIONS_USERNAME,
            REFERENCED_TWEETS_ID_AUTHOR_ID,
        )
    }
}
