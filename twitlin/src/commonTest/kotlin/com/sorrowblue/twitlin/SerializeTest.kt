package com.sorrowblue.twitlin

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
}
