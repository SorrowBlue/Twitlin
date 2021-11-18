package com.sorrowblue.twitlin.v2.spaces

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.objects.UserId
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Space
 *
 * @property id
 * @property state
 * @property _createdAt
 * @property hostIds
 * @property lang
 * @property isTicketed
 * @property invitedUserIds
 * @property participantCount
 * @property _scheduledStart
 * @property speakerIds
 * @property _startedAt
 * @property title
 * @property _updatedAt
 * @constructor Create empty Space
 */
@AndroidParcelize
@kotlinx.serialization.Serializable
public data class Space(
    val id: SpaceId,
    val state: State,
    @SerialName("created_at")
    val _createdAt: String? = null,
    @SerialName("host_ids")
    val hostIds: List<UserId>? = null,
    val lang: String? = null,
    val isTicketed: Boolean? = null,
    @SerialName("invited_user_ids")
    val invitedUserIds: List<UserId>? = null,
    @SerialName("participant_count")
    val participantCount: Int? = null,
    @SerialName("scheduled_start")
    val _scheduledStart: String? = null,
    @SerialName("speaker_ids")
    val speakerIds: List<UserId>? = null,
    @SerialName("started_at")
    val _startedAt: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("updated_at")
    val _updatedAt: String? = null,
) : AndroidParcelable, JvmSerializable {

    val createdAt: LocalDateTime? get() = _createdAt?.isoToLocalDateTime()

    val scheduledStart: LocalDateTime? get() = _scheduledStart?.isoToLocalDateTime()

    val startedAt: LocalDateTime? get() = _startedAt?.isoToLocalDateTime()

    val updatedAt: LocalDateTime? get() = _updatedAt?.isoToLocalDateTime()

    /**
     * State
     *
     * @property value
     * @constructor Create empty State
     */
    @Serializable
    public enum class State(public val value: String) {
        @SerialName("live")
        LIVE("live"),

        @SerialName("scheduled")
        SCHEDULED("scheduled")
    }

}
