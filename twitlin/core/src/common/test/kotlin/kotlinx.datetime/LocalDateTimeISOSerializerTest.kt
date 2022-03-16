package kotlinx.datetime

import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeISOSerializerTest {

    private val json = "2021-01-13T11:22:44Z"
    private val data = LocalDateTime(2021, 1, 13, 11, 22, 44)

    @Test
    fun testDeserialize() {
        assertEquals(data, json.isoToLocalDateTime())
    }

    @Test
    fun testserialize() {
        assertEquals(json, data.encodeToISOString())
    }
}
