package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.objects.TwitterCard
import org.w3c.dom.Element
import org.w3c.dom.parsing.DOMParser

actual fun resolveCard(source: String): TwitterCard {
    val document = DOMParser().parseFromString(source, "text/html")
    val head = document.head ?: return TwitterCard("","","","",TwitterCard.Type.SUMMARY,"")
    val body = document.body ?: return TwitterCard("","","","",TwitterCard.Type.SUMMARY,"")
    val title = head.getContentByAttributeValue("property" to "og:title", "name" to "twitter:title")
        ?: body.getContentByAttributeValue("property" to "og:title", "name" to "twitter:title")
        ?: ""
    val url = head.getContentByAttributeValue("property" to "og:url", "name" to "twitter:url")
        ?: body.getContentByAttributeValue("property" to "og:url", "name" to "twitter:url")
        ?: ""
    val description =
        head.getContentByAttributeValue(
            "property" to "og:description",
            "name" to "twitter:description"
        )
            ?: body.getContentByAttributeValue(
                "property" to "og:description",
                "name" to "twitter:description"
            )
            ?: ""
    val image =
        head.getContentByAttributeValue("property" to "og:image", "name" to "twitter:image:src")
            ?: body.getContentByAttributeValue(
                "property" to "og:image",
                "name" to "twitter:image:src"
            )
            ?: ""
    val card = (head.getContentByAttributeValue("name" to "twitter:card")
        ?: body.getContentByAttributeValue("name" to "twitter:card"))?.let {
        runCatching { TwitterCard.Type.valueOf(it.toUpperCase()) }.getOrElse { TwitterCard.Type.SUMMARY }
    } ?: TwitterCard.Type.SUMMARY
    val site = head.getContentByAttributeValue("name" to "twitter:site")
        ?: body.getContentByAttributeValue("name" to "twitter:site")
        ?: ""
    return TwitterCard(title, url, description, image, card, site)
}

private fun Element.getContentByAttributeValue(vararg keyValues: Pair<String, String>): String? {
    keyValues.forEach {
        val e = querySelector("meta[${it.first}='${it.second}']")
        if (e != null) return e.getAttribute("content")
    }
    return null
}

actual fun resolveTweetCardType(source: String): TwitterCard.Type {
    TODO("Not yet implemented")
}
