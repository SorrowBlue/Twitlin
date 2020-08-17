package com.sorrowblue.twitlin.v2.serializer

import com.soywiz.klock.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

private val FORMAT =  DateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'")
//"2019-01-25T12:58:59.000Z"

/**
 * [DateTimeTz]用シリアライザ
 */
object DateTimeTzSerializer : KSerializer<DateTimeTz> {
	override val descriptor =
		PrimitiveSerialDescriptor("com.soywiz.klock.DateTimeTz", PrimitiveKind.STRING)

	override fun deserialize(decoder: Decoder) =
		FORMAT.parse(decoder.decodeString())
			.toOffset(TimezoneOffset.local(DateTime.EPOCH))

	override fun serialize(encoder: Encoder, value: DateTimeTz) =
		encoder.encodeString(value.utc.format(FORMAT))
}
