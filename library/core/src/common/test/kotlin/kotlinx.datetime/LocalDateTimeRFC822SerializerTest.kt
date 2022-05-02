package kotlinx.datetime

import io.kotest.core.spec.style.FunSpec
import kotlin.test.assertEquals

internal class LocalDateTimeRFC822SerializerTest : FunSpec({

    val json = "Wed Jan 13 11:22:44 +0000 2021"
    val data = LocalDateTime(2021, Month.JANUARY, 13, 11, 22, 44)

    test("Deserialize") {
        assertEquals(data, json.rfc822ToLocalDateTime())
    }
})
