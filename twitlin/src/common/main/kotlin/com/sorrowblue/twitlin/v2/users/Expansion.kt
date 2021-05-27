package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.field.OptionalField

public enum class Expansion(override val value: String) : OptionalField {
    PINNED_TWEET_ID("pinned_tweet_id");

    public companion object {
        public fun all(): List<Expansion> = listOf(PINNED_TWEET_ID)
    }
}
