package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.api.users.lists.ListsApi
import com.sorrowblue.twitlin.api.users.lists.UserList
import com.sorrowblue.twitlin.core.objects.ListId
import io.kotest.core.spec.style.FunSpec

class ListsApiTest : FunSpec({

    val listsApi = TwitlinApi.getApi<ListsApi>(oauth1aClient)

    test("listsApi.list") {
        listsApi.list(screenName = "sorrowblue_sb")
            .shouldSuccess()
    }

    test("listsApi.members") {
        listsApi.members(slug = "team", ownerScreenName = "twitterapi")
            .shouldSuccess()
    }

    test("listsApi.showMember") {
        listsApi.showMember(
            slug = "team",
            ownerScreenName = "twitterapi",
            screenName = "froginthevalley"
        ).shouldSuccess()
    }

    test("listsApi.memberships") {
        listsApi.memberships(screenName = "twitter")
            .shouldSuccess()
    }


    test("listsApi.ownerships") {
        listsApi.ownerships(screenName = "twitter", count = 2)
            .shouldSuccess()
    }

    test("listsApi.show") {
        listsApi.show(listId = ListId("1364175218340556801"))
            .shouldSuccess()
    }

    test("listsApi.statuses") {
        listsApi.statuses(listId = ListId("222408616"))
            .shouldSuccess()
    }

    test("listsApi.subscribers") {
        listsApi.subscribers(listId = ListId("222408616"))
            .shouldSuccess()
    }

    test("listsApi.showSubscribers") {
        listsApi.showSubscribers(
            listId = ListId("222408616"),
            screenName = "sorrowblue_sb"
        ).shouldSuccess()
    }

    test("listsApi.subscriptions") {
        listsApi.subscriptions(screenName = "syarihu")
            .shouldSuccess()
    }

    test("listsApi.create") {
        listsApi.create("test_list", UserList.Mode.PUBLIC, "This is test list. Created by Twitlin")
            .shouldSuccess()
    }

    test("listsApi.update") {
        listsApi.update(
            "1099005549159493632",
            name = "a2Test",
            mode = UserList.Mode.PRIVATE,
            description = "This is test list. Created by Twitlin"
        ).shouldSuccess()
    }

    test("listsApi.createAllMembers") {
        listsApi.createAllMembers(
            "list-20730",
            ownerScreenName = "denmakun_dayo",
            screenNames = listOf("Nyelvi_pien", "psvita_1000")
        ).shouldSuccess()
    }
})
