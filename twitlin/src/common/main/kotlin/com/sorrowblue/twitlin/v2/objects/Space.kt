package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@AndroidParcelize
@Serializable
public data class Space(
    val id: String,
    val state: State,
    @SerialName("created_at") val _createdAt: String? = null,
    @SerialName("host_ids") val hostIds: List<String>? = null,
    val lang: String? = null,
    val isTicketed: Boolean? = null,
    @SerialName("invited_user_ids") val invitedUserIds: List<String>? = null,
    @SerialName("participant_count") val participantCount: Int? = null,
    @SerialName("scheduled_start") val _scheduledStart: String? = null,
    @SerialName("speaker_ids") val speakerIds: List<String>? = null,
    @SerialName("started_at") val _startedAt: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("updated_at") val _updatedAt: String? = null,
) : AndroidParcelable, JvmSerializable {

    val createdAt: LocalDateTime? get() = _createdAt?.isoToLocalDateTime()
    val scheduledStart: LocalDateTime? get() = _scheduledStart?.isoToLocalDateTime()
    val startedAt: LocalDateTime? get() = _startedAt?.isoToLocalDateTime()
    val updatedAt: LocalDateTime? get() = _updatedAt?.isoToLocalDateTime()

    @Serializable
    public enum class State {
        @SerialName("live")
        LIVE,

        @SerialName("scheduled")
        SCHEDULED
    }

}
