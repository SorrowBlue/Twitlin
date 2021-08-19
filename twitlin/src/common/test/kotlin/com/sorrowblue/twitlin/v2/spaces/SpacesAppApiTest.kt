package com.sorrowblue.twitlin.v2.spaces

import com.sorrowblue.twitlin.TwitterV2API
import com.sorrowblue.twitlin.v2.field.SpaceField
import com.sorrowblue.twitlin.v2.field.UserField
import com.sorrowblue.twitlin.v2.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull
import test.AbstractTest

class SpacesAppApiTest : AbstractTest {

    @Test
    fun creatorIdsTest() = runBlocking {
            TwitterV2API.spacesAppApi.creatorIds(
                listOf("1106564757337919480"),
                expansions = Expansion.all(),
                spaceFields = SpaceField.all(),
                userFields = UserField.all()
            ).testResult().also(::assertNotNull)
    }

    @Test
    fun spaceTest() = runBlocking {
            TwitterV2API.spacesAppApi.space(
                "1OwxWVYerDwJQ",
                expansions = Expansion.all(),
                spaceFields = SpaceField.all(),
                userFields = UserField.all()
            ).testResult().also(::assertNotNull)
    }
}
