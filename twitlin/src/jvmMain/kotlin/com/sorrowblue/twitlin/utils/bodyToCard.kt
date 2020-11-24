package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.objects.TwitterCard
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.*

actual fun resolveCard(source: String): TwitterCard? {
	val document: Document = Jsoup.parse(source)
	val head: Element = document.head()
	val body: Element by lazy { document.body() }
	val title = head.getContentByAttributeValue("property" to "og:title", "name" to "twitter:title")
		?: body.getContentByAttributeValue("property" to "og:title", "name" to "twitter:title")
		?: ""
	val url = head.getContentByAttributeValue("property" to "og:url", "name" to "twitter:url")
		?: body.getContentByAttributeValue("property" to "og:url", "name" to "twitter:url")
		?: ""
	val description =
		head.getContentByAttributeValue("property" to "og:description", "name" to "twitter:description")
			?: body.getContentByAttributeValue("property" to "og:description", "name" to "twitter:description")
			?: ""
	val image = head.getContentByAttributeValue("property" to "og:image", "name" to "twitter:image:src")
		?: body.getContentByAttributeValue("property" to "og:image", "name" to "twitter:image:src")
		?: ""
	val card = (head.getContentByAttributeValue("name" to "twitter:card")
		?: body.getContentByAttributeValue("name" to "twitter:card"))?.let {
		runCatching { TwitterCard.CardType.valueOf(it.toUpperCase(Locale.getDefault())) }.getOrElse { TwitterCard.CardType.SUMMARY }
	} ?: TwitterCard.CardType.SUMMARY
	val site = head.getContentByAttributeValue("name" to "twitter:site")
		?: body.getContentByAttributeValue("name" to "twitter:site")
		?: ""
	return TwitterCard(title, url, description, image, card, site)
}

private fun Element.getContentByAttributeValue(vararg keyValues: Pair<String, String>): String? {
	keyValues.forEach {
		val s = getElementsByAttributeValue(it.first, it.second).attr("content")
		if (s.isNotEmpty()) return s
	}
	return null
}

actual fun resolveTweetCardType(source: String): TweetCardType {
	val document: Document = Jsoup.parse(source)
	return (document.head().getContentByAttributeValue("name" to "twitter:card")
		?: document.body().getContentByAttributeValue("name" to "twitter:card"))?.let {
		runCatching { TweetCardType.valueOf(it.toUpperCase(Locale.getDefault())) }.getOrElse { TweetCardType.SUMMARY }
	} ?: TweetCardType.SUMMARY
}
