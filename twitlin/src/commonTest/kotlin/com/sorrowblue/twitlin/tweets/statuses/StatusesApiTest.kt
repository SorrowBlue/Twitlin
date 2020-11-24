package com.sorrowblue.twitlin.tweets.statuses

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.test.testResult
import kotlin.test.Test
import kotlin.test.assertNotNull

class StatusesApiTest : AbstractTest {

	@Test
	fun lookupTest() = runTest {
		assertNotNull(TwitterAPI.statuses.lookup(listOf(1284807458699894785)).testResult())
	}

	@Test
	fun timelineTest() = runTest {
		assertNotNull(TwitterAPI.statuses.homeTimeline(count = 100).testResult())
	}

	@Test
	fun updateTest() = runTest {
		TwitterAPI.statuses.update("Tweet test from Twitlin.", "")
	}
}

