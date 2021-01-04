/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

public actual fun resolveTweetCardType(source: String): TweetCardType {
    val document: Document = Jsoup.parse(source)
    val card = document.head().getContentByAttributeValue("name" to "twitter:card")
        ?: document.body().getContentByAttributeValue("name" to "twitter:card").orEmpty()
    return TweetCardType.parse(card)
}

private fun Element.getContentByAttributeValue(vararg keyValues: Pair<String, String>): String? {
    keyValues.forEach {
        val s = getElementsByAttributeValue(it.first, it.second).attr("content")
        if (s.isNotEmpty()) return s
    }
    return null
}
