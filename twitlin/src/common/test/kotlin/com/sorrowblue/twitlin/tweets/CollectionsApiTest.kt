package com.sorrowblue.twitlin.tweets

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.CollectionId
import com.sorrowblue.twitlin.tweets.collections.CollectionsApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import test.resultLog

class CollectionsApiTest : AbstractTest {

    private val collectionsApi = Twitlin.getApi<CollectionsApi>(oauth1aClient)

    @Test
    fun entriesTest() = runBlocking {
        collectionsApi.entries(CollectionId("custom-539487832448843776"))
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun listTest() = runBlocking {
        collectionsApi.list(screenName = "twittermusic")
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun createTest() = runBlocking {
        collectionsApi.create("twitlintest", "twitlintest description")
            .resultLog().let { assertNotNull(it) }
    }
}
