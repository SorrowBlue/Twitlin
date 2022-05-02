package com.sorrowblue.twitlin.api.v2.tweets.search

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.oauth2.ProjectConfig
import com.sorrowblue.twitlin.api.v2.objects.Tweet
import com.sorrowblue.twitlin.api.v2.test.TweetCap
import com.sorrowblue.twitlin.api.v2.test.shouldSuccess
import com.sorrowblue.twitlin.api.v2.tweets.TweetsApi
import com.sorrowblue.twitlin.api.v2.util.TweetNode
import com.sorrowblue.twitlin.core.objects.TweetId
import io.kotest.core.spec.style.FunSpec
import kotlin.time.ExperimentalTime

class TweetsSearchApiTest : FunSpec({

    val tweetsApi = TwitlinApiV2.getApi<TweetsApi>(ProjectConfig.oAuth2Client)
    val tweetsSearchApi = TwitlinApiV2.getApi<TweetsSearchApi>(ProjectConfig.oAuth2Client)

    @OptIn(ExperimentalTime::class)
    test("tweetsSearchApi.recent").config(tags = setOf(TweetCap)) {
        tweetsSearchApi.recent(query = "python").shouldSuccess()
    }

    @OptIn(ExperimentalTime::class)
    test("tweetsSearchApi.all").config(tags = setOf(TweetCap)) {
        tweetsSearchApi.all(query = "python").shouldSuccess()
    }

    fun TweetNode.printTree(level: Int = 0) {
        val text = tweet.text.replace("\n", "")
        val replyTo = tweet.referencedTweets?.find { it.type == Tweet.ReferenceTweet.Type.REPLIED_TO }?.id
        println("${IntArray(level).joinToString("") { "_" }}$level}: $replyTo -> ${tweet.id}: $text")
        val l = level + 1
        children.forEach {
            it.printTree(l)
        }
    }
    @OptIn(ExperimentalTime::class)
    test("BuildConversation").config(tags = setOf(TweetCap)) {
        val conversationId = TweetId("1446912819652284419")
        val root = tweetsApi.tweet(conversationId, tweetFields = TweetField.public()).dataOrNull()?.data
            ?: return@config
        val pager = tweetsSearchApi.recent(
            query = "conversation_id:${conversationId.id}",
            maxResults = 20,
            tweetFields = TweetField.public()
        ).dataOrNull()?.data ?: return@config
        TweetNode.buildConversation(root, pager).printTree()
    }

    test("tweetsSearchApi.streamRules") {
        tweetsSearchApi.streamRules().shouldSuccess()
    }

    test("tweetsSearchApi.addStreamRules, tweetsSearchApi.deleteStreamRules") {
        tweetsSearchApi.addStreamRules(
            listOf(SearchStreamRule("cat has:media", "cats with media"))
        ).onSuccess {
            tweetsSearchApi.deleteStreamRules(it.data.data?.map(SearchStreamRule::id).orEmpty())
                .shouldSuccess()
        }.shouldSuccess()
    }
})
