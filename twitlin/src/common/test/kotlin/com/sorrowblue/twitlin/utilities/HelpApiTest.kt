/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utilities

import com.sorrowblue.twitlin.TwitterAPI
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDateTimeStrEpochSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import test.AbstractTest
import test.resultLog

class HelpApiTest : AbstractTest {

    @Test
    fun configuration() =
        runBlocking { assertNotNull(TwitterAPI.helpApi.configuration().resultLog()) }

    @Test
    fun languages() = runBlocking { assertNotNull(TwitterAPI.helpApi.languages().resultLog()) }

    @Test
    fun lateLimit() = runBlocking { TwitterAPI.applicationApi.rateLimitStatus().resultLog() }

    @Test
    fun testIso() {
        val date = Json.decodeFromString<Te>("{\"value\": \"1611627550\"}")
        println(date)
    }

    @Serializable
    data class Te(@Serializable(LocalDateTimeStrEpochSerializer::class) val value: LocalDateTime)
}
