package com.sorrowblue.twitlin.geo

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test
import kotlin.test.assertNotNull

class GeoApiTest : AbstractTest {

    @Test
    fun testId() = runBlocking {
        TwitterAPI.geoApi.id("df51dec6f4ee2b2c")
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun testReverseGeocode() = runBlocking {
        TwitterAPI.geoApi.reverseGeocode(35.70575493447757, 139.66592095410786)
            .resultLog().let { assertNotNull(it) }
    }

    @Test
    fun testSearch() = runBlocking {
        TwitterAPI.geoApi.search(35.70575493447757, 139.66592095410786)
            .resultLog().let { assertNotNull(it) }
    }
}
