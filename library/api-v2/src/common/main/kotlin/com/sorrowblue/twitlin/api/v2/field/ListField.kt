package com.sorrowblue.twitlin.api.v2.field

public enum class ListField(override val value: String) : OptionalField {
    CREATED_AT("created_at"),
    FOLLOWER_COUNT("follower_count"),
    MEMBER_COUNT("member_count"),
    PRIVATE("private"),
    DESCRIPTION("description");

    public companion object {
        public fun all(): List<ListField> = listOf(
            CREATED_AT,
            FOLLOWER_COUNT,
            MEMBER_COUNT,
            PRIVATE,
            DESCRIPTION
        )
    }
}
