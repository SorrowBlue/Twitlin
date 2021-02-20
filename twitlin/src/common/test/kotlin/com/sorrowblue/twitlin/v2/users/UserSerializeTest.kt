/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.tweets.OptionalData
import kotlin.test.Test
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import test.AbstractTest
import test.TestUtils

class UserSerializeTest : AbstractTest {
    @Test
    fun testUser_sorrowblue() {
        val json = TestUtils.loadFile("v2/users/sorrowblue.json")
        val serializer = Response.serializer(User.serializer())
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUserId_default() {
        val json = TestUtils.loadFile("v2/users/id/200_default_payload.json")
        val serializer = Response.serializer(OptionalData.serializer(User.serializer()))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUserId_fields() {
        val json =
            TestUtils.loadFile("v2/users/id/200_request_fields_for_user_and_expanded_pinned_tweet.json")
        val serializer = Response.serializer(OptionalData.serializer(User.serializer()))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUserId_by_id() {
        val json = TestUtils.loadFile("v2/users/id/200_user_by_id.json")
        val serializer = Response.serializer(OptionalData.serializer(User.serializer()))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUserId_not_found() {
        val json = TestUtils.loadFile("v2/users/id/200_user_not_found.json")
        val serializer = Response.serializer(OptionalData.serializer(User.serializer()))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersIds_default() {
        val json = TestUtils.loadFile("v2/users/ids/users_ids_200_default_payload.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersIds_1_not_found() {
        val json = TestUtils.loadFile("v2/users/ids/users_ids_200_multiple_users_1_not_found.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersIds_expanded() {
        val json =
            TestUtils.loadFile("v2/users/ids/users_ids_200_request_user_and_expanded_pinned_tweet_fields.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersIds_fields() {
        val json = TestUtils.loadFile("v2/users/ids/users_ids_200_request_user_fields.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersIds_too_many_users() {
        val json = TestUtils.loadFile("v2/users/ids/users_ids_400_too_many_users.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersUsername_default() {
        val json = TestUtils.loadFile("v2/users/username/200_default_payload.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersUsername_fields() {
        val json = TestUtils.loadFile("v2/users/username/200_request_user_fields.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersUsername_not_found() {
        val json = TestUtils.loadFile("v2/users/username/200_some_users_not_found.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersUsername_expanded() {
        val json =
            TestUtils.loadFile("v2/users/username/200_success_request_fields_for_users_and_expanded_pinned_tweet.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }

    @Test
    fun testUsersUsername_too_many_users() {
        val json = TestUtils.loadFile("v2/users/username/400_too_many_users.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(User.serializer())))
        val result = Json.decodeFromString(serializer, json)
        println(result)
    }
}
