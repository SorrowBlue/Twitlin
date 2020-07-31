package com.sorrowblue.twitlin.serializers

import com.soywiz.klock.*
import kotlinx.serialization.*

private const val TWITTER_PATTERN = "EEE MMM dd HH:mm:ss xx yyyy"

/**
 * [DateTimeTz]用シリアライザ
 */
object DateTimeTzSerializer : KSerializer<DateTimeTz> {
	override val descriptor =
		PrimitiveDescriptor("com.soywiz.klock.DateTimeTz", PrimitiveKind.STRING)

	override fun deserialize(decoder: Decoder) =
		DateFormat(TWITTER_PATTERN).parse(decoder.decodeString())
			.toOffset(TimezoneOffset.local(DateTime.EPOCH))

	override fun serialize(encoder: Encoder, value: DateTimeTz) =
		encoder.encodeString(value.utc.format(TWITTER_PATTERN))
}
