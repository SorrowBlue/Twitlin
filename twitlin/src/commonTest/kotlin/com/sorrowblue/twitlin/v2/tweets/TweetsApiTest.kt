package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.objects.TwitterTweet
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.v2.objects.AddSearchStreamRule
import com.sorrowblue.twitlin.v2.objects.SearchStreamRule
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.users.TwitterAPIV2
import kotlin.test.Test
import kotlin.test.assertNotNull

@TwitterAPIV2
class TweetsApiTest : AbstractTest {
	@Test
	fun tweetsIdTest() = runTest {
		TwitterAPI.V2.tweetsApi.tweets(
			"1263145271946551300",
			tweetFields = listOf(TweetField.ENTITIES),
			expansions = Expansion.all()
		).testResult().also(::assertNotNull)
	}

	@Test
	fun tweetsIdsTest() = runTest {
		val ids =
			TwitterAPI.statuses.homeTimeline(count = 100).getOrNull()?.map(TwitterTweet::idStr)
				?: return@runTest
		TwitterAPI.V2.tweetsApi.tweets(
			ids,
			expansions = Expansion.all(),
			tweetFields = listOf(TweetField.CREATED_AT, TweetField.TEXT),
			userFields = listOf(UserField.PROFILE_IMAGE_URL, UserField.NAME, UserField.USERNAME)
		).testResult().also(::assertNotNull)
	}

	@Test
	fun hiddenTest() = runTest {
//		TwitterAPI.V2.tweetsApi.hidden("1297177018518437888").testResult()
	}

	/**
	 * TODO
	 * [] https://twitter.com/sorrowblue_sb/status/1297177014336544769
	 */
	@Test
	fun unHiddenTest() = runTest {
//		TwitterAPI.V2.tweetsApi.hidden("1297177018518437888", isHidden = false).testResult()
	}

	@Test
	fun searchStreamRulesTest() = runTest {
		TwitterAPI.V2.tweetsApi.searchStreamRules().testResult()
	}

	@Test
	fun addSearchStreamRuleTest() = runTest {
		TwitterAPI.V2.tweetsApi.searchStreamRules().dataOrNull()?.let {
			TwitterAPI.V2.tweetsApi.deleteSearchStreamRules(it.rules.map(SearchStreamRule.StreamRule::id))
				.testResult()
		}
		TwitterAPI.V2.tweetsApi.addSearchStreamRules(
			listOf(AddSearchStreamRule("cat", tag = "CAT IMAGE"))
		).testResult()
	}

	@Test
	fun deleteSearchStreamRulesTest() = runTest {
		val nowIds = TwitterAPI.V2.tweetsApi.searchStreamRules().testResult()
			?.rules?.map(SearchStreamRule.StreamRule::id).let(::assertNotNull)
		if (nowIds.isEmpty()) {
			TwitterAPI.V2.tweetsApi.addSearchStreamRules(
				listOf(
					AddSearchStreamRule(value = "cat image", tag = "CAT_IMAGE"),
					AddSearchStreamRule(value = "dog txt"),
					AddSearchStreamRule(value = "foo", tag = "BAR")
				)
			).testResult().let(::assertNotNull)
			TwitterAPI.V2.tweetsApi.searchStreamRules()
				.testResult()?.rules?.map(SearchStreamRule.StreamRule::id)?.let {
					TwitterAPI.V2.tweetsApi.deleteSearchStreamRules(it)
				}?.testResult().let(::assertNotNull)
		} else {
			TwitterAPI.V2.tweetsApi.deleteSearchStreamRules(nowIds).testResult()
				.let(::assertNotNull)
		}
	}
}
