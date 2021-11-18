package com.sorrowblue.twitlin.v2.lists

import com.sorrowblue.twitlin.v2.field.OptionalField

public enum class Expansion(override val value: String) : OptionalField {
    OWNER_ID("owner_id");

    public companion object {
        public fun all(): List<Expansion> = listOf(OWNER_ID)
    }
}
