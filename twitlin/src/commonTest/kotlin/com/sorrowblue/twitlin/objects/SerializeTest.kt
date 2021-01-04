/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.objects

import com.sorrowblue.twitlin.test.loadJson
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertNotNull

class SerializeTest {

    @Test
    fun userListTest() {
        val json = loadJson("/objects/users.json")
        kotlin.runCatching {
            val value = Json { ignoreUnknownKeys = true }.decodeFromString<List<User>>(json)
            print(value)
        }.onFailure {
            it.printStackTrace()
        }.getOrNull().let(::assertNotNull)
    }

    @Test
    fun tweetListTest() {
        val json = loadJson("/objects/tweets.json")
        kotlin.runCatching {
            val value = Json { ignoreUnknownKeys = true }.decodeFromString<List<Tweet>>(json)
            print(value)
        }.onFailure {
            it.printStackTrace()
        }.getOrNull().let(::assertNotNull)
    }
}
