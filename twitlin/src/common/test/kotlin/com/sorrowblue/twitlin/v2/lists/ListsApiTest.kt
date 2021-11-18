package com.sorrowblue.twitlin.v2.lists

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.ListId
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.v2.field.ListField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.testResult
import kotlin.test.Test
import test.AbstractTest
import com.sorrowblue.twitlin.v2.users.Expansion.Companion as UsersExpansion

class ListsApiTest : AbstractTest {

    private val listsApi = Twitlin.getApi<ListsApi>(oauth1aClient)

    @Test
    fun testList() = runBlocking {
        listsApi.list(ListId("1450834051498053632"), Expansion.all(), ListField.all(), UserField.all()).testResult()
    }

    @Test
    fun testTweets() = runBlocking {
        listsApi.tweets(
            ListId("1450834051498053632"),
            com.sorrowblue.twitlin.v2.tweets.Expansion.all(),
            TweetField.all(),
            UserField.all()
        ).testResult()
    }

    @Test
    fun testMembers() = runBlocking {
        listsApi.members(
            ListId("1450834051498053632"),
            UsersExpansion.all(),
            TweetField.all(),
            UserField.all()
        ).testResult()
    }

    @Test
    fun testFollowers() = runBlocking {
        listsApi.followers(
            ListId("1450834051498053632"),
            UsersExpansion.all(),
            TweetField.all(),
            UserField.all()
        ).testResult()
    }

    @Test
    fun testAddAndRemoveMember() = runBlocking {
        listsApi.addMember(ListId("1450834051498053632"), UserId("783214")).testResult()
        listsApi.removeMember(ListId("1450834051498053632"), UserId("783214")).testResult()
    }

    @Test
    fun testCreateAndDelete() = runBlocking {
        listsApi.create("name", "description", true).onSuccess {
            listsApi.update(it.data.data.id, "new_name", "new_description", false).testResult()
            listsApi.delete(it.data.data.id).testResult()
        }.testResult()
    }
}
