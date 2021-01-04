/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.tweets.objects

import com.sorrowblue.twitlin.test.loadJson
import com.sorrowblue.twitlin.tweets.CollectionTweet
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertNotNull

class SerializeTest {

    @Test
    fun collectionTweetTest() {
        val json = loadJson("/collections/collection_tweets.json")
        kotlin.runCatching {
            val value = Json.decodeFromString<Map<String, CollectionTweet>>(json)
            print(value)
        }.onFailure {
            it.printStackTrace()
        }.getOrNull().let(::assertNotNull)
    }
}
