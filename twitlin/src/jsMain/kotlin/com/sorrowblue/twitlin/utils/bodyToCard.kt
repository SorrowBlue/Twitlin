package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.objects.TwitterCard
import org.w3c.dom.Element
import org.w3c.dom.parsing.DOMParser

actual fun bodyToCard(body: String): TwitterCard? {
	val head = DOMParser().parseFromString(body, "text/html").head ?: return null
	val title = head.getContentByAttributeValue("property", "og:title", "twitter:title") ?: return null
	val url = head.getContentByAttributeValue("property", "og:url", "twitter:url") ?: return null
	val description =
		head.getContentByAttributeValue("property", "og:description", "twitter:description") ?: return null
	val image = head.getContentByAttributeValue("property", "og:image", "twitter:image:src") ?: return null
	val card = head.getContentByAttributeValue("name", "twitter:card")?.let {
		runCatching { TwitterCard.CardType.valueOf(it.toUpperCase()) }.getOrElse { TwitterCard.CardType.UNDEFINED }
	} ?: return null
	val site = head.getContentByAttributeValue("name", "twitter:site") ?: return null
	return TwitterCard(title, url, description, image, card, site)
}

private fun Element.getContentByAttributeValue(key: String, vararg values: String): String? {
	values.forEach {
		val e = querySelector("meta[$key='$it']")
		if (e != null) return e.getAttribute("content")
	}
	return null
}
