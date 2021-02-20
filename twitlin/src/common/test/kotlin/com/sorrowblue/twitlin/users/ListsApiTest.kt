/*
 * (c) 2020-2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test
import kotlin.test.assertNotNull

class ListsApiTest : AbstractTest {

    @Test
    fun listTest() = runBlocking {
        TwitterAPI.listsApi.list(screenName = "twitterapi").resultLog().let { assertNotNull(it) }
    }

    @Test
    fun membersTest() = runBlocking {
        TwitterAPI.listsApi.members(slug = "team", ownerScreenName = "twitterapi").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun membersShowTest() = runBlocking {
        TwitterAPI.listsApi.showMembers(
            slug = "team",
            ownerScreenName = "twitterapi",
            screenName = "froginthevalley"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun membershipsTest() = runBlocking {
        TwitterAPI.listsApi.memberships(screenName = "twitter").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun ownershipsTest() = runBlocking {
        TwitterAPI.listsApi.ownerships(screenName = "twitter", count = 2).resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun showTest() = runBlocking {
        TwitterAPI.listsApi.show(listId = "222408616").resultLog().let { assertNotNull(it) }
    }

    @Test
    fun statusesTest() = runBlocking {
        TwitterAPI.listsApi.statuses(listId = "222408616")
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun subscribersTest() = runBlocking {
        TwitterAPI.listsApi.subscribers(listId = "222408616").resultLog().let { assertNotNull(it) }
    }

    @Test
    fun showSubscribersTest() = runBlocking {
        TwitterAPI.listsApi.showSubscribers(
            listId = "222408616",
            screenName = "sorrowblue_sb"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun subscriptionsTest() = runBlocking {
        TwitterAPI.listsApi.subscriptions(screenName = "syarihu").resultLog()
            .let { assertNotNull(it) }
    }

    @Test
    fun createTest() = runBlocking {
        TwitterAPI.listsApi.create(
            "test_list",
            UserList.Mode.PUBLIC,
            "This is test list. Created by Twitlin"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun updateTest() = runBlocking {
        TwitterAPI.listsApi.update(
            "1099005549159493632",
            name = "a2Test",
            mode = UserList.Mode.PRIVATE,
            description = "This is test list. Created by Twitlin"
        ).resultLog().let { assertNotNull(it) }
    }

    @Test
    fun createAllMembersTest() = runBlocking {
        TwitterAPI.listsApi.createAllMembers(
            "1345721054384193541",
            screenNames = listOf("Nyelvi_pien", "psvita_1000")
        ).resultLog().let { assertNotNull(it) }
    }
}
