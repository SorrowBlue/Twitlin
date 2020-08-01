package com.sorrowblue.twitlin.utils

import io.ktor.http.encodeURLParameter
import kotlin.test.Test
import kotlin.test.assertEquals

class StringKtTest {

	@Test
	fun urlEncode() {

		// Twitter API Examples tests
		assertEquals("Ladies + Gentlemen".urlEncode(), "Ladies%20%2B%20Gentlemen")
		assertEquals("An encoded string!".urlEncode(), "An%20encoded%20string%21")
		assertEquals("Dogs, Cats & Mice".urlEncode(), "Dogs%2C%20Cats%20%26%20Mice")
		assertEquals("☃".urlEncode(), "%E2%98%83")

		// Symbol tests
		assertEquals(
			""" !"#$%&'()*+,-./""".urlEncode(), "%20%21%22%23%24%25%26%27%28%29%2A%2B%2C-.%2F"
		)
		assertEquals("%3A%3B%3C%3D%3E%3F%40", ":;<=>?@".urlEncode())
		assertEquals("%5B%5C%5D%5E_%60", """[\]^_`""".urlEncode())
		assertEquals("%7B%7C%7D~", "{|}~".urlEncode())

		// Other tests
		assertEquals("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz".urlEncode())
		assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ".urlEncode())
		assertEquals("%E3%82%92%E3%81%B8%E3%82%AC%EF%BD%B1%E8%B1%B5", "をへガｱ豵".urlEncode())
	}

}

private fun String.urlEncode() = encodeURLParameter()
