package com.sorrowblue.twitlin.v2.users

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.ListId
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.v2.field.ListField
import com.sorrowblue.twitlin.v2.field.TweetField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest
import com.sorrowblue.twitlin.v2.lists.Expansion.Companion as ListsExpansion

class UserApiTest : AbstractTest {

    private val usersApi = Twitlin.getApi<UsersApi>(oauth1aClient)

    @Test
    fun usersIdTest() = runBlocking {
        usersApi.users(
            UserId("938122027231150081"),
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = Expansion.all()
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun usersIdsTest() = runBlocking {
        usersApi.users(
            listOf(UserId("2244994945"), UserId("6253282")),
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = Expansion.all()
        ).testResult().also {
            assertNotNull(it, "")
        }
    }

    @Test
    fun byUsernameTest() = runBlocking {
        usersApi.byUsername(
            "TwitterDev",
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = Expansion.all()
        ).testResult().also {
            assertNotNull(it, "")
        }
    }

    @Test
    fun testBlockingList() = runBlocking {
        usersApi.blocking(UserId("938122027231150081")).testResult()
    }

    @Test
    fun testBlocking() = runBlocking {
        usersApi.blocking(UserId("938122027231150081"), UserId("783214")).testResult()
    }

    @Test
    fun testUnBlocking() = runBlocking {
        usersApi.unBlocking(UserId("938122027231150081"), UserId("783214")).testResult()
    }

    @Test
    fun testFollowingList() = runBlocking {
        usersApi.following(UserId("938122027231150081")).testResult()
    }

    @Test
    fun testFollowing() = runBlocking {
        usersApi.following(UserId("938122027231150081"), UserId("1613102274")).testResult()
    }

    @Test
    fun testUnFollowing() = runBlocking {
        usersApi.unFollowing(UserId("938122027231150081"), UserId("1613102274")).testResult()
    }

    @Test
    fun testFollowers() = runBlocking {
        usersApi.followers(UserId("938122027231150081")).testResult()
    }

    @Test
    fun testMutingUser() = runBlocking {
        usersApi.mutingUser(UserId("938122027231150081")).testResult()
    }

    @Test
    fun testOwnedLists() = runBlocking {
        usersApi.ownedLists(UserId("938122027231150081"), ListsExpansion.all(), ListField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testMembershipsList() = runBlocking {
        usersApi.membershipsList(UserId("938122027231150081"), ListsExpansion.all(), ListField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testFollowedLists() = runBlocking {
        usersApi.followedLists(UserId("938122027231150081"), ListsExpansion.all(), ListField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testFollowAndUnFollowList() = runBlocking {
        usersApi.followList(UserId("938122027231150081"), ListId("61954239")).onSuccess {
            usersApi.unFollowList(UserId("938122027231150081"), ListId("61954239")).testResult()
        }.testResult()
    }

    @Test
    fun testPinnedLists() = runBlocking {
        usersApi.pinnedLists(UserId("938122027231150081"), ListsExpansion.all(), ListField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testPinAndUnPinList() = runBlocking {
        usersApi.pinList(UserId("938122027231150081"), ListId("222408616")).onSuccess {
            usersApi.unPinList(UserId("938122027231150081"), ListId("222408616")).testResult()
        }.testResult()
    }
}
