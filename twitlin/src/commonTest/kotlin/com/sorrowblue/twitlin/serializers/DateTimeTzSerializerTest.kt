package com.sorrowblue.twitlin.serializers

import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeTz
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

internal class DateTimeTzSerializerTest {

	private val source = DateTime.invoke(2017, 7, 11, 8, 38, 19).utc
	private val strSource = """{"date":"Tue Jul 11 08:38:19 +0000 2017"}"""


	@OptIn(UnstableDefault::class, ImplicitReflectionSerializer::class)
	@Test
	fun deserialize() {
		val json = Json.parse<TestData>(strSource)
		assertEquals(source, json.date)
	}

	@OptIn(UnstableDefault::class, ImplicitReflectionSerializer::class)
	@Test
	fun serialize() {
		assertEquals(strSource, Json.stringify(TestData(source)))
	}
}

@Serializable
private class TestData(
	@Serializable(DateTimeTzSerializer::class)
	val date: DateTimeTz
)
