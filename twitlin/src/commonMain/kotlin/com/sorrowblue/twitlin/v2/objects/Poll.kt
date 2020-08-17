package com.sorrowblue.twitlin.v2.objects

import com.sorrowblue.twitlin.Parcelable
import com.sorrowblue.twitlin.Parcelize
import com.sorrowblue.twitlin.v2.serializer.DateTimeTzSerializer
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Poll(
	val id: String,
	val options: List<Option>,
	@SerialName("duration_minutes")
	val durationMinutes: Int,
	@SerialName("end_datetime")
	@Serializable(DateTimeTzSerializer::class)
	val endDatetime: DateTimeTz,
	@SerialName("voting_status")
	val votingStatus: String,
) : Parcelable {

	@Parcelize
	@Serializable
	data class Option(
		val position: Int,
		val label: String,
		val votes: Int,
	) : Parcelable
}
