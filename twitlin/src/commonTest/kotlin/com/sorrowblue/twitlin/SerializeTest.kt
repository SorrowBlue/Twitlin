package com.sorrowblue.twitlin

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KProperty
import kotlin.test.Test

class SerializeTest {

	@Test
	fun intTest() {
		val i = 521
		val json = Json.encodeToString(i)
		println(json)
		id = i
	}

	@Test
	fun stringTest() {
		val i = "adoa521"
		val json = Json.encodeToString(i)
		println(json)
		userName = i
		val s = setOf("aaaaaaaa")
		println(s::class)
	}

	inline fun <reified T: Any> T.isInt() = T::class == Int::class

	private var id: Int by PreferenceDelegate("preference")
	private var userName: String by PreferenceDelegate("preference")
}

class PreferenceDelegate<T : Any>(val fileName: String? = null) {
	inline operator fun <reified T : Any> setValue(thisRef: Any, property: KProperty<*>, value: T) {
		when (value) {
			is Int -> println("name = ${property.name}, value = $value, type = Int")
			is Long -> println("name = ${property.name}, value = $value, type = Long")
			is Float -> println("name = ${property.name}, value = $value, type = Float")
			is Boolean -> println("name = ${property.name}, value = $value, type = Boolean")
			is String -> println("name = ${property.name}, value = $value, type = String")
			is Set<*> ->
				if (value.all { it is String }) {
					println("name = ${property.name}, value = $value, type = Set<String>")
				} else {
					println(
						"name = ${property.name}, value = ${
							value.map { Json.encodeToString(it) }.toSet()
						}, type = ${value.first()!!::class}"
					)
				}
			else -> println("name = ${property.name}, value = $value, type = ${value::class}")
		}
	}

	operator fun getValue(thisRef: Any, property: KProperty<*>): T {
		TODO("Not yet implemented")
	}
}
