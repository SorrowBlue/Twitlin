package kotlinx.datetime

import com.sorrowblue.twitlin.core.TwitlinConfig
import io.kotest.core.spec.style.FunSpec
import kotlin.test.assertEquals

class LocalDateTimeStrEpochSerializerTest : FunSpec({

    val json = "1610536964000"
    val data = LocalDateTime(2021, 1, 13, 11, 22, 44)

    test("eeserialize") {
        assertEquals(
            data,
            Instant.fromEpochMilliseconds(json.padEnd(13, '0').toLong())
                .toLocalDateTime(TwitlinConfig.defaultTimeZone)
        )
    }

    test("serialize") {
        assertEquals(
            json,
            data.toInstant(TwitlinConfig.defaultTimeZone).toEpochMilliseconds().toString()
        )
    }
})
