/*
 * (c) 2020-2021 SorrowBlue.
 */

package kotlinx.datetime

import kotlinx.serialization.Serializable
import kotlin.test.Test

@Serializable
private class StrEpochTestData(
    @Serializable(LocalDateTimeStrEpochSerializer::class)
    override val value: LocalDateTime
) : LocalDataTimeTestData

class LocalDateTimeStrEpochSerializerTest : AbstractLocalDateTimeSerializerTest {

    override val json = """{"value":"1610536964000"}""".trimMargin()

    @Test
    fun testDeserialize() = deserialize<StrEpochTestData>()

    @Test
    fun testSerialize() = serialize { StrEpochTestData(it) }
}
