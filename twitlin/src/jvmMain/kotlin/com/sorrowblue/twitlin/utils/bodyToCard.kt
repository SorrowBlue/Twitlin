/*
 * (c) 2021 SorrowBlue.
 */

package com.sorrowblue.twitlin.utils

import com.sorrowblue.twitlin.objects.TwitterCard
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

public actual fun resolveCard(source: String): TwitterCard {
    val document = Jsoup.parse(source)
    val head = document.head()
    val body = document.body()
    val title = head.getContentByAttributeValue(TweetCardUtil.Attribute.title)
        ?: body.getContentByAttributeValue(TweetCardUtil.Attribute.title).orEmpty()
    val url = head.getContentByAttributeValue(TweetCardUtil.Attribute.url)
        ?: body.getContentByAttributeValue(TweetCardUtil.Attribute.url).orEmpty()
    val description = head.getContentByAttributeValue(TweetCardUtil.Attribute.description)
        ?: body.getContentByAttributeValue(TweetCardUtil.Attribute.description).orEmpty()
    val image = head.getContentByAttributeValue(TweetCardUtil.Attribute.image)
        ?: body.getContentByAttributeValue(TweetCardUtil.Attribute.image).orEmpty()
    val card = (head.getContentByAttributeValue(TweetCardUtil.Attribute.card)
        ?: body.getContentByAttributeValue(TweetCardUtil.Attribute.card)).orEmpty()
    val site = head.getContentByAttributeValue(TweetCardUtil.Attribute.site)
        ?: body.getContentByAttributeValue(TweetCardUtil.Attribute.site).orEmpty()
    return TwitterCard(title, url, description, image, TwitterCard.Type.parse(card), site)
}

private fun Element.getContentByAttributeValue(keyValues: List<Pair<String, String>>): String? {
    keyValues.forEach {
        val s = getElementsByAttributeValue(it.first, it.second).attr("content")
        if (s.isNotEmpty()) return s
    }
    return null
}

public actual fun resolveTweetCardType(source: String): TwitterCard.Type {
    val document: Document = Jsoup.parse(source)
    val card = document.head().getContentByAttributeValue(TweetCardUtil.Attribute.card)
        ?: document.body().getContentByAttributeValue(TweetCardUtil.Attribute.card).orEmpty()
    return TwitterCard.Type.parse(card)
}
