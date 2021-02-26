/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.v2.client.Response
import com.sorrowblue.twitlin.v2.objects.Tweet
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import test.AbstractTest
import test.TestUtils
import kotlin.test.Test
import kotlin.test.assertNotEquals

class TweetSerializeTest : AbstractTest {

    @Test
    fun testOptionalDataTweet() {
        val json =
            TestUtils.loadFile("/v2/tweets/id/tweet_id_200_default_payload_no_additional_fields_requested.json")
        val serializer = Response.serializer(OptionalData.serializer(Tweet.serializer()))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataTweet_media() {
        val json =
            TestUtils.loadFile("/v2/tweets/id/tweet_id_200_request_media_fields.json")
        val serializer = Response.serializer(OptionalData.serializer(Tweet.serializer()))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataTweet_place() {
        val json =
            TestUtils.loadFile("/v2/tweets/id/tweet_id_200_request_place_fields.json")
        val serializer = Response.serializer(OptionalData.serializer(Tweet.serializer()))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataTweet_poll() {
        val json =
            TestUtils.loadFile("/v2/tweets/id/tweet_id_200_request_poll_fields.json")
        val serializer = Response.serializer(OptionalData.serializer(Tweet.serializer()))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataTweet_tweet() {
        val json =
            TestUtils.loadFile("/v2/tweets/id/tweet_id_200_request_tweet_fields.json")
        val serializer = Response.serializer(OptionalData.serializer(Tweet.serializer()))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataTweet_deleted_tweet() {
        val json =
            TestUtils.loadFile("/v2/tweets/id/tweet_id_200_deleted_tweet.json")
        val serializer = Response.serializer(OptionalData.serializer(Tweet.serializer()))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataListTweet_deleted_valid() {
        val json =
            TestUtils.loadFile("/v2/tweets/ids/tweet_ids_200_1_deleted_1_valid_tweet.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(Tweet.serializer())))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataListTweet_default() {
        val json =
            TestUtils.loadFile("/v2/tweets/ids/tweet_ids_200_multiple_tweets_default_payload.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(Tweet.serializer())))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataListTweet_tweet() {
        val json =
            TestUtils.loadFile("/v2/tweets/ids/tweet_ids_200_multiple_tweets_with_tweet_fields.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(Tweet.serializer())))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testOptionalDataListTweet_many_tweets() {
        val json =
            TestUtils.loadFile("/v2/tweets/ids/tweet_ids_400_too_many_tweets.json")
        val serializer =
            Response.serializer(OptionalData.serializer(ListSerializer(Tweet.serializer())))
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testRecentSearchTweets_default() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/recent/200_recent_search_default_payload.json")
        val serializer = Response.serializer(PagingTweet.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testRecentSearchTweets_expanded() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/recent/200_request_expanded_media_fields.json")
        val serializer = Response.serializer(PagingTweet.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testRecentSearchTweets_media_field() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/recent/200_request_expanded_media_fields.json")
        val serializer = Response.serializer(PagingTweet.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }

    @Test
    fun testRecentSearchTweets_tweet_field() {
        val json =
            TestUtils.loadFile("/v2/tweets/search/recent/200_request_tweet_fields.json")
        val serializer = Response.serializer(PagingTweet.serializer())
        val response = Json.decodeFromString(serializer, json)
            .onError { assertNotEquals("Decode failed", it.title) }
        println(response)
    }
}
