package com.sorrowblue.twitlin.api.tweets

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.objects.CollectionId
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.api.tweets.collections.CollectionsApi
import io.kotest.core.spec.style.FunSpec

class CollectionsApiTest : FunSpec({

    val collectionsApi = TwitlinApi.getApi<CollectionsApi>(oauth1aClient)

    test("collectionsApi.entries") {
        collectionsApi.entries(CollectionId("custom-539487832448843776"))
            .shouldSuccess()
    }

    test("collectionsApi.list") {
        collectionsApi.list(screenName = "twittermusic")
            .shouldSuccess()
    }

    test("collectionsApi.create") {
        collectionsApi.create("twitlintest", "twitlintest description")
            .shouldSuccess()
    }
})
