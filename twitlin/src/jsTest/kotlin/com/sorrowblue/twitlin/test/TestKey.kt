package com.sorrowblue.twitlin.test

import kotlin.test.Test

actual object TestKey {

	val res = JSON.stringify(js("require('../../../resources/local.json')"))
	actual val API_KEY: String
		get() = TODO()
	actual val API_SECRET: String
		get() = TODO("Not yet implemented")
	actual val ACCESS_TOKEN: String
		get() = TODO("Not yet implemented")
	actual val ACCESS_TOKEN_SECRET: String
		get() = TODO("Not yet implemented")

}

class Test3 {

	@Test
	fun test() {
		println(TestKey.res)

	}
}
