package kotlinx.datetime

import com.sorrowblue.twitlin.Twitlin
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface AbstractLocalDateTimeSerializerTest {

    val source
        get() = LocalDateTime(2021, 1, 13, 11, 22, 44)
            .toInstant(Twitlin.defaultTimeZone)
            .toLocalDateTime(Twitlin.defaultTimeZone)

    val json: String

    @BeforeTest
    fun setUp() {
        Twitlin.defaultTimeZone = TimeZone.UTC
    }
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T : LocalDataTimeTestData> AbstractLocalDateTimeSerializerTest.serialize(
    noinline body: (source: LocalDateTime) -> T
) {
    assertEquals(json, Json.encodeToString(body.invoke(source)))
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T : LocalDataTimeTestData> AbstractLocalDateTimeSerializerTest.deserialize() {
    val json = Json.decodeFromString<T>(json)
    assertEquals(source, json.value)
}
