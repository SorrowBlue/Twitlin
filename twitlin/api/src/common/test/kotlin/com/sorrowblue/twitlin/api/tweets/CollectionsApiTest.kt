package com.sorrowblue.twitlin.api.tweets

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.objects.CollectionId
import com.sorrowblue.twitlin.api.tweets.collections.CollectionsApi
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class CollectionsApiTest : AbstractTest {

    private val collectionsApi = TwitlinApi.getApi<CollectionsApi>(oauth1aClient)

    @Test
    fun entriesTest() = runTest {
        collectionsApi.entries(CollectionId("custom-539487832448843776"))
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun listTest() = runTest {
        collectionsApi.list(screenName = "twittermusic")
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun createTest() = runTest {
        collectionsApi.create("twitlintest", "twitlintest description")
            .resultLog().let { assertNotNull(it) }
    }
}
