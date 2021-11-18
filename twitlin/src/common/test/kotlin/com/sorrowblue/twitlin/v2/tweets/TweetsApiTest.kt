package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.TweetId
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.objects.CashTag
import com.sorrowblue.twitlin.v2.objects.Hashtag
import com.sorrowblue.twitlin.v2.objects.Mention
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.objects.User
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.users.Exclude
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import test.AbstractTest

class TweetsApiTest : AbstractTest {

    private val tweetsApi = Twitlin.getApi<TweetsApi>(oauth1aClient)
    private val tweetsAppApi = Twitlin.getApi<TweetsApi>(oauth2Client)
    private val statusesApi = Twitlin.getApi<StatusesApi>(oauth1aClient)

    private val SORROWBLUE_SB = UserId("938122027231150081")
    private val HIDDEN_TWEET = TweetId("1299752435855036420")
    private val TWEET = TweetId("1441013982161031168")

    /**
     * [See tweet](https://twitter.com/sorrowblue_sb/status/1299752429290885127)
     */
    @Test
    fun testHidden() = runBlocking {
        tweetsApi.hidden(HIDDEN_TWEET).testResult()
    }

    /**
     * [See tweet](https://twitter.com/sorrowblue_sb/status/1299752429290885127)
     */
    @Test
    fun testUnHidden() = runBlocking {
        tweetsApi.hidden(HIDDEN_TWEET, false).testResult()
    }

    @Test
    fun testUnLikes() = runBlocking {
        tweetsApi.unLikes(SORROWBLUE_SB, TWEET).testResult()
    }

    @Test
    fun testLikingUsers() = runBlocking {
        tweetsApi.likingUsers(TweetId("1394925800470814720")).testResult()
    }

    @Test
    fun testLikedTweets() = runBlocking {
        tweetsApi.likedTweets(SORROWBLUE_SB).testResult()
    }

    @Test
    fun testLikes() = runBlocking {
        tweetsApi.likes(SORROWBLUE_SB, TWEET).testResult()
    }

    @Test
    fun testUnRetweet() = runBlocking {
        tweetsApi.unRetweet(SORROWBLUE_SB, TWEET).testResult()
    }

    @Test
    fun testRetweetedBy() = runBlocking {
        tweetsApi.retweetedBy(TweetId("1428326155342409728")).testResult()
    }

    @Test
    fun testRetweet() = runBlocking {
        tweetsApi.retweet(SORROWBLUE_SB, TWEET).testResult()
    }

    @Test
    fun testSampleStream() {
        runCatching {
            runBlocking {
                var c = 0
                tweetsAppApi.sampleStream(tweetFields = listOf(TweetField.TEXT))
                    .collect {
                        if (100 < c) {
                            cancel("Manual cancel")
                        }
                        c++
                    }
            }
        }.onFailure {
            assertTrue(it is CancellationException)
        }
    }

    @Test
    fun testMentions() = runBlocking {
        tweetsApi.mentions(UserId("986174595660005377")).testResult()
    }

    @Test
    fun testTweets() = runBlocking {
        tweetsApi.tweets(
            UserId("1627919538"),
            exclude = Exclude.REPLIES,
            mediaFields = MediaField.public(),
            tweetFields = TweetField.public(),
            userFields = UserField.all(),
            pollFields = PollField.all(),
            expansions = Expansion.all(),
            placeFields = PlaceField.all(),
            maxResults = 100
        ).testResult()
    }

    @Test
    fun testMentionsByUsername() = runBlocking {
        tweetsApi.mentionsByUsername("sorrowblue_sb").testResult()
    }

    @Test
    fun testTweetsByUsername() = runBlocking {
        tweetsApi.tweetsByUsername("sorrowblue_sb").testResult()
    }

    @Test
    fun testTweet_id() = runBlocking {
        tweetsApi.tweet(
            TweetId("1446829727184941056"),
            expansions = Expansion.all(),
            mediaFields = MediaField.public(),
            placeFields = PlaceField.all(),
            pollFields = PollField.all(),
            tweetFields = TweetField.public(),
            userFields = UserField.all()
        ).testResult().also(::assertNotNull)?.let {
            println("formatted text: \n${it.data.formattedText}")
        }
    }

    @Test
    fun testTweet_ids() = runBlocking {
        tweetsApi.tweet(
            idList_100,
            expansions = Expansion.all(),
            mediaFields = MediaField.public(),
            placeFields = PlaceField.all(),
            pollFields = PollField.all(),
            tweetFields = TweetField.public(),
            userFields = UserField.all()
        )
    }

    @Test
    fun testTweet() = runBlocking {
        tweetsApi.tweet("Test reply tweet from twitlin dm", directMessageUserId = SORROWBLUE_SB).testResult()
    }
}

private val idList_100 = listOf(
    1442053113846796289,
    1442053060830773249,
    1442052535636815884,
    1442052179657826305,
    1442051373885886470,
    1442051361672085507,
    1442050898843168769,
    1442050062905843716,
    1442049887579742210,
    1442049695086379011,
    1442049498507710474,
    1442049361660112900,
    1442047233688358914,
    1442047061344460809,
    1442046285230473226,
    1442046138345934851,
    1442045922825814020,
    1442045845931659264,
    1442045678402736128,
    1442044421936082948,
    1442044297784676356,
    1442044192734150657,
    1442044130826207233,
    1442044129731514380,
    1442043767893082112,
    1442043660338487297,
    1442043387972964370,
    1442042091282964483,
    1442042054364721153,
    1442041662654484491,
    1442041608396967944,
    1442041410773942282,
    1442041162559152129,
    1442041057802285058,
    1442040932388327424,
    1442040322347782144,
    1442039661287784449,
    1442039618396778502,
    1442038183428976651,
    1442037186161831942,
    1442037026799316996,
    1442036346533474310,
    1442036227532746752,
    1442036216149340164,
    1442035900536422401,
    1442035896916668418,
    1442035089408335879,
    1442034400695177224,
    1442033641102589957,
    1442033190772764680,
    1442033028851650568,
    1442032972467617799,
    1442032586830745607,
    1442032500608405509,
    1442031383182864388,
    1442031377466069001,
    1442031002956681218,
    1442030969213440004,
    1442030524449521669,
    1442029945849479170,
    1442029008514809856,
    1442028669623439369,
    1442028630083702784,
    1442028560386977792,
    1442028508180455425,
    1442028144999874562,
    1442026948713017345,
    1442026818870005765,
    1442026672899776515,
    1442026580897779712,
    1442026480473542658,
    1442025631999430672,
    1442025609081675779,
    1442025510490361868,
    1442025408401010693,
    1442025226724777984,
    1442025167123718146,
    1442025001733935108,
    1442024966321434629,
    1442024394620948483,
    1442024151494004740,
    1442023114896916483,
    1442022878598230016,
    1442022620182896647,
    1442022390834159619,
    1442022360513593346,
    1442022025854263296,
    1442021284607528960,
    1442020636931489797,
    1442020586377469958,
    1442020135275937794,
    1442019788042113025,
    1442019163623477248,
    1442018857099542529,
    1442018725884956675,
    1442018709153804288,
    1442017629493858313,
    1442017607926697990,
    1442017406428139522,
    1442017210013138944
).map { TweetId(it.toString()) }

val Tweet.formattedText: String
    get() {
        if (entities == null) return text
        var text = text
        entities?.annotations
        val entity = entities!!.annotations.orEmpty() + entities!!.cashtags.orEmpty() +
                entities!!.hashtags.orEmpty() + entities!!.mentions.orEmpty() + entities!!.urls.orEmpty()
        entity.sortedByDescending { it.start }.distinctBy { it.start }.forEach {
            val replacement = when (it) {
                is CashTag -> "<a href=\"https://twitter.com/search?q=%24${it.tag}&src=cashtag_click\">$${it.tag}</a>"
                is Hashtag -> "<a href=\"https://twitter.com/hashtag/${it.tag}&src=hashtag_click\">#${it.tag}</a>"
                is Mention -> "<a href=\"https://twitter.com/${it.username}\">@${it.username}</a>"
                is Tweet.Entities.Annotation -> null
                is Tweet.Entities.Url ->
                    when {
                        it.displayUrl?.indexOf("pic.twitter.com") != -1 -> ""
                        it.title != null && it.description != null ->
                            if (it.end == text.lastIndex) "" else "<a href=\"${it.url}\">${it.displayUrl}</a>"
                        else -> "<a href=\"${it.url}\">${it.displayUrl}</a>"
                    }
                is User.Entities.Url -> "<a href=\"${it.url}\">${it.displayUrl}</a>"
                else -> null
            }
            replacement?.let { _ ->
                text = text.replaceRange(it.start, it.end, replacement)
            }
        }
        return text
    }
