package com.sorrowblue.twitlin.v2.spaces

import com.sorrowblue.twitlin.v2.field.OptionalField

/**
 * Expansion
 *
 * @property value
 * @constructor Create empty Expansion
 */
public enum class Expansion(override val value: String) : OptionalField {
    INVITED_USER_IDS("invited_user_ids"),
    SPEAKER_IDS("speaker_ids"),
    CREATOR_ID("creator_id"),
    HOST_IDS("host_ids");

    public companion object {
        public fun all(): List<Expansion> = listOf(INVITED_USER_IDS, SPEAKER_IDS, CREATOR_ID, HOST_IDS)
    }
}
