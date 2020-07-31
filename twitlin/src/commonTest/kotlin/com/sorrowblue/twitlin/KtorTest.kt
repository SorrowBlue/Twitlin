package com.sorrowblue.twitlin

import com.sorrowblue.twitlin.utils.urlEncode
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

}
