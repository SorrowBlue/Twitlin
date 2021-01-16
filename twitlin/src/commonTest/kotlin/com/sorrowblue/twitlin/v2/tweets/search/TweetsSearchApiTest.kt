/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.tweets.PagingTweet
import test.AbstractTest
import kotlin.test.Test
import kotlin.test.assertTrue

class TweetsSearchApiTest : AbstractTest {

    @Test
    fun testSearchRecent() = runBlocking {
        val result = mutableListOf<PagingTweet>()
        searchRecent()?.let {
            result.add(it)
//            searchRecent(it.meta.nextToken)?.let {
//                result.add(it)
//                searchRecent(it.meta.nextToken)?.let {
//                    result.add(it)
//                }
//            }
        }
        assertTrue(result.isNotEmpty())
    }

    private suspend fun searchRecent(nextToken: String? = null): PagingTweet? =
        TwitterV2API.tweetsSearchApi.searchRecent(query = "python", nextToken = nextToken)
            .testResult()
}
