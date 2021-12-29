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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import com.sorrowblue.twitlin.v2.lists.Expansion.Companion as ListsExpansion

@ExperimentalCoroutinesApi
class UserApiTest : AbstractTest {

    private val usersApi = Twitlin.getApi<UsersApi>(oauth1aClient)

    @Test
    fun usersIdTest() = runTest {
        usersApi.users(
            UserId("938122027231150081"),
            tweetFields = TweetField.all(),
            userFields = UserField.all(),
            expansions = Expansion.all()
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun usersIdsTest() = runTest {
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
    fun byUsernameTest() = runTest {
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
    fun testBlockingList() = runTest {
        usersApi.blocking(UserId("938122027231150081")).testResult()
    }

    @Test
    fun testBlocking() = runTest {
        usersApi.blocking(UserId("938122027231150081"), UserId("783214")).testResult()
    }

    @Test
    fun testUnBlocking() = runTest {
        usersApi.unBlocking(UserId("938122027231150081"), UserId("783214")).testResult()
    }

    @Test
    fun testFollowingList() = runTest {
        usersApi.following(UserId("938122027231150081")).testResult()
    }

    @Test
    fun testFollowing() = runTest {
        usersApi.following(UserId("938122027231150081"), UserId("1613102274")).testResult()
    }

    @Test
    fun testUnFollowing() = runTest {
        usersApi.unFollowing(UserId("938122027231150081"), UserId("1613102274")).testResult()
    }

    @Test
    fun testFollowers() = runTest {
        usersApi.followers(UserId("938122027231150081")).testResult()
    }

    @Test
    fun testMutingUser() = runTest {
        usersApi.mutingUser(UserId("938122027231150081")).testResult()
    }

    @Test
    fun testOwnedLists() = runTest {
        usersApi.ownedLists(UserId("938122027231150081"), ListsExpansion.all(), ListField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testMembershipsList() = runTest {
        usersApi.membershipsList(UserId("938122027231150081"), ListsExpansion.all(), ListField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testFollowedLists() = runTest {
        usersApi.followedLists(UserId("938122027231150081"), ListsExpansion.all(), ListField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testFollowAndUnFollowList() = runTest {
        usersApi.followList(UserId("938122027231150081"), ListId("61954239")).onSuccess {
            usersApi.unFollowList(UserId("938122027231150081"), ListId("61954239")).testResult()
        }.testResult()
    }

    @Test
    fun testPinnedLists() = runTest {
        usersApi.pinnedLists(UserId("938122027231150081"), ListsExpansion.all(), ListField.all(), UserField.all())
            .testResult()
    }

    @Test
    fun testPinAndUnPinList() = runTest {
        usersApi.pinList(UserId("938122027231150081"), ListId("222408616")).onSuccess {
            usersApi.unPinList(UserId("938122027231150081"), ListId("222408616")).testResult()
        }.testResult()
    }
}
