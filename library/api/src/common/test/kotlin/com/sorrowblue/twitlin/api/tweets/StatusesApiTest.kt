package com.sorrowblue.twitlin.api.tweets

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.api.tweets.statuses.StatusesApi
import com.sorrowblue.twitlin.core.objects.TweetId
import io.kotest.core.spec.style.FunSpec
import io.kotest.property.azstring
import kotlin.random.Random
import kotlinx.coroutines.withTimeoutOrNull

class StatusesApiTest : FunSpec({

    val statusesApi = TwitlinApi.getApi<StatusesApi>(oauth1aClient)

    test("statusesApi.lookup") {
        statusesApi.lookup(listOf(TweetId("1340446874583457792")))
            .shouldSuccess()
    }

    test("statusesApi.homeTimeline") {
        statusesApi.homeTimeline(10, trimUser = true, includeEntities = false)
            .shouldSuccess()
    }

    test("statusesApi.mentionsTimeline") {
        statusesApi.mentionsTimeline(10, trimUser = true, includeEntities = false)
            .shouldSuccess()
    }

    test("statusesApi.userTimeline") {
        statusesApi.userTimeline(screenName = "sorrowblue_sb", count = 10)
            .shouldSuccess()
    }

    test("statusesApi.retweetersIds") {
        statusesApi.retweetersIds(TweetId("1513385690826162179"))
            .shouldSuccess()
    }

    test("statusesApi.retweets") {
        statusesApi.retweets(TweetId("1513385690826162179"))
            .shouldSuccess()
    }

    test("statusesApi.retweetsOfMe") {
        statusesApi.retweetsOfMe()
            .shouldSuccess()
    }

    test("statusesApi.show") {
        statusesApi.show(TweetId("1513385690826162179"))
            .shouldSuccess()
    }

    test("statusesApi.update") {
        statusesApi.update("Tweet test from Twitlin. code=[${Random.azstring(10)}]")
            .shouldSuccess()
    }

    test("statusesApi.filter") {
        withTimeoutOrNull(2000) {
            statusesApi.filter(track = listOf("twitter")).collect {
                it.shouldSuccess()
            }
        }
    }
    test("statusesApi.sample") {
        withTimeoutOrNull(2000) {
            statusesApi.sample().collect {
                it.shouldSuccess()
            }
        }
    }
})

