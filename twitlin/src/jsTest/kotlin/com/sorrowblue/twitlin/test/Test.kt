package com.sorrowblue.twitlin.test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

actual object Test {
	actual fun runTest(block: suspend () -> Unit): dynamic = GlobalScope.promise { block() }
}
