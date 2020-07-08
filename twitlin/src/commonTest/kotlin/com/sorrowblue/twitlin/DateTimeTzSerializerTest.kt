package com.sorrowblue.twitlin

import com.soywiz.klock.*
import kotlin.test.Test

private const val TWITTER_PATTERN = "EEE MMM dd HH:mm:ss xx yyyy"


class DateTimeTzSerializerTest {

	@Test
	fun deserializeTest() {
		val decoder = "Tue Jul 11 08:38:19 +0000 2017"
		val d = DateFormat(TWITTER_PATTERN).parse(decoder).toOffset(TimezoneOffset.local(DateTime.EPOCH))
		println(d)

	}

	@Test
	fun serializeTest() {
		val s = DateTimeTz.nowLocal().utc.format(TWITTER_PATTERN)
		println(s)
	}
}
