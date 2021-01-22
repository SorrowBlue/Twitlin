/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.tweets.PagingTweet
import kotlin.test.Test
import kotlin.test.assertTrue
import test.AbstractTest

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

    @Test
    fun testAddStreamRules() = runBlocking {
        TwitterV2API.tweetsSearchApi.addStreamRules(
            listOf(
                SearchStreamRule("tostones recipe", "")
            ), dryRun = true
        ).testResult()
    }

    private suspend fun searchRecent(nextToken: String? = null): PagingTweet? =
        TwitterV2API.tweetsSearchApi.searchRecent(query = "python", nextToken = nextToken)
            .testResult()

    @Test
    fun testTweetsId() = runBlocking {
        TwitterV2API.tweetsApi.tweet(
            "1263145271946551300",
            expansions = listOf(Expansion.AUTHOR_ID, Expansion.ATTACHMENTS_MEDIA_KEYS),
            mediaFields = listOf(
                MediaField.URL,
                MediaField.PREVIEW_IMAGE_URL,
                MediaField.DURATION_MS,
                MediaField.HEIGHT,
                MediaField.MEDIA_KEY,
                MediaField.WIDTH,
                MediaField.TYPE,
                MediaField.PUBLIC_METRICS,
            ),
            tweetFields = listOf(TweetField.ATTACHMENTS, TweetField.AUTHOR_ID, TweetField.ENTITIES)
        )
            .testResult()
    }
}
