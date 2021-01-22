/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search.stream.rules

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.tweets.search.AddedSearchStreamRules
import kotlin.test.Test
import kotlin.test.assertNotEquals
import kotlinx.serialization.json.Json
import test.AbstractTest
import test.TestUtils

class RulesSerializeTest : AbstractTest {

    @Test
    fun testAddStreamRules() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/stream/rules/201_created_list_of_rules.json")
        val serializer = Response.serializer(AddedSearchStreamRules.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testAddStreamRules_invalid() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/stream/rules/200_errors_for_invalid_rule.json")
        val serializer = Response.serializer(AddedSearchStreamRules.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testAddStreamRules_not_enrolled() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/stream/rules/403_client_not_enrolled.json")
        val serializer = Response.serializer(AddedSearchStreamRules.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testAddStreamRules_list() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/stream/rules/201_created_list_of_rules.json")
        val serializer = Response.serializer(AddedSearchStreamRules.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testAddStreamRules_dry() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/stream/rules/201_create_rules_with_dry_run.json")
        val serializer = Response.serializer(AddedSearchStreamRules.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testAddStreamRules_bad_request() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/stream/rules/400_bad_request_create.json")
        val serializer = Response.serializer(AddedSearchStreamRules.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }
}
