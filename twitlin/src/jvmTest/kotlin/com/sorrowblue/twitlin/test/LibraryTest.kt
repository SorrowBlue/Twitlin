package com.sorrowblue.twitlin.test

import org.jsoup.Jsoup
import org.junit.Test

class LibraryTest {

    @Test
    fun jsoupParseTest() {
        val jsoup = Jsoup.parse("Jsoup.parse(source)")
        println("body " + jsoup.body().text())
        println("html " + jsoup.html())
        println("head " + jsoup.head().text())
    }
}
