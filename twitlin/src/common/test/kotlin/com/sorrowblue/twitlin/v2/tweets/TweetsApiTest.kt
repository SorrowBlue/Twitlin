/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.v2.tweets

import com.github.aakira.napier.Napier
import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.field.Expansion
import com.sorrowblue.twitlin.v2.field.MediaField
import com.sorrowblue.twitlin.v2.field.PlaceField
import com.sorrowblue.twitlin.v2.field.PollField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
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
        TweetField.CONVERSATION_ID,
        TweetField.PUBLIC_METRICS
    )

    private val mediaField = listOf(
        MediaField.PREVIEW_IMAGE_URL,
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
            "1353899268780695552",
            expansions = Expansion.all(),
            mediaFields = mediaField,
            placeFields = PlaceField.all(),
            pollFields = PollField.all(),
            tweetFields = tweetField,
            userFields = userField
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun testIds() = runBlocking {
        TwitterV2API.tweetsApi.tweet(
            idList_100,
            tweetFields = tweetField,
            expansions = Expansion.all(),
            mediaFields = mediaField,
            pollFields = PollField.all(),
            userFields = UserField.all()
        )
    }

    /**
     * [See tweet](https://twitter.com/sorrowblue_sb/status/1299752429290885127)
     */
    @Test
    fun testHidden() = runBlocking {
        TwitterV2API.tweetsApi.hidden("1299752435855036420").testResult()
    }

    /**
     * [See tweet](https://twitter.com/sorrowblue_sb/status/1299752429290885127)
     */
    @Test
    fun testHidden_unhidden() = runBlocking {
        TwitterV2API.tweetsApi.hidden("1299752435855036420", isHidden = false).testResult()
    }

    @Test
    fun testSampleStream() = runBlocking {
        TwitterV2API.tweetsAppApi.sampleStream(tweetFields = listOf(TweetField.TEXT)).collect {
            Napier.i("streamTest = $it")
        }
    }
}

private val idList_100 = listOf(
    1353899268780695552, 1353898601420791808, 1353898498442240002, 1353898157361438720,
    1353897967942438913, 1353896222289924096, 1353896095487647745, 1353896082229452800,
    1353896012759240705, 1353895760031432707, 1353895736715354114, 1353895669891665920,
    1353895126557319170, 1353894234856644609, 1353894215348936705, 1353853039304527872,
    1353892942298165249, 1353892590182035456, 1353891466469203969, 1353891128504799232,
    1353891038809649152, 1353890983537041409, 1353890826770763776, 1353890064179228672,
    1353889703833919488, 1353889575186255872, 1353888621816762375, 1353887624990126080,
    1353885679290204161, 1353885372393132032, 1353885370333597696, 1353884400195313670,
    1353882655176056838, 1353881702351867904, 1353880645051637762, 1353880276674330624,
    1353879392468955136, 1353879344351899648, 1353878613712490497, 1353878379368333313,
    1353878128532185088, 1353877606433624066, 1353877509301964800, 1353877505736798209,
    1353877184100749318, 1353877067566223360, 1353875265420595200, 1353874213027762176,
    1353873600550301696, 1353873512180584448, 1353872844225089536, 1353872226265620480,
    1353871366185869312, 1353871295788666880, 1353871288104755200, 1353870884214202368,
    1353870721726865409, 1353870393992306689, 1353870393652637697, 1353870327055491072,
    1353870271719956482, 1353870270772113408, 1353870268394070017, 1353870260169043968,
    1353869542372503552, 1353869071461171202, 1353867835869597698, 1353867562568675329,
    1353867146640592897, 1353866279719903232, 1353865686615945216, 1353865618387214338,
    1353865528297705473, 1353865135350190080, 1353864968857239552, 1353864836006862849,
    1353864542669824000, 1353864310976462850, 1353864243997667328, 1353863998035251200,
    1353863995187367936, 1353863570564366336, 1353862755309260807, 1353862663290245121,
    1353861301026471936, 1353861264083017728, 1353860964492341250, 1353860664062693377,
    1353859632217821184, 1353859353862782976, 1353858545591013377, 1353858326312800256,
    1353858217239961600, 1353856818049470465, 1353855917700247552, 1353855915150065665,
    1353855225124118528, 1353853830115381248
).map { it.toString() }
