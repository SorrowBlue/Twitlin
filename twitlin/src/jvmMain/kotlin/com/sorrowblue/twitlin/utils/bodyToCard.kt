package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.objects.TwitterCard
import org.jsoup.Jsoup

actual fun bodyToCard(body: String): TwitterCard {
	val jsoup = Jsoup.parse(body)
	val head = jsoup.head()
	val title = head.getElementsByAttributeValue("property", "og:title")
		?: head.getElementsByAttributeValue("property", "twitter:title")
	val url = head.getElementsByAttributeValue("property", "og:url")
		?: head.getElementsByAttributeValue("property", "twitter:url")
	val description = head.getElementsByAttributeValue("property", "og:description")
		?: head.getElementsByAttributeValue("property", "twitter:description")
	val image = head.getElementsByAttributeValue("property", "og:image")
		?: head.getElementsByAttributeValue("property", "twitter:image:src")
	val card = head.getElementsByAttributeValue("name", "twitter:card")
	val site = head.getElementsByAttributeValue("name", "twitter:site")
	return TwitterCard(
		title?.attr("content") ?: "",
		url?.attr("content") ?: "",
		description?.attr("content") ?: "",
		image?.attr("content") ?: "",
		card?.attr("content")?.let { TwitterCard.CardType.valueOf2(it) } ?: TwitterCard.CardType.UNDEFINED,
		site?.attr("content") ?: ""
	)
}
