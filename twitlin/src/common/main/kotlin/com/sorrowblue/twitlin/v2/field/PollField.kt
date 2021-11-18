package com.sorrowblue.twitlin.v2.field

public enum class PollField(override val value: String) : OptionalField {
    DURATION_MINUTES("duration_minutes"),
    END_DATETIME("end_datetime"),
    ID("id"),
    OPTIONS("options"),
    VOTING_STATUS("voting_status");

    public companion object {
        public fun all(): List<PollField> = listOf(
            DURATION_MINUTES,
            END_DATETIME,
            ID,
            OPTIONS,
            VOTING_STATUS
        )
    }
}
