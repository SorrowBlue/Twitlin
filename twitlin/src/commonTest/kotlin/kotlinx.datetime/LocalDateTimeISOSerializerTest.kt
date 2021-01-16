/*
 * (c) 2021 SorrowBlue.
 */

package kotlinx.datetime

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test

@Serializable
private class ISOTestData(
    @Serializable(LocalDateTimeISOSerializer::class)
    override val value: LocalDateTime
) : LocalDataTimeTestData

class LocalDateTimeISOSerializerTest : AbstractLocalDateTimeSerializerTest {

    override val json = """{"value":"2021-01-13T11:22:44Z"}""".trimMargin()

    @Test
    fun testDeserialize() = deserialize<ISOTestData>()

    @Test
    fun testSerialize() = serialize(::ISOTestData)

    @Test
    fun testserialize() {
        val endTime = LocalDateTime(2021, 1, 14, 9, 11, 34)
        val s = Json.encodeToString(LocalDateTimeISOSerializer, endTime)
        print(s)
    }
}
