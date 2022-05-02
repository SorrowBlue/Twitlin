package com.sorrowblue.twitlin.api.v2.tweets

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.MediaField
import com.sorrowblue.twitlin.api.v2.field.PlaceField
import com.sorrowblue.twitlin.api.v2.field.PollField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.oauth2.ProjectConfig
import com.sorrowblue.twitlin.api.v2.test.Api
import com.sorrowblue.twitlin.api.v2.test.TweetCap
import com.sorrowblue.twitlin.api.v2.test.shouldSuccess
import com.sorrowblue.twitlin.api.v2.users.Exclude
import com.sorrowblue.twitlin.core.objects.TweetId
import com.sorrowblue.twitlin.core.objects.UserId
import io.kotest.core.spec.style.FunSpec
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.cancel

class TweetsApiTest : FunSpec({

    tags(Api)

    val tweetsApi = TwitlinApiV2.getApi<TweetsApi>(ProjectConfig.oAuth2Client)
    val tweetsAppApi = TwitlinApiV2.getApi<TweetsApi>(ProjectConfig.oAuth2Client)

    val sorrowBlueSB = UserId("938122027231150081")
    val hiddenTweetId = TweetId("1299752435855036420")
    val tweetId = TweetId("1441013982161031168")

    test("tweetsApi.hidden") {
        tweetsApi.hidden(hiddenTweetId, true).shouldSuccess()
        tweetsApi.hidden(hiddenTweetId, false).shouldSuccess()
    }

    test("tweetsApi.unLikes") {
        tweetsApi.unLikes(sorrowBlueSB, tweetId).shouldSuccess()
    }

    test("tweetsApi.likingUsers") {
        tweetsApi.likingUsers(TweetId("1394925800470814720")).shouldSuccess()
    }

    test("tweetsApi.likedTweets") {
        tweetsApi.likedTweets(sorrowBlueSB).shouldSuccess()
    }

    test("tweetsApi.likes") {
        tweetsApi.likes(sorrowBlueSB, tweetId).shouldSuccess()
    }

    test("tweetsApi.retweetedBy") {
        tweetsApi.retweetedBy(TweetId("1428326155342409728")).shouldSuccess()
    }

    test("tweetsApi.retweet") {
        tweetsApi.retweet(sorrowBlueSB, tweetId).shouldSuccess()
    }

    test("tweetsApi.unRetweet") {
        tweetsApi.unRetweet(sorrowBlueSB, tweetId).shouldSuccess()
    }

    @OptIn(ExperimentalTime::class)
    test("tweetsAppApi.sampleStream").config(tags = setOf(TweetCap)) {
        tweetsAppApi.sampleStream(tweetFields = TweetField.public()).collect {
            it.shouldSuccess()
            cancel("Manual cancel")
        }
    }

    test("tweetsApi.mentions") {
        tweetsApi.mentions(UserId("986174595660005377")).shouldSuccess()
    }

    test("tweetsApi.tweets") {
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
        ).shouldSuccess()
    }

    test("tweetsApi.tweet") {
        tweetsApi.tweet(
            listOf(TweetId("1446829727184941056"), TweetId("1505546005965512705")),
            expansions = Expansion.all(),
            mediaFields = MediaField.public(),
            placeFields = PlaceField.all(),
            pollFields = PollField.all(),
            tweetFields = TweetField.public(),
            userFields = UserField.all()
        ).shouldSuccess()
    }

    test("POST tweetsApi.tweet") {
        tweetsApi.tweet("Test reply tweet from twitlin dm", directMessageUserId = sorrowBlueSB).shouldSuccess()
    }
})
