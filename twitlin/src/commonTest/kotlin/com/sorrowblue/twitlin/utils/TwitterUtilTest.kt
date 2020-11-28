package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.objects.TwitterCard
import com.sorrowblue.twitlin.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class TwitterUtilTest {
    @Test
    fun summaryLargeImageCardTest() = runTest {
        val card =
            TweetUtil.twitterCard("https://www.orefolder.net/blog/2020/08/echo-dot-ggmm-d3/")
        println("summaryLargeImageCardTest = $card")
        assertEquals(TwitterCard.Type.SUMMARY_LARGE_IMAGE, card?.type)
    }

    @Test
    fun summaryCardTest() = runTest {
        val card = TweetUtil.twitterCard("https://github.com/SorrowBlue")
        println("summaryCardTest = $card")
        assertEquals(TwitterCard.Type.SUMMARY, card?.type)
    }

    @Test
    fun playerCardTest() = runTest {
        val card = TweetUtil.twitterCard("https://www.youtube.com/watch?v=Kauv7MVPcsA")
        println("playerCardTest = $card")
        assertEquals(TwitterCard.Type.PLAYER, card?.type)

    }
}
