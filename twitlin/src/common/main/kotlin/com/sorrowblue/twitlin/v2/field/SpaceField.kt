package com.sorrowblue.twitlin.v2.field


public enum class SpaceField(override val value: String) : OptionalField {
    HOST_IDS("host_ids"),
    CREATED_AT("created_at"),
    CREATOR_ID("creator_id"),
    ID("id"),
    LANG("lang"),
    INVITED_USER_IDS("invited_user_ids"),
    PARTICIPANT_COUNT("participant_count"),
    SPEAKER_IDS("speaker_ids"),
    STARTED_AT("started_at"),
    STATE("state"),
    TITLE("title"),
    UPDATED_AT("updated_at"),
    SCHEDULED_START("scheduled_start"),
    IS_TICKETED("is_ticketed");

    public companion object {
        public fun all(): List<SpaceField> = listOf(
            HOST_IDS,
            CREATED_AT,
            CREATOR_ID,
            ID,
            LANG,
            INVITED_USER_IDS,
            PARTICIPANT_COUNT,
            SPEAKER_IDS,
            STARTED_AT,
            STATE,
            TITLE,
            UPDATED_AT,
            SCHEDULED_START,
            IS_TICKETED
        )
    }
}
