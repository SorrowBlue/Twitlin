package com.sorrowblue.twitlin.serializers

import com.soywiz.klock.*
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class DateTimeTzSerializerTest {

	@OptIn(ImplicitReflectionSerializer::class, UnstableDefault::class)
	@Test
	fun decodeTest() {
		val dateTimeTz = Json.stringify(Data(DateTime.invoke(1997, 5, 13, 12, 54, 44, 444).local))
		assertEquals("""{"dateTimeTz":"Tue May 13 12:54:44 +0000 1997"}""", dateTimeTz, "合格")
	}

	@OptIn(ImplicitReflectionSerializer::class, UnstableDefault::class)
	@Test
	fun encodeTest() {
		val dateTimeTz = Json.parse<Data>("""{"dateTimeTz":"Tue May 13 12:54:44 +0000 1997"}""")
		assertEquals(DateTime.invoke(1997, 5, 13, 12, 54, 44, 444).local.toString(), dateTimeTz.dateTimeTz.toString(), "合格")
	}

	@Serializable
	class Data(
		@Serializable(DateTimeTzSerializer::class)
		val dateTimeTz: DateTimeTz
	)
}
