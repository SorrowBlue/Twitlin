package com.sorrowblue.twitlin.serializers

import com.soywiz.klock.DateFormat
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import com.soywiz.klock.TimezoneOffset
import com.soywiz.klock.parse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

private const val TWITTER_PATTERN = "EEE MMM dd HH:mm:ss xx yyyy"

/**
 * [DateTimeTz]用シリアライザ
 */
object DateTimeTzSerializer : KSerializer<DateTimeTz> {
    override val descriptor =
        PrimitiveSerialDescriptor("com.soywiz.klock.DateTimeTz", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder) =
        DateFormat(TWITTER_PATTERN).parse(decoder.decodeString())
            .toOffset(TimezoneOffset.local(DateTime.EPOCH))

    override fun serialize(encoder: Encoder, value: DateTimeTz) =
        encoder.encodeString(value.utc.format(TWITTER_PATTERN))
}
