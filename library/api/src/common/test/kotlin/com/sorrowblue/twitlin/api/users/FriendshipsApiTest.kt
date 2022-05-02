package com.sorrowblue.twitlin.api.users

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.api.users.friendships.FriendshipsApi
import com.sorrowblue.twitlin.core.objects.UserId
import io.kotest.core.spec.style.FunSpec

class FriendshipsApiTest : FunSpec({

    val friendshipsApi = TwitlinApi.getApi<FriendshipsApi>(oauth1aClient)

    test("friendshipsApi.show") {
        friendshipsApi.show(sourceScreenName = "sorrowblue_sb", targetScreenName = "twitter")
            .shouldSuccess()
    }

    test("friendshipsApi.incoming") {
        friendshipsApi.incoming()
            .shouldSuccess()
    }

    test("friendshipsApi.lookup") {
        friendshipsApi.lookup(listOf(UserId("1528352858"), UserId("2905085521")))
            .shouldSuccess()
    }

    test("friendshipsApi.lookupByScreenName") {
        friendshipsApi.lookupByScreenName(listOf("andypiper", "binary_aaron", "twitterdev"))
            .shouldSuccess()
    }

    test("friendshipsApi.noRetweetsIds") {
        friendshipsApi.noRetweetsIds()
            .shouldSuccess()
    }

    test("friendshipsApi.outgoing") {
        friendshipsApi.outgoing("-1")
            .shouldSuccess()
    }
})
