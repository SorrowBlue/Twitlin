package com.sorrowblue.twitlin.api.v2.lists

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.ListField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.oauth2.ProjectConfig
import com.sorrowblue.twitlin.api.v2.test.shouldSuccess
import com.sorrowblue.twitlin.core.objects.ListId
import com.sorrowblue.twitlin.core.objects.UserId
import io.kotest.core.spec.style.FunSpec
import com.sorrowblue.twitlin.api.v2.tweets.Expansion as TweetsExpansion
import com.sorrowblue.twitlin.api.v2.users.Expansion as UsersExpansion

class ListsApiTest : FunSpec({

    val listsApi = TwitlinApiV2.getApi<ListsApi>(ProjectConfig.oAuth2Client)

    test("listsApi.list") {
        listsApi.list(ListId("1450834051498053632"), Expansion.all(), ListField.all(), UserField.all())
            .shouldSuccess()
    }

    test("listsApi.tweets") {
        listsApi.tweets(ListId("1450834051498053632"), TweetsExpansion.all(), TweetField.all(), UserField.all())
            .shouldSuccess()
    }

    test("listsApi.members") {
        listsApi.members(ListId("1450834051498053632"), UsersExpansion.all(), TweetField.all(), UserField.all())
            .shouldSuccess()
    }

    test("listsApi.followers") {
        listsApi.followers(ListId("1450834051498053632"), UsersExpansion.all(), TweetField.all(), UserField.all())
            .shouldSuccess()
    }

    test("listsApi.addMember, listsApi.removeMember") {
        listsApi.addMember(ListId("1450834051498053632"), UserId("783214")).shouldSuccess()
        listsApi.removeMember(ListId("1450834051498053632"), UserId("783214")).shouldSuccess()
    }

    test("listsApi.create, listsApi.update, listsApi.delete") {
        listsApi.create("name", "description", true).onSuccess {
            listsApi.update(it.data.data.id, "new_name", "new_description", false).shouldSuccess()
            listsApi.delete(it.data.data.id).shouldSuccess()
        }.shouldSuccess()
    }
})
