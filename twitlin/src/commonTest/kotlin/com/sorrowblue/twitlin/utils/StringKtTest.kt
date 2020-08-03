@file:Suppress("SpellCheckingInspection")

package com.sorrowblue.twitlin.utils

import kotlin.test.Test
import kotlin.test.assertEquals

class StringKtTest {

	@Test
	fun urlEncodeTest() {

		// Twitter API Examples tests
		assertEqualsUrlEncode("Ladies%20%2B%20Gentlemen", "Ladies + Gentlemen")
		assertEqualsUrlEncode("An%20encoded%20string%21", "An encoded string!")
		assertEqualsUrlEncode("Dogs%2C%20Cats%20%26%20Mice", "Dogs, Cats & Mice")
		assertEqualsUrlEncode("%E2%98%83", "☃")

		// Symbol tests
		assertEqualsUrlEncode("%20%21%22%23%24%25%26%27%28%29%2A%2B%2C-.%2F", """ !"#$%&'()*+,-./""")
		assertEqualsUrlEncode("%3A%3B%3C%3D%3E%3F%40", ":;<=>?@")
		assertEqualsUrlEncode("%5B%5C%5D%5E_%60", """[\]^_`""")
		assertEqualsUrlEncode("%7B%7C%7D~", "{|}~")

		// Other tests
		assertEqualsUrlEncode("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz")
		assertEqualsUrlEncode("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
		assertEqualsUrlEncode("%E3%82%92%E3%81%B8%E3%82%AC%EF%BD%B1%E8%B1%B5", "をへガｱ豵")
	}

	private fun assertEqualsUrlEncode(expected: String, value: String) {
		println("$value -> ${value.urlEncode()}")
		assertEquals(expected, value.urlEncode())
	}

}
