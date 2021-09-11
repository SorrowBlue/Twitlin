/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.v2.client.Error
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
class SerializeTest {

    @Test
    fun emptyTest() {
        val json = "{}"
        val value = Json.decodeFromString<Unit>(json)
        println("value: $value")
        assertEquals(Unit, value)
        val string = Json.encodeToString(Unit)
        println("string: $string")
        assertEquals(json, string)
    }

    @Test
    fun errorTest() {
        val json = """
            {"title":"Forbidden","type":"about:blank","detail":"Forbidden"}
        """.trimIndent()
        val error = Json.decodeFromString<Error>(json)
        print(error)
    }
}
