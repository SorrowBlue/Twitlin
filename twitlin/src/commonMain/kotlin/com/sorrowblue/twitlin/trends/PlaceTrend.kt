package com.sorrowblue.twitlin.trends

import com.soywiz.klock.*
import kotlinx.serialization.*

@Serializable
data class PlaceTrend(
	val trends: List<Trend>,
	@SerialName("as_of")
	@Serializable(DateTimeTzSerializer2::class)
	val asOf: DateTimeTz,
	@SerialName("created_at")
	@Serializable(DateTimeTzSerializer2::class)
	val createdAt: DateTimeTz,
	val locations: List<Location>
) {
	@Serializable
	data class Location(
		val name: String,
		val woeid: Int
	)
}

private object DateTimeTzSerializer2 : KSerializer<DateTimeTz> {
	private const val TWITTER_PATTERN = "yyyy-MM-ddTHH:mm:ssZ"
	override val descriptor = PrimitiveDescriptor("com.soywiz.klock.DateTimeTz2", PrimitiveKind.STRING)
	override fun deserialize(decoder: Decoder) =
		DateFormat(TWITTER_PATTERN).parse(decoder.decodeString()).toOffset(TimezoneOffset.local(DateTime.EPOCH))

	override fun serialize(encoder: Encoder, value: DateTimeTz) =
		encoder.encodeString(value.utc.format(TWITTER_PATTERN))
}
