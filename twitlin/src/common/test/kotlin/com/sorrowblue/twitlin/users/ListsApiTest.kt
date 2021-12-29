package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.ListId
import com.sorrowblue.twitlin.users.lists.ListsApi
import com.sorrowblue.twitlin.users.lists.UserList
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class ListsApiTest : AbstractTest {

    private val listsApi = Twitlin.getApi<ListsApi>(oauth1aClient)

    @Test
    fun listTest() = runTest { listsApi.list(screenName = "sorrowblue_sb").resultLog().let { assertNotNull(it) } }

    @Test
    fun membersTest() = runTest {
        listsApi.members(slug = "team", ownerScreenName = "twitterapi").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun membersShowTest() = runTest {
        listsApi.showMember(
            slug = "team",
            ownerScreenName = "twitterapi",
            screenName = "froginthevalley"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun membershipsTest() = runTest {
        listsApi.memberships(screenName = "twitter").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun ownershipsTest() = runTest {
        listsApi.ownerships(screenName = "twitter", count = 2).resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun showTest() =
        runTest { listsApi.show(listId = ListId("1364175218340556801")).resultLog().let { assertNotNull(it) } }

    @Test
    fun statusesTest() = runTest {
        listsApi.statuses(listId = ListId("222408616"))
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun subscribersTest() =
        runTest { listsApi.subscribers(listId = ListId("222408616")).resultLog().let { assertNotNull(it) } }

    @Test
    fun showSubscribersTest() = runTest {
        listsApi.showSubscribers(
            listId = ListId("222408616"),
            screenName = "sorrowblue_sb"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun subscriptionsTest() = runTest {
        listsApi.subscriptions(screenName = "syarihu").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun createTest() = runTest {
        listsApi.create("test_list", UserList.Mode.PUBLIC, "This is test list. Created by Twitlin")
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun updateTest() = runTest {
        listsApi.update(
            "1099005549159493632",
            name = "a2Test",
            mode = UserList.Mode.PRIVATE,
            description = "This is test list. Created by Twitlin"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun createAllMembersTest() = runTest {
        listsApi.createAllMembers(
            "list-20730",
            ownerScreenName = "denmakun_dayo",
            screenNames = listOf("Nyelvi_pien", "psvita_1000")
        )
            .resultLog().let { assertNotNull(it) }
    }
}
