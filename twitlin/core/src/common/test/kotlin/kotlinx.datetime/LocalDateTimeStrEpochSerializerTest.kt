package kotlinx.datetime

import com.sorrowblue.twitlin.core.Twitlin
import kotlin.test.Test
import kotlin.test.assertEquals

class LocalDateTimeStrEpochSerializerTest {

    private val json = "1610536964000"
    private val data = LocalDateTime(2021, 1, 13, 11, 22, 44)

    @Test
    fun testDeserialize() {
        assertEquals(
            data,
            Instant.fromEpochMilliseconds(json.padEnd(13, '0').toLong())
                .toLocalDateTime(Twitlin.defaultTimeZone)
        )
    }

    @Test
    fun testSerialize() {
        assertEquals(
            json,
            data.toInstant(Twitlin.defaultTimeZone).toEpochMilliseconds().toString()
        )
    }
}
