package com.sorrowblue.twitlin.api.v2.tweets.search

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.testResult
import com.sorrowblue.twitlin.api.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.api.v2.util.TweetNode
import com.sorrowblue.twitlin.core.objects.TweetId
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest

@ExperimentalCoroutinesApi
class TweetsSearchApiTest : AbstractTest {

    private val tweetsApi = TwitlinApiV2.getApi<TweetsApi>(oauth1aClient)
    private val tweetsSearchApi = TwitlinApiV2.getApi<TweetsSearchApi>(oauth2Client)

    @Test
    fun testSearchRecent() = runTest { tweetsSearchApi.recent(query = "python").testResult() }

    @Test
    fun testSearchAll() = runTest { tweetsSearchApi.all(query = "python").testResult() }

    /**
     * Test conversion id to build
     *
     * Tweet url: [https://twitter.com/VaporPreview/status/1408720499828432900]
     */
    @Test
    fun testBuildConversation() = runTest {
        val conversationId = TweetId("1446912819652284419")
        val root = tweetsApi.tweet(conversationId, tweetFields = TweetField.public()).dataOrNull()?.data
            ?: return@runTest
        val pager = tweetsSearchApi.recent(
            query = "conversation_id:${conversationId.id}",
            maxResults = 20,
            tweetFields = TweetField.public()
        ).dataOrNull()?.data ?: return@runTest
        TweetNode.buildConversation(root, pager).printTree()
    }

    @Test
    fun testStreamRules() = runTest {
        tweetsSearchApi.streamRules().testResult()
    }

    @Test
    fun testAddStreamRules() = runTest {
        tweetsSearchApi.addStreamRules(
            listOf(SearchStreamRule("cat has:media", "cats with media"))
        ).onSuccess {
            tweetsSearchApi.deleteStreamRules(it.data.data?.map(SearchStreamRule::id).orEmpty())
                .testResult()
        }.testResult()
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
