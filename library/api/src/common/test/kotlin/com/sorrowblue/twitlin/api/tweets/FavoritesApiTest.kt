package com.sorrowblue.twitlin.api.tweets

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.api.tweets.favorites.FavoritesApi
import io.kotest.core.spec.style.FunSpec

class FavoritesApiTest : FunSpec({

    val favoritesApi = TwitlinApi.getApi<FavoritesApi>(oauth1aClient)

    test("favoritesApi.list") {
        favoritesApi.list("STAR_ZERO", includeEntities = false)
            .shouldSuccess()
    }
})
