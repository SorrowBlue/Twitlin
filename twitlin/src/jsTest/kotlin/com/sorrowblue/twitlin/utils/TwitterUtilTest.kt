package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.objects.TwitterCard
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals

class TwitterUtilTest {
	@Test
	fun bodyToCardTest() {
		val card = bodyToCard(SOURCE)
		assertEquals("Echo Dotをワイヤレスにして持ち運べる！ポータブルバッテリーベース GGMM D3", card?.title)
		assertEquals("https://www.orefolder.net/blog/2020/08/echo-dot-ggmm-d3/", card?.url)
		assertEquals("Echo Dot（第3世代）に装着してワイヤレスで使えるようになるポータブルバッテリーベース「GGMM D3」を購入してみました。これを使えば、自由にEcho Dotを持ち運んで家の中のどこでも使える",
			card?.description)
		assertEquals("https://www.orefolder.net/wp/wp-content/uploads/2020/08/20200803-ggmmdot-1.jpg", card?.image)
		assertEquals(TwitterCard.CardType.SUMMARY_LARGE_IMAGE, card?.type)
		assertEquals("@orefolder", card?.site)
	}
}

private const val SOURCE = """
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta property="og:title" content="Echo Dotをワイヤレスにして持ち運べる！ポータブルバッテリーベース GGMM D3">
	<meta property="og:description" content="Echo Dot（第3世代）に装着してワイヤレスで使えるようになるポータブルバッテリーベース「GGMM D3」を購入してみました。これを使えば、自由にEcho Dotを持ち運んで家の中のどこでも使える">
	<meta property="og:type" content="article">
	<meta property="og:url" content="https://www.orefolder.net/blog/2020/08/echo-dot-ggmm-d3/">
	<meta property="og:image" content="https://www.orefolder.net/wp/wp-content/uploads/2020/08/20200803-ggmmdot-1.jpg">
	<meta property="og:site_name" content="orefolder.net">
	<meta name="twitter:card" content="summary_large_image">
	<meta name="twitter:site" content="@orefolder">
	<meta property="og:locale" content="ja_JP">
	<meta property="fb:app_id" content="1031603360214119">
</head>
<body>
</body>
</html>
"""
