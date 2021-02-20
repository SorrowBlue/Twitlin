/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.field

public enum class TweetField(override val value: String) : OptionalField {
    ATTACHMENTS("attachments"),
    AUTHOR_ID("author_id"),
    CONTEXT_ANNOTATIONS("context_annotations"),
    CONVERSATION_ID("conversation_id"),
    CREATED_AT("created_at"),
    ENTITIES("entities"),
    GEO("geo"),
    ID("id"),
    IN_REPLY_TO_USER_ID("in_reply_to_user_id"),
    LANG("lang"),
    NON_PUBLIC_METRICS("non_public_metrics"),
    PUBLIC_METRICS("public_metrics"),
    ORGANIC_METRICS("organic_metrics"),
    PROMOTED_METRICS("promoted_metrics"),
    POSSIBLY_SENSITIVE("possibly_sensitive"),
    REFERENCED_TWEETS("referenced_tweets"),
    SOURCE("source"),
    TEXT("text"),
    WITHHELD("withheld");

    public companion object {
        public fun all(): List<TweetField> = listOf(
            ATTACHMENTS,
            AUTHOR_ID,
            CONTEXT_ANNOTATIONS,
            CONVERSATION_ID,
            CREATED_AT,
            ENTITIES,
            GEO,
            ID,
            IN_REPLY_TO_USER_ID,
            LANG,
            NON_PUBLIC_METRICS,
            PUBLIC_METRICS,
            ORGANIC_METRICS,
            PROMOTED_METRICS,
            POSSIBLY_SENSITIVE,
            REFERENCED_TWEETS,
            SOURCE,
            TEXT,
            WITHHELD
        )
    }
}
