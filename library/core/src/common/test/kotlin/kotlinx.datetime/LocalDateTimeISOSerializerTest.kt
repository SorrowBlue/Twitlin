package kotlinx.datetime

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class LocalDateTimeISOSerializerTest : FunSpec({

    val json = "2021-01-13T11:22:44Z"
    val data = LocalDateTime(2021, 1, 13, 11, 22, 44)

    test("deserialize") {
        json.isoToLocalDateTime() shouldBe data
    }

    test("serialize") {
        data.encodeToISOString() shouldBe json
    }
})
