package com.sorrowblue.twitlin.test

actual object TestKey {

	private val res = JSON.stringify(js("require('../../../resources/local.properties')"))
	actual val API_KEY: String
		get() = TODO()
	actual val API_SECRET: String
		get() = TODO("Not yet implemented")
	actual val ACCESS_TOKEN: String
		get() = TODO("Not yet implemented")
	actual val ACCESS_TOKEN_SECRET: String
		get() = TODO("Not yet implemented")

}
