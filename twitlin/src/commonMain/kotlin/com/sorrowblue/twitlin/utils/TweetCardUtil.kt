package com.sorrowblue.twitlin.utils

internal object TweetCardUtil {
    object Attribute {
        internal val description =
            listOf("property" to "og:description", "name" to "twitter:description")
        internal val image = listOf("property" to "og:image", "name" to "twitter:image:src")
        internal val card = listOf("name" to "twitter:card")
        internal val title = listOf("property" to "og:title", "name" to "twitter:title")
        internal val url = listOf("property" to "og:url", "name" to "twitter:url")
        internal val site = listOf("name" to "twitter:site")
    }
}
