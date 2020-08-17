package com.sorrowblue.twitlin.test

import kotlinx.coroutines.runBlocking

actual object Test {
	actual fun runTest(block: suspend () -> Unit) = runBlocking { block() }
}
