@file:OptIn(ExperimentalTime::class)

package com.sorrowblue.twitlin.api.v2.users

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.ListField
import com.sorrowblue.twitlin.api.v2.field.TweetField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.oauth2.ProjectConfig
import com.sorrowblue.twitlin.api.v2.test.Api
import com.sorrowblue.twitlin.api.v2.test.NonApiTest
import com.sorrowblue.twitlin.api.v2.test.shouldSuccess
import com.sorrowblue.twitlin.core.objects.ListId
import com.sorrowblue.twitlin.core.objects.UserId
import io.kotest.core.spec.style.FunSpec
import kotlin.time.ExperimentalTime
import com.sorrowblue.twitlin.api.v2.lists.Expansion.Companion as ListsExpansion

class UserApiTest : FunSpec({

    tags(Api)

    val usersApi = TwitlinApiV2.getApi<UsersApi>(ProjectConfig.oAuth2Client)

    test("usersApi.users(id)").config(tags = setOf(NonApiTest)) {
        usersApi.users(
            UserId("938122027231150081"),
            expansions = Expansion.all(),
            tweetFields = TweetField.public(),
            userFields = UserField.all()
        ).shouldSuccess()
    }

    test("usersApi.users(ids)") {
        usersApi.users(
            listOf(UserId("2244994945"), UserId("6253282")),
            expansions = Expansion.all(),
            tweetFields = TweetField.public(),
            userFields = UserField.all()
        ).shouldSuccess()
    }

    test("usersApi.byUsername") {
        usersApi.byUsername(
            "TwitterDev",
            expansions = Expansion.all(),
            tweetFields = TweetField.public(),
            userFields = UserField.all()
        ).shouldSuccess()
    }

    test("usersApi.me") {
        usersApi.me(
            expansions = Expansion.all(),
            tweetFields = TweetField.public(),
            userFields = UserField.all()
        ).shouldSuccess()
    }

    test("usersApi.blocking") {
        usersApi.blocking(ProjectConfig.me!!.id).shouldSuccess()
    }

    test("usersApi.blocking(id, targetUserId)") {
        usersApi.blocking(ProjectConfig.me!!.id, UserId("783214")).shouldSuccess()
    }

    test("usersApi.unBlocking") {
        usersApi.unBlocking(ProjectConfig.me!!.id, UserId("783214")).shouldSuccess()
    }

    test("usersApi.following") {
        usersApi.following(UserId("938122027231150081")).shouldSuccess()
    }

    test("usersApi.following(id, targetUserId)") {
        usersApi.following(ProjectConfig.me!!.id, UserId("1613102274")).shouldSuccess()
    }

    test("usersApi.unFollowing") {
        usersApi.unFollowing(ProjectConfig.me!!.id, UserId("1613102274")).shouldSuccess()
    }

    test("usersApi.followers") {
        usersApi.followers(ProjectConfig.me!!.id).shouldSuccess()
    }

    test("usersApi.mutingUser") {
        usersApi.mutingUser(ProjectConfig.me!!.id).shouldSuccess()
    }

    test("usersApi.ownedLists") {
        usersApi.ownedLists(ProjectConfig.me!!.id, ListsExpansion.all(), ListField.all(), UserField.all())
            .shouldSuccess()
    }

    test("usersApi.membershipsList") {
        usersApi.membershipsList(ProjectConfig.me!!.id, ListsExpansion.all(), ListField.all(), UserField.all())
            .shouldSuccess()
    }

    test("usersApi.followedLists") {
        usersApi.followedLists(ProjectConfig.me!!.id, ListsExpansion.all(), ListField.all(), UserField.all())
            .shouldSuccess()
    }

    test("usersApi.unFollowList") {
        usersApi.followList(ProjectConfig.me!!.id, ListId("61954239")).onSuccess {
            usersApi.unFollowList(ProjectConfig.me!!.id, ListId("61954239")).shouldSuccess()
        }.shouldSuccess()
    }

    test("usersApi.pinnedLists") {
        usersApi.pinnedLists(ProjectConfig.me!!.id, ListsExpansion.all(), ListField.all(), UserField.all())
            .shouldSuccess()
    }

    test("usersApi.unPinList") {
        usersApi.pinList(ProjectConfig.me!!.id, ListId("222408616")).onSuccess {
            usersApi.unPinList(ProjectConfig.me!!.id, ListId("222408616")).shouldSuccess()
        }.shouldSuccess()
    }
})
