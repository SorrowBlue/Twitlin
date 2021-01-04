/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull

class ListsApiTest : AbstractTest {

    @Test
    fun listTest() = runTest {
        TwitterAPI.listsApi.list(screenName = "twitterapi").testResult().let { assertNotNull(it) }
    }

    @Test
    fun membersTest() = runTest {
        TwitterAPI.listsApi.members(slug = "team", ownerScreenName = "twitterapi").testResult()
            .let { assertNotNull(it) }
    }

    @Test
    fun membersShowTest() = runTest {
        TwitterAPI.listsApi.showMembers(
            slug = "team",
            ownerScreenName = "twitterapi",
            screenName = "froginthevalley"
        ).testResult().let { assertNotNull(it) }
    }

    @Test
    fun membershipsTest() = runTest {
        TwitterAPI.listsApi.memberships(screenName = "twitter").testResult()
            .let { assertNotNull(it) }
    }

    @Test
    fun ownershipsTest() = runTest {
        TwitterAPI.listsApi.ownerships(screenName = "twitter", count = 2).testResult()
            .let { assertNotNull(it) }
    }

    @Test
    fun showTest() = runTest {
        TwitterAPI.listsApi.show(listId = "222408616").testResult().let { assertNotNull(it) }
    }

    @Test
    fun statusesTest() = runTest {
        TwitterAPI.listsApi.statuses(listId = "222408616")
            .testResult().let { assertNotNull(it) }
    }

    @Test
    fun subscribersTest() = runTest {
        TwitterAPI.listsApi.subscribers(listId = "222408616").testResult().let { assertNotNull(it) }
    }

    @Test
    fun showSubscribersTest() = runTest {
        TwitterAPI.listsApi.showSubscribers(
            listId = "222408616",
            screenName = "sorrowblue_sb"
        ).testResult().let { assertNotNull(it) }
    }

    @Test
    fun subscriptionsTest() = runTest {
        TwitterAPI.listsApi.subscriptions(screenName = "syarihu").testResult()
            .let { assertNotNull(it) }
    }

    @Test
    fun createTest() = runTest {
        TwitterAPI.listsApi.create(
            "test_list",
            UserList.Mode.PUBLIC,
            "This is test list. Created by Twitlin"
        ).testResult().let { assertNotNull(it) }
    }

    @Test
    fun updateTest() = runTest {
        TwitterAPI.listsApi.update(
            "1099005549159493632",
            name = "a2Test",
            mode = UserList.Mode.PRIVATE,
            description = "This is test list. Created by Twitlin"
        ).testResult().let { assertNotNull(it) }
    }

    @Test
    fun createAllMembersTest() = runTest {
        TwitterAPI.listsApi.createAllMembers(
            "1345721054384193541",
            screenNames = listOf("Nyelvi_pien", "psvita_1000")
        ).testResult().let { assertNotNull(it) }
    }
}
