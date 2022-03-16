package kotlinx.datetime

import com.sorrowblue.twitlin.core.Twitlin
import kotlin.test.Test
import kotlin.test.assertEquals

internal class LocalDateTimeRFC822SerializerTest {

    private val json = "Wed Jan 13 11:22:44 +0000 2021"
    private val data = LocalDateTime(2021, Month.JANUARY, 13, 11, 22, 44)

    @Test
    fun testDeserialize() {
        assertEquals(data, json.rfc822ToLocalDateTime())
    }

    @Test
    fun testSerialize() {
        assertEquals(json, data.toInstant(Twitlin.defaultTimeZone).formatRFC822())
    }
}
