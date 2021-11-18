package test

import kotlin.random.Random
import kotlin.test.Test
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class ATest {

    @OptIn(ExperimentalTime::class)
    @Test
    fun test() {
        measureTime {
            repeat(100000) {
                Random.nextBytes(128).toHexString()
            }
        }.toDouble(DurationUnit.MILLISECONDS).also { println("hex: $it") }
        measureTime {
            repeat(100000) {
                Random.nextBytes(128).decodeToString()
            }
        }.toDouble(DurationUnit.MILLISECONDS).also { println("decodeToString: $it") }
    }
    private fun ByteArray.toHexString() =
        joinToString("") { (0xFF and it.toInt()).toString(16).padStart(2, '0') }

    private fun String.hexToByteArray(): ByteArray {
        return ByteArray(length / 2) {
            val pointer = it * 2
            hexStringToByte(substring(pointer, pointer + 2))
        }
    }
    fun hexStringToByte(hexString: String) = hexString.toInt(16).toByte()
}
