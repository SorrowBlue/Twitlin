package com.sorrowblue.twitlin.net

import kotlin.test.Test
import kotlin.test.assertEquals


internal class StringKtTest {

	@Test
	fun urlEncode() {
		assertEquals("Ladies + Gentlemen".urlEncode(), "Ladies%20%2B%20Gentlemen")
		assertEquals("An encoded string!".urlEncode(), "An%20encoded%20string%21")
		assertEquals("Dogs, Cats & Mice".urlEncode(), "Dogs%2C%20Cats%20%26%20Mice")
		assertEquals("☃".urlEncode(), "%E2%98%83")
		assertEquals("-._~".urlEncode(), "-._~")
	}
}