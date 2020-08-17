package com.sorrowblue.twitlin.test

expect object Test {
	fun runTest(block: suspend () -> Unit)
}
