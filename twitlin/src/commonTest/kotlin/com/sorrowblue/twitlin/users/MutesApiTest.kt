/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.users

import com.sorrowblue.twitlin.TwitterAPI
import test.AbstractTest
import test.resultLog
import kotlin.test.Test
import kotlin.test.assertNotNull

class MutesApiTest : AbstractTest {

    @Test
    fun createTest() = runBlocking {
        TwitterAPI.mutesApi.create("shinya_yuunari")
            .resultLog().let { assertNotNull(it) }
    }
}
