package com.sorrowblue.twitlin.util

import com.sorrowblue.twitlin.objects.TwitterCard
import com.sorrowblue.twitlin.utils.TweetUtil
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class TwitterUtilTest {
	@Test
	fun summaryLargeImageCardTest() {
		val card = runBlocking { TweetUtil.twitterCard("https://www.orefolder.net/blog/2020/08/echo-dot-ggmm-d3/") }
		println("summaryLargeImageCardTest = $card")
		assertEquals(TwitterCard.CardType.SUMMARY_LARGE_IMAGE, card?.type)
	}

	@Test
	fun summaryCardTest() {
		val card = runBlocking {
			TweetUtil.twitterCard("https://github.com/SorrowBlue")
		}
		println("summaryCardTest = $card")
		assertEquals(TwitterCard.CardType.SUMMARY, card?.type)
	}

	@Test
	fun playerCardTest() {
		val card = runBlocking {
			TweetUtil.twitterCard("https://www.youtube.com/watch?v=Kauv7MVPcsA")
		}
		println("playerCardTest = $card")
		assertEquals(TwitterCard.CardType.PLAYER, card?.type)
	}
}
