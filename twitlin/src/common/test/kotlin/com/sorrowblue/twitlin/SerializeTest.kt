/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.v2.client.Error
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

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
            {"title":"Forbidden","type":"about:blank","status":403,"detail":"Forbidden"}
        """.trimIndent()
        val error = Json.decodeFromString<Error>(json)
        print(error)
    }
}
