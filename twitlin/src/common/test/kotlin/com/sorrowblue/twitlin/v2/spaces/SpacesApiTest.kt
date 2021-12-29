package com.sorrowblue.twitlin.v2.spaces

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.UserId
import com.sorrowblue.twitlin.v2.field.SpaceField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest

@ExperimentalCoroutinesApi
class SpacesApiTest : AbstractTest {

    private val spacesApi = Twitlin.getApi<SpacesApi>(oauth2Client)

    @Test
    fun creatorIdsTest() = runTest {
        spacesApi.creatorIds(
            listOf(UserId("1106564757337919480")),
            expansions = Expansion.all(),
            spaceFields = SpaceField.all(),
            userFields = UserField.all()
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun spaceTest() = runTest {
        spacesApi.space(
            SpaceId("1OwxWVYerDwJQ"),
            expansions = Expansion.all(),
            spaceFields = SpaceField.all(),
            userFields = UserField.all()
        ).testResult().also(::assertNotNull)
    }

    @Test
    fun searchTest() = runTest {
        spacesApi.search(
            "hello",
            Space.State.LIVE,
            expansions = Expansion.all(),
            spaceFields = SpaceField.all(),
            userFields = UserField.all()
        ).testResult().also(::assertNotNull)
    }
}
