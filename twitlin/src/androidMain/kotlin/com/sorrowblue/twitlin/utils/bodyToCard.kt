package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.objects.TwitterCard
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.util.*

actual fun bodyToCard(body: String): TwitterCard? {
	val head: Element = Jsoup.parse(body).head()
	val title = head.getContentByAttributeValue("property", "og:title", "twitter:title") ?: return null
	val url = head.getContentByAttributeValue("property", "og:url", "twitter:url") ?: return null
	val description =
		head.getContentByAttributeValue("property", "og:description", "twitter:description") ?: return null
	val image = head.getContentByAttributeValue("property", "og:image", "twitter:image:src") ?: return null
	val card = head.getContentByAttributeValue("name", "twitter:card")?.let {
		runCatching { TwitterCard.CardType.valueOf(it.toUpperCase(Locale.getDefault())) }.getOrElse { TwitterCard.CardType.UNDEFINED }
	} ?: return null
	val site = head.getContentByAttributeValue("name", "twitter:site") ?: return null
	return TwitterCard(title, url, description, image, card, site)
}

private fun Element.getContentByAttributeValue(key: String, vararg values: String): String? {
	values.forEach {
		val s = getElementsByAttributeValue(key, it).attr("content")
		if (s.isNotEmpty()) return s
	}
	return null
}
