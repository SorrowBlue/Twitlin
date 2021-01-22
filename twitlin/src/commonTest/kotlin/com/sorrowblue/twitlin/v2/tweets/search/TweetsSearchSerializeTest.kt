/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.v2.client.Response
import kotlin.test.Test
import kotlinx.serialization.json.Json
import test.AbstractTest
import test.TestUtils

class TweetsSearchSerializeTest : AbstractTest {

    @Test
    fun testAddedSearchStreamRules_200_errors_for_invalid_rule() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/add_rules_200_errors_for_invalid_rule.json")
        val response =
            Json.decodeFromString(Response.serializer(AddedSearchStreamRules.serializer()), json)
        println(response)
    }

    @Test
    fun testAddedSearchStreamRules_201_create_rules_with_dry_ru() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/stream/rules/201_create_rules_with_dry_run.json")
        val response =
            Json.decodeFromString(Response.serializer(AddedSearchStreamRules.serializer()), json)
        println(response)
    }

    @Test
    fun testAddedSearchStreamRules_201_created_list_of_rules() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/add_rules_201_created_list_of_rules.json")
        val response =
            Json.decodeFromString(Response.serializer(AddedSearchStreamRules.serializer()), json)
        println(response)
    }

    @Test
    fun testAddedSearchStreamRules_400_bad_request() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/add_rules_400_bad_request.json")
        val response =
            Json.decodeFromString(Response.serializer(AddedSearchStreamRules.serializer()), json)
        println(response)
    }

    @Test
    fun testAddedSearchStreamRules_403_forbidden() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/add_rules_403_forbidden.json")
        val response =
            Json.decodeFromString(Response.serializer(AddedSearchStreamRules.serializer()), json)
        println(response)
    }
}
