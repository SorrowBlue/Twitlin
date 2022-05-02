package com.sorrowblue.twitlin.api.geo

import com.sorrowblue.twitlin.api.TwitlinApi
import com.sorrowblue.twitlin.api.directmessages.oauth1aClient
import com.sorrowblue.twitlin.api.test.shouldSuccess
import com.sorrowblue.twitlin.core.objects.PlaceId
import io.kotest.core.spec.style.FunSpec

class GeoApiTest : FunSpec({

    val geoApi = TwitlinApi.getApi<GeoApi>(oauth1aClient)

    test("geoApi.id") {
        geoApi.id(PlaceId("df51dec6f4ee2b2c"))
            .shouldSuccess()
    }

    test("geoApi.reverseGeocode") {
        geoApi.reverseGeocode(35.70575493447757, 139.66592095410786)
            .shouldSuccess()
    }

    test("geoApi.search") {
        geoApi.search(35.70575493447757, 139.66592095410786)
            .shouldSuccess()
    }
})
