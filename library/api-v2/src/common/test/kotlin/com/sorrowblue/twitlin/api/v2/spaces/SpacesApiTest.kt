package com.sorrowblue.twitlin.api.v2.spaces

import com.sorrowblue.twitlin.api.v2.TwitlinApiV2
import com.sorrowblue.twitlin.api.v2.field.SpaceField
import com.sorrowblue.twitlin.api.v2.field.UserField
import com.sorrowblue.twitlin.api.v2.oauth2.ProjectConfig
import com.sorrowblue.twitlin.api.v2.test.shouldSuccess
import com.sorrowblue.twitlin.core.objects.UserId
import io.kotest.core.spec.style.FunSpec

class SpacesApiTest : FunSpec({

    val spacesApi = TwitlinApiV2.getApi<SpacesApi>(ProjectConfig.oAuth2Client)

    test("spacesApi.creatorIds") {
        spacesApi.creatorIds(
            listOf(UserId("1106564757337919480")),
            expansions = Expansion.all(),
            spaceFields = SpaceField.all(),
            userFields = UserField.all()
        ).shouldSuccess()
    }

    test("spacesApi.space") {
        spacesApi.space(
            SpaceId("1OwxWVYerDwJQ"),
            expansions = Expansion.all(),
            spaceFields = SpaceField.all(),
            userFields = UserField.all()
        ).shouldSuccess()
    }

    test("spacesApi.search") {
        spacesApi.search(
            "kotlin",
            Space.State.LIVE,
            expansions = Expansion.all(),
            spaceFields = SpaceField.all(),
            userFields = UserField.all()
        ).shouldSuccess()
    }
})
