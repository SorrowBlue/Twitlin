package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.v2.serializer.LocalDateTimeSerializer
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Poll(
	val id: String,
	val options: List<Option>,
	@SerialName("duration_minutes")
	val durationMinutes: Int,
	@SerialName("end_datetime")
	@Serializable(LocalDateTimeSerializer::class)
	val endDatetime: LocalDateTime,
	@SerialName("voting_status")
	val votingStatus: String,
) {

	@Serializable
	data class Option(
		val position: Int,
		val label: String,
		val votes: Int,
	)
}
