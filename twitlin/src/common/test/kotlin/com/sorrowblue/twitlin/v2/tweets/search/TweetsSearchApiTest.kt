/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.objects.ReferenceTweet
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.util.TweetNode
import kotlin.test.Test
import test.AbstractTest

class TweetsSearchApiTest : AbstractTest {

    @Test
    fun testSearchRecent() = runBlocking {
        TwitterV2API.tweetsSearchApi.searchRecent(query = "python").testResult()
    }

    /**
     * Test conversion id to build
     *
     * Tweet url: [https://twitter.com/VaporPreview/status/1408720499828432900]
     */
    @Test
    fun testBuildConversation() = runBlocking {
        val conversationId = "1408720499828432900"
        val root = TwitterV2API.tweetsApi.tweet(conversationId, tweetFields = TweetField.public()).dataOrNull()?.data
            ?: return@runBlocking
        val pager = TwitterV2API.tweetsSearchApi.searchRecent(
            query = "conversation_id:$conversationId",
            tweetFields = TweetField.public()
        ).dataOrNull()?.data ?: return@runBlocking
        TweetNode.buildConversation(root, pager).printTree()
    }


    @Test
    fun testAddStreamRules() = runBlocking {
        TwitterV2API.tweetsSearchApi.addStreamRules(
            listOf(SearchStreamRule("tostones recipe", "")),
            dryRun = true
        ).testResult()
    }

    private fun TweetNode.printTree(level: Int = 0) {
        val text = tweet.text.replace("\n", "")
        val replyTo = tweet.referencedTweets?.find { it.type == ReferenceTweet.Type.REPLIED_TO }?.id
        println("${IntArray(level).joinToString("") { "_" }}$level}: $replyTo -> ${tweet.id}: $text")
        val l = level + 1
        children.forEach {
            it.printTree(l)
        }
    }
}
