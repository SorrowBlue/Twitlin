package com.sorrowblue.twitlin.v2.tweets

import com.sorrowblue.twitlin.TwitterAPI
import com.sorrowblue.twitlin.objects.TwitterTweet
import com.sorrowblue.twitlin.test.AbstractTest
import com.sorrowblue.twitlin.test.runTest
import com.sorrowblue.twitlin.v2.objects.AddSearchStreamRule
import com.sorrowblue.twitlin.v2.objects.ReferenceTweet
import com.sorrowblue.twitlin.v2.objects.SearchStreamRule
import com.sorrowblue.twitlin.v2.objects.Tweet
import com.sorrowblue.twitlin.v2.testResult
import com.sorrowblue.twitlin.v2.users.TwitterAPIV2
import kotlin.test.Test
import kotlin.test.assertNotNull

@TwitterAPIV2
class TweetsApiTest : AbstractTest {
	@Test
	fun timeline() = runTest {
//		val list = TwitterAPI.statuses.userTimeline("sorrowblue_sb",count = 2).getOrNull()?.map(TwitterTweet::idStr) ?: return@runTest
		val list = TwitterAPI.statuses.homeTimeline(count = 40).getOrNull()?.map(TwitterTweet::idStr) ?: return@runTest
		TwitterAPI.V2.tweetsApi.tweets(
			list,
			tweetFields = listOf(TweetField.CREATED_AT, TweetField.TEXT, TweetField.ENTITIES, TweetField.ATTACHMENTS, TweetField.CONVERSATION_ID),
			expansions = Expansion.all(),
			mediaFields = listOf(MediaField.URL, MediaField.TYPE, MediaField.MEDIA_KEY, MediaField.DURATION_MS),
			pollFields = PollField.all(),
			userFields = listOf(UserField.PROFILE_IMAGE_URL, UserField.NAME, UserField.USERNAME)
		).testResult().also(::assertNotNull)
	}

	@Test
	fun tweetsIdTest() = runTest {
		TwitterAPI.V2.tweetsApi.tweets(
			"1320424794433597440",
			tweetFields = listOf(TweetField.CREATED_AT, TweetField.TEXT, TweetField.ENTITIES, TweetField.ATTACHMENTS),
			expansions = Expansion.all(),
			mediaFields = listOf(MediaField.URL, MediaField.TYPE, MediaField.MEDIA_KEY, MediaField.DURATION_MS),
			pollFields = PollField.all(),
			userFields = listOf(UserField.PROFILE_IMAGE_URL, UserField.NAME, UserField.USERNAME)
		).testResult().also(::assertNotNull)
	}

@Test
	fun recentTest() = runTest {
		TwitterAPI.V2.tweetsApi.searchRecent(
			"conversation_id:1323199167095689216",
			tweetFields = listOf(TweetField.CREATED_AT, TweetField.TEXT, TweetField.ENTITIES, TweetField.ATTACHMENTS),
			expansions = Expansion.all(),
			mediaFields = listOf(MediaField.URL, MediaField.TYPE, MediaField.MEDIA_KEY, MediaField.DURATION_MS),
			pollFields = PollField.all(),
			userFields = listOf(UserField.PROFILE_IMAGE_URL, UserField.NAME, UserField.USERNAME)
		).testResult()?.tweets?.filterNot { it.referencedTweets?.any { it.type == ReferenceTweet.Type.RETWEETED } ?: false }?.also {
			println("list = ${it.map(Tweet::text)}")
		}
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
