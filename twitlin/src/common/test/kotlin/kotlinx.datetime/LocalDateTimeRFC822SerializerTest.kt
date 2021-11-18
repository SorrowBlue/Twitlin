package kotlinx.datetime

import kotlin.test.Test
import kotlinx.serialization.Serializable

@Serializable
class RFC822TestData(
    @Serializable(LocalDateTimeRFC822Serializer::class)
    override val value: LocalDateTime
) : LocalDataTimeTestData

internal class LocalDateTimeRFC822SerializerTest : AbstractLocalDateTimeSerializerTest {

    override val json = """{"value":"Wed Jan 13 11:22:44 +0000 2021"}""".trimIndent()

    @Test
    fun testDeserialize() = deserialize<RFC822TestData>()

    @Test
    fun testSerialize() = serialize(::RFC822TestData)
}
