package com.sorrowblue.twitlin.api.v2.objects

import com.sorrowblue.twitlin.core.annotation.AndroidParcelable
import com.sorrowblue.twitlin.core.annotation.AndroidParcelize
import com.sorrowblue.twitlin.core.annotation.JvmSerializable
import com.sorrowblue.twitlin.core.annotation.KotlinIgnoredOnParcel
import kotlin.collections.List
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Poll
 *
 * @property id
 * @property options
 * @property durationMinutes
 * @property _endDatetime
 * @property votingStatus
 * @constructor Create empty Poll
 */
@AndroidParcelize
@Serializable
public data class Poll(
    @SerialName("id")
    val id: PollId,
    val options: List<Option>,
    @SerialName("duration_minutes")
    val durationMinutes: Int? = null,
    @SerialName("end_datetime")
    internal val _endDatetime: String? = null,
    @SerialName("voting_status")
    val votingStatus: Status? = null,
) : AndroidParcelable, JvmSerializable {

    val endDatetime: LocalDateTime? get() = _endDatetime?.isoToLocalDateTime()

    /**
     * Option
     *
     * @property position
     * @property label
     * @property votes
     * @constructor Create empty Option
     */
    @AndroidParcelize
    @Serializable
    public data class Option(
        val position: Int,
        val label: String,
        val votes: Int,
    ) : AndroidParcelable, JvmSerializable

    /**
     * Status
     *
     * @constructor Create empty Status
     */
    @Serializable
    public enum class Status {
        @SerialName("closed")
        CLOSED,

        @SerialName("open")
        OPEN
    }

    @KotlinIgnoredOnParcel
    val sortedOptions: List<Option> = options.sortedBy { it.position }

    @KotlinIgnoredOnParcel
    val allVotes: Int = options.sumOf { it.votes }
}
