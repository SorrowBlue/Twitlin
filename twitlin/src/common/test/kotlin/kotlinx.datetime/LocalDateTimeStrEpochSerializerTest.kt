package kotlinx.datetime

import kotlin.test.Test
import kotlinx.serialization.Serializable

@Serializable
class StrEpochTestData(
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
