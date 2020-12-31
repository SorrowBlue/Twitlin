/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.annotation.JvmSerializable
import com.sorrowblue.twitlin.v2.serializer.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class Poll(
    val id: String,
    val options: List<Option>,
    @SerialName("duration_minutes")
    val durationMinutes: Int? = null,
    @SerialName("end_datetime")
    @Serializable(LocalDateTimeSerializer::class)
    val endDatetime: LocalDateTime? = null,
    @SerialName("voting_status")
    val votingStatus: Status? = null,
) : JvmSerializable {

    @Serializable
    public data class Option(
        val position: Int,
        val label: String,
        val votes: Int,
    ) : JvmSerializable

    @Serializable
    public enum class Status {
        @SerialName("closed")
        CLOSED,

        @SerialName("open")
        OPEN
    }

    val sortedOptions: List<Option> = options.sortedBy { it.position }
    val allVotes: Int = options.sumOf { it.votes }
}
