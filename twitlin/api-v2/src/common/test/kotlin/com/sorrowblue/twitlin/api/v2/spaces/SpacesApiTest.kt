package com.sorrowblue.twitlin.api.v2.spaces

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.SpaceField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.testResult
import com.sorrowblue.twitlin.core.objects.UserId
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest

@ExperimentalCoroutinesApi
class SpacesApiTest : AbstractTest {

    private val spacesApi = TwitlinApiV2.getApi<SpacesApi>(oauth2Client)

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
