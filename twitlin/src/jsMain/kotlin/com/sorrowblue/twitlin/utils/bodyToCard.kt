/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

import org.w3c.dom.Element
import org.w3c.dom.parsing.DOMParser

public actual fun resolveTweetCardType(source: String): TweetCardType {
    val document = DOMParser().parseFromString(source, "text/html")
    val card =
        document.head?.getContentByAttributeValue("name" to "twitter:card")
            ?: document.body?.getContentByAttributeValue("name" to "twitter:card").orEmpty()
    return TweetCardType.parse(card)
}

private fun Element.getContentByAttributeValue(vararg keyValues: Pair<String, String>): String? {
    keyValues.forEach {
        val e = querySelector("meta[${it.first}='${it.second}']")
        if (e != null) return e.getAttribute("content")
    }
    return null
}
