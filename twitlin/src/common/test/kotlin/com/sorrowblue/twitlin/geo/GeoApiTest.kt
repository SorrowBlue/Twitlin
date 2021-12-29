package com.sorrowblue.twitlin.geo

import com.sorrowblue.twitlin.Twitlin
import com.sorrowblue.twitlin.objects.PlaceId
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import test.AbstractTest
import test.resultLog

@ExperimentalCoroutinesApi
class GeoApiTest : AbstractTest {

    private val geoApi = Twitlin.getApi<GeoApi>(oauth1aClient)

    @Test
    fun testId() = runTest {
        geoApi.id(PlaceId("df51dec6f4ee2b2c"))
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun testReverseGeocode() = runTest {
        geoApi.reverseGeocode(35.70575493447757, 139.66592095410786)
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun testSearch() = runTest {
        geoApi.search(35.70575493447757, 139.66592095410786)
            .resultLog().let { assertNotNull(it) }
    }
}
