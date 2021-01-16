/*
 * (c) 2020.
 */

package com.sorrowblue.twitlin.v2.tweets

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.testResult
import kotlinx.coroutines.flow.collect
import test.AbstractTest
import kotlin.test.Test
import kotlin.test.assertNotNull
import com.sorrowblue.twitlin.objects.Tweet as V1Tweet

class TweetsApiTest : AbstractTest {

    private val tweetField = listOf(
        TweetField.CREATED_AT,
        TweetField.TEXT,
        TweetField.ENTITIES,
        TweetField.ATTACHMENTS,
        TweetField.CONVERSATION_ID
    )

    private val mediaField = listOf(
        MediaField.URL,
        MediaField.TYPE,
        MediaField.MEDIA_KEY,
        MediaField.DURATION_MS
    )

    private val userField = listOf(UserField.PROFILE_IMAGE_URL, UserField.NAME, UserField.USERNAME)


    @Test
    fun tweetTest() = runBlocking {
        val list =
            TwitterAPI.statuses.homeTimeline(count = 40).dataOrNull()?.also { assertNotNull(it) }
                ?.map(V1Tweet::idStr) ?: return@runBlocking
        TwitterV2API.tweetsApi.tweet(
            list,
            expansions = Expansion.all(),
            mediaFields = mediaField,
            placeFields = PlaceField.all(),
            pollFields = PollField.all(),
            tweetFields = tweetField,
            userFields = userField
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun tweetsIdTest() = runBlocking {
        TwitterV2API.tweetsApi.tweet(
            "1349187270318833664",
            expansions = Expansion.all(),
            mediaFields = mediaField,
            placeFields = PlaceField.all(),
            pollFields = PollField.all(),
            tweetFields = tweetField,
            userFields = userField
        ).testResult().also(::assertNotNull)
    }

    /**
     * [See tweet](https://twitter.com/sorrowblue_sb/status/1299752429290885127)
     */
    @Test
    fun hiddenTest() = runBlocking {
        TwitterV2API.tweetsApi.hidden("1299752435855036420").testResult()
    }

    /**
     * [See tweet](https://twitter.com/sorrowblue_sb/status/1299752429290885127)
     */
    @Test
    fun unHiddenTest() = runBlocking {
        TwitterV2API.tweetsApi.hidden("1299752435855036420", isHidden = false).testResult()
    }

    @Test
    fun streamTest() = runBlocking {
        TwitterV2API.tweetsAppApi.sampleStream(tweetFields = listOf(TweetField.TEXT)).collect {
            Napier.i("streamTest = $it")
        }
    }
}
