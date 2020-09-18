package com.sorrowblue.twitlin.test

import org.junit.Test

class TTest {
	@Test
	fun stringTest() {
		val s: Set<String> = setOf("aaaaaaaa")
		println(s::class)
		println(s::class.javaObjectType)
		println(s::class.constructors)
		println(s::class.nestedClasses)
		println(s::class.objectInstance)
		println(s::class.qualifiedName)
		println(s::class.javaPrimitiveType)
		println(s::class.simpleName)
	}
}
