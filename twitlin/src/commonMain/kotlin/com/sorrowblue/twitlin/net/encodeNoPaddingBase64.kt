package com.sorrowblue.twitlin.net

import io.ktor.util.encodeBase64
import io.ktor.utils.io.core.ByteReadPacket
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.readAvailable
import io.ktor.utils.io.core.writeText

private const val BASE64_MASK: Byte = 0x3f
private const val BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

internal fun String.encodeNoPaddingBase64(): String =
	buildPacket { writeText(this@encodeNoPaddingBase64) }.encodeNoPaddingBase64()

private fun ByteArray.clearFrom(from: Int) = (from until size).forEach { this[it] = 0 }

private fun Int.toBase64(): Char = BASE64_ALPHABET[this]

private fun ByteReadPacket.encodeNoPaddingBase64(): String = buildString {
	val data = ByteArray(3)
	while (remaining > 0) {
		val read = readAvailable(data)
		data.clearFrom(read)
		val padSize = (data.size - read) * 8 / 6
		val chunk = ((data[0].toInt() and 0xFF) shl 16) or
				((data[1].toInt() and 0xFF) shl 8) or
				(data[2].toInt() and 0xFF)
		for (index in data.size downTo padSize) {
			val char = (chunk shr (6 * index)) and BASE64_MASK.toInt()
			append(char.toBase64())
		}
	}
}
