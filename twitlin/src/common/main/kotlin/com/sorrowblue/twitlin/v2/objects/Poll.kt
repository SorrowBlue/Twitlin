/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.AndroidParcelable
import com.sorrowblue.twitlin.annotation.AndroidParcelize
import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.annotation.KotlinIgnoredOnParcel
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.isoToLocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@AndroidParcelize
@Serializable
public data class Poll(
    val id: String,
    val options: List<Option>,
    @SerialName("duration_minutes")
    val durationMinutes: Int? = null,
    @SerialName("end_datetime")
    val _endDatetime: String? = null,
    @SerialName("voting_status")
    val votingStatus: Status? = null,
) : AndroidParcelable, JvmSerializable {

    val endDatetime: LocalDateTime? get() = _endDatetime?.isoToLocalDateTime()

    @AndroidParcelize
    @Serializable
    public data class Option(
        val position: Int,
        val label: String,
        val votes: Int,
    ) : AndroidParcelable, JvmSerializable

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
