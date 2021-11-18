package com.sorrowblue.twitlin.v2.tweets.search

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.v2.util.TweetNode
import kotlin.test.Test
import test.AbstractTest

class TweetsSearchApiTest : AbstractTest {

    private val tweetsApi = Twitlin.getApi<TweetsApi>(oauth1aClient)
    private val tweetsSearchApi = Twitlin.getApi<TweetsSearchApi>(oauth1aClient)

    @Test
    fun testSearchRecent() = runBlocking {
        tweetsSearchApi.recent(query = "python").testResult()
    }

    /**
     * Test conversion id to build
     *
     * Tweet url: [https://twitter.com/VaporPreview/status/1408720499828432900]
     */
    @Test
    fun testBuildConversation() = runBlocking {
        val conversationId = TweetId("1446912819652284419")
        val root = tweetsApi.tweet(conversationId, tweetFields = TweetField.public()).dataOrNull()?.data
            ?: return@runBlocking
        val pager = tweetsSearchApi.recent(
            query = "conversation_id:${conversationId.id}",
            maxResults = 20,
            tweetFields = TweetField.public()
        ).dataOrNull()?.data ?: return@runBlocking
        TweetNode.buildConversation(root, pager).printTree()
    }


    @Test
    fun testAddStreamRules() = runBlocking {
        tweetsSearchApi.addStreamRules(
            listOf(SearchStreamRule("tostones recipe", "")),
            dryRun = true
        ).testResult()
    }

    private fun TweetNode.printTree(level: Int = 0) {
        val text = tweet.text.replace("\n", "")
        val replyTo = tweet.referencedTweets?.find { it.type == Tweet.ReferenceTweet.Type.REPLIED_TO }?.id
        println("${IntArray(level).joinToString("") { "_" }}$level}: $replyTo -> ${tweet.id}: $text")
        val l = level + 1
        children.forEach {
            it.printTree(l)
        }
    }
}
