package com.sorrowblue.twitlin.api.v2.lists

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.ListField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.testResult
import com.sorrowblue.twitlin.core.objects.ListId
import com.sorrowblue.twitlin.core.objects.UserId
import kotlin.test.Test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import com.sorrowblue.twitlin.api.v2.tweets.Expansion as TweetsExpansion
import com.sorrowblue.twitlin.api.v2.users.Expansion as UsersExpansion

@ExperimentalCoroutinesApi
class ListsApiTest : AbstractTest {

    private val listsApi = TwitlinApiV2.getApi<ListsApi>(oauth1aClient)

    @Test
    fun testList() = runTest {
        listsApi.list(ListId("1450834051498053632"), Expansion.all(), ListField.all(), UserField.all()).testResult()
    }

    @Test
    fun testTweets() = runTest {
        listsApi.tweets(ListId("1450834051498053632"), TweetsExpansion.all(), TweetField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testMembers() = runTest {
        listsApi.members(ListId("1450834051498053632"), UsersExpansion.all(), TweetField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testFollowers() = runTest {
        listsApi.followers(ListId("1450834051498053632"), UsersExpansion.all(), TweetField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testAddAndRemoveMember() = runTest {
        listsApi.addMember(ListId("1450834051498053632"), UserId("783214")).testResult()
        listsApi.removeMember(ListId("1450834051498053632"), UserId("783214")).testResult()
    }

    @Test
    fun testCreateAndDelete() = runTest {
        listsApi.create("name", "description", true).onSuccess {
            listsApi.update(it.data.data.id, "new_name", "new_description", false).testResult()
            listsApi.delete(it.data.data.id).testResult()
        }.testResult()
    }
}
