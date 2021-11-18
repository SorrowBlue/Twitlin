package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.ListId
import com.sorrowblue.twitlin.users.lists.ListsApi
import com.sorrowblue.twitlin.users.lists.UserList
import kotlin.jvm.JvmInline
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import test.resultLog

class ListsApiTest : AbstractTest {

    private val listsApi = Twitlin.getApi<ListsApi>(oauth1aClient)

    @Test
    fun listTest() = runBlocking {
        listsApi.list(screenName = "sorrowblue_sb").resultLog().let { assertNotNull(it) }
    }

    @Test
    fun membersTest() = runBlocking {
        listsApi.members(slug = "team", ownerScreenName = "twitterapi").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun membersShowTest() = runBlocking {
        listsApi.showMember(
            slug = "team",
            ownerScreenName = "twitterapi",
            screenName = "froginthevalley"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun membershipsTest() = runBlocking {
        listsApi.memberships(screenName = "twitter").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun ownershipsTest() = runBlocking {
        listsApi.ownerships(screenName = "twitter", count = 2).resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun showTest() = runBlocking {
        listsApi.show(listId = ListId("1364175218340556801")).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun statusesTest() = runBlocking {
        listsApi.statuses(listId = ListId("222408616"))
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun subscribersTest() = runBlocking {
        listsApi.subscribers(listId = ListId("222408616")).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun showSubscribersTest() = runBlocking {
        listsApi.showSubscribers(
            listId = ListId("222408616"),
            screenName = "sorrowblue_sb"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun subscriptionsTest() = runBlocking {
        listsApi.subscriptions(screenName = "syarihu").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun createTest() = runBlocking {
        listsApi.create("test_list", UserList.Mode.PUBLIC, "This is test list. Created by Twitlin")
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun updateTest() = runBlocking {
        listsApi.update(
            "1099005549159493632",
            name = "a2Test",
            mode = UserList.Mode.PRIVATE,
            description = "This is test list. Created by Twitlin"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun createAllMembersTest() = runBlocking {
        listsApi.createAllMembers("1345721054384193541", screenNames = listOf("Nyelvi_pien", "psvita_1000"))
            .resultLog().let { assertNotNull(it) }
    }
}

@JvmInline
value class Name(val s: String) {
    init {
        require(s.length > 0) { }
    }

    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}
