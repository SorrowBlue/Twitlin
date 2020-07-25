package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.net.urlEncode
import io.ktor.http.formUrlEncode
import io.ktor.http.formUrlEncodeTo
import kotlin.test.Test
import kotlin.test.assertEquals

class KtorTest {

	@Test
	fun pairListFormUrlEncodeTest() {
		assertEquals(
			listOf(
				"foge" to "12412",
				"fuge" to "false",
				"bar" to "abcdefg",
				"foo" to "awd",
				"soo" to "0.08575"
			).formUrlEncode(), "foge=12412&fuge=false&bar=abcdefg&foo=awd&soo=0.08575"
		)
		assertEquals(listOf<Pair<String, String>>().formUrlEncode(), "")
		val sb = StringBuilder()
		listOf("foo" to "bar").formUrlEncodeTo(sb)
		assertEquals(sb.toString(), "foo=bar")
	}

	@Test
	fun urlEncodeTest() {
		assertEquals("Ladies + Gentlemen".urlEncode(), "Ladies%20%2B%20Gentlemen")
		assertEquals("An encoded string!".urlEncode(), "An%20encoded%20string%21")
		assertEquals("Dogs, Cats & Mice".urlEncode(), "Dogs%2C%20Cats%20%26%20Mice")
		assertEquals("☃".urlEncode(), "%E2%98%83")
		assertEquals("#$%'()=~|`{*}<>?_[;:]./-^\\".urlEncode(), "%23%24%25%27%28%29%3D%7E%7C%60%7B*%7D%3C%3E%3F_%5B%3B%3A%5D.%2F-%5E%5C", "OK")
	}

}
